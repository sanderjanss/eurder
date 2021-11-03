package com.switchfullywork.eurder.domain.userdto;

import com.switchfullywork.eurder.domain.entity.user.Address;
import com.switchfullywork.eurder.domain.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateUserRequest {

    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;
    private final Role role;


}
