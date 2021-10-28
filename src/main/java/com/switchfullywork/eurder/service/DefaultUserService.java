package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.user.CreateUserDTO;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;
import com.switchfullywork.eurder.domain.user.UserDTO;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.exceptions.UserAllreadyExistsException;
import com.switchfullywork.eurder.mappers.UserMapper;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public DefaultUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void registerCustomer(CreateUserDTO customer){
        if(customer == null){
            throw new InvalidUserException("Invalid user.");
        }
        if(customer.getRole() != Role.CUSTOMER){
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
        User user = userMapper.toUser(customer);
        if(!userRepository.contains(user)){
            throw new UserAllreadyExistsException("This emailaddress is allready registered.");
        }
        userRepository.registerCustomer(user);
    }

    public List<UserDTO> getAllCustomers(UUID adminId, UUID memberId){
        assertValidUser(adminId);
        assertValidUser(memberId);
        assertAuthorizedUser(adminId);

        return userMapper.toDtoList(userRepository.getAllCustomers(memberId));
    }


    private void assertValidUser(UUID userId){
        if(userRepository.findById(userId) == null){
            throw new InvalidUserException("This account is not part of the database.");
        }
    }
    private void assertAuthorizedUser(UUID userId){
        if(userRepository.findById(userId).getRole() != Role.ADMIN){
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
    }
}
