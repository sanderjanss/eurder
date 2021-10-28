package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.user.CreateUserDTO;
import com.switchfullywork.eurder.domain.user.User;
import com.switchfullywork.eurder.domain.user.UserDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserMapper {

    public List<UserDTO> toDtoList(List<User> userList){
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

    public UserDTO toDto(User user){

        var userDTOBuilder = new UserDTO.UserDTOBuilder();
        return userDTOBuilder
                .setUserId(user.getUserId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setAddress(user.getAddress())
                .setEmailAddress(user.getEmailAddress())
                .setRole(user.getRole())
                .setPhoneNumber(user.getPhoneNumber())
                .build();
    }
}
