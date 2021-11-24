package com.switchfullywork.eurder.domain.userdto;

import com.switchfullywork.eurder.domain.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final AddressResponse address;
    private final String phoneNumber;
    private final Role role;

}
