package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.userdto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void registerCustomer(CreateUserRequest user);

    List<UserResponse> getAllCustomers();

    UserResponse findUserByUserId(Integer userId);

}
