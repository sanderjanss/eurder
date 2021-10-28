package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    boolean contains(User user);
    User findById(UUID id);
    void registerCustomer(User user);
    List<User> getAllCustomers(UUID userIdOrNull);
}
