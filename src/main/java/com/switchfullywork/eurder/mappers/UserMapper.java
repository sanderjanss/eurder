package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.domain.userdto.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public List<UserResponse> toDtoList(List<User> userList) {
        return userList.stream().map(this::toDto).toList();
    }

    public User toUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .address(createUserRequest.getAddress())
                .emailAddress(createUserRequest.getEmailAddress())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .role(createUserRequest.getRole())
                .build();
    }

    public UserResponse toDto(User user) {
        return UserResponse.builder()
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
