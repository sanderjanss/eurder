package com.switchfullywork.eurder.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Address {

    private final String streetName;
    private final String postalCode;
    private final String city;
    private final int houseNumber;

}
