package com.switchfullywork.eurder.domain.entity.user;

import com.switchfullywork.eurder.domain.entity.user.Address;
import com.switchfullywork.eurder.domain.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = { "emailAddress" })
public class User {

    private final UUID userId = UUID.randomUUID();
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;
    private final Role role;


}
