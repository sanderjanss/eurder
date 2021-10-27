package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerCustomer(User customer){
        if(customer.getRole() != Role.CUSTOMER){
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
        userRepository.registerCustomer(customer);
    }

    public List<User> getAllCustomers(UUID adminId, UUID memberId){
        if(userRepository.findById(adminId) == null){
            throw new InvalidUserException("This account is not part of the database.");
        }
        if(userRepository.findById(adminId).getRole() != Role.ADMIN){
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
        if(userRepository.findById(memberId) == null){
            throw new InvalidUserException("This account is not part of the database.");
        }
        return userRepository.getAllCustomers(memberId);
    }
}
