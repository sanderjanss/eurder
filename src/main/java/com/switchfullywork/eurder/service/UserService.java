package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.user.CreateUserDTO;
import com.switchfullywork.eurder.domain.user.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void registerCustomer(CreateUserDTO user);
    List<UserDTO> getAllCustomers(UUID adminId, UUID memberId);
}
