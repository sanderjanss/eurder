package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.userdto.UserResponse;

import java.util.List;

public interface UserService {

    void registerCustomer(CreateUserRequest user);

    List<UserResponse> getAllCustomers();

    UserResponse findUserByUserId(Integer userId);

}
