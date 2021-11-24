package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.user.Role;
import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.userdto.UserResponse;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.exceptions.UserAllreadyExistsException;
import com.switchfullywork.eurder.mappers.UserMapper;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ValidationService validationService;

    @Autowired
    public DefaultUserService(UserRepository userRepository, UserMapper userMapper, ValidationService validationService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.validationService = validationService;
    }

    public void registerCustomer(CreateUserRequest customer) {
        validationService.assertValidUserRequest(customer);
        if (customer.getRole() != Role.CUSTOMER) {
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
        User user = userMapper.toUser(customer);
        if (userRepository.findUserByEmailAddress(user.getEmailAddress()) != null) {
            throw new UserAllreadyExistsException();
        }
        userRepository.save(user);
    }

    public List<UserResponse> getAllCustomers() {
        List<User> customerList = userRepository.findAll().stream().filter(user -> user.getRole() == Role.CUSTOMER).toList();
        return userMapper.toDtoList(customerList);
    }

    @Override
    public UserResponse findUserByUserId(Integer userId) {
        return userMapper.toDto(userRepository.findUserByUserId(userId));
    }

}
