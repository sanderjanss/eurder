package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.domain.userdto.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<UserResponse> toDtoList(List<User> userList) {
        return userList.stream().map(this::toDto).toList();
    }

    public User toUser(CreateUserRequest createUserRequest) {
        return new User.Builder()
                .withFirstName(createUserRequest.getFirstName())
                .withLastName(createUserRequest.getLastName())
                .withAddress(addressMapper.toEntity(createUserRequest.getAddress()))
                .withEmailAddress(createUserRequest.getEmailAddress())
                .withPhoneNumber(createUserRequest.getPhoneNumber())
                .withRole(createUserRequest.getRole())
                .build();
    }

    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(addressMapper.toDto(user.getAddress()))
                .emailAddress(user.getEmailAddress())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
