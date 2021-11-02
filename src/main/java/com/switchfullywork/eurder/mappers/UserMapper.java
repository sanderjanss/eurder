package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.user.CreateUserDTO;
import com.switchfullywork.eurder.domain.user.User;
import com.switchfullywork.eurder.domain.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public List<UserDTO> toDtoList(List<User> userList) {
        return userList.stream().map(this::toDto).toList();
    }

    public User toUser(CreateUserDTO createUserDTO) {
        var userBuilder = new User.UserBuilder();
        return userBuilder
                .setFirstName(createUserDTO.getFirstName())
                .setLastName(createUserDTO.getLastName())
                .setAddress(createUserDTO.getAddress())
                .setEmailAddress(createUserDTO.getEmailAddress())
                .setPhoneNumber(createUserDTO.getPhoneNumber())
                .setRole(createUserDTO.getRole())
                .build();
    }

    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .emailAddress(user.getEmailAddress())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
