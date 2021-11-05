package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.userdto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void registerCustomer(CreateUserRequest user);

    List<UserResponse> getAllCustomers(UUID adminId, UUID memberId);

    void assertValidUser(UUID userId);

    void assertAuthorizedUser(UUID userId);
}
