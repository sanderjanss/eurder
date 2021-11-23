package com.switchfullywork.eurder.domain.userdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
public class CreateAddressRequest {


    private final String streetName;
    private final String postalCode;
    private final String city;
    private final int houseNumber;

}
