package com.switchfullywork.eurder.domain.userdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddressResponse {

    private final int addressId;
    private final String streetName;
    private final String postalCode;
    private final String city;
    private final int houseNumber;
}
