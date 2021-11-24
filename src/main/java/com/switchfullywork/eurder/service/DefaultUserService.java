package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.user.Role;
import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.userdto.UserResponse;
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

    public void registerCustomer(CreateUserRequest createUserRequest) {
        validationService.assertValidUserRequest(createUserRequest);
        userRepository.save(userMapper.toEntity(createUserRequest));
    }

    public List<UserResponse> getAllCustomers() {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> user.getRole() == Role.CUSTOMER)
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserResponse findUserByUserId(Integer userId) {
        return userMapper.toDto(userRepository.findUserByUserId(userId));
    }

}
