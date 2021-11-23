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

    @Autowired
    public DefaultUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void registerCustomer(CreateUserRequest customer) {
        if (customer == null) {
            throw new InvalidUserException("Invalid user.");
        }
        if (customer.getRole() != Role.CUSTOMER) {
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
        User user = userMapper.toUser(customer);
        if (userRepository.findUserByEmailAddress(user.getEmailAddress()) != null) {
            throw new UserAllreadyExistsException("This emailaddress is allready registered.");
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


    public void assertValidUser(int userId) {
        if (userRepository.findUserByUserId(userId) == null) {
            throw new InvalidUserException("This account is not part of the database.");
        }
    }

    public void assertAuthorizedUser(int userId) {
        if (userRepository.findUserByUserId(userId).getRole() != Role.ADMIN) {
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
    }


}
