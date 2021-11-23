package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.entity.user.Address;
import com.switchfullywork.eurder.domain.userdto.AddressResponse;
import com.switchfullywork.eurder.domain.userdto.CreateAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity (CreateAddressRequest createAddressRequest){
        return Address.builder()
                .streetName(createAddressRequest.getStreetName())
                .houseNumber(createAddressRequest.getHouseNumber())
                .postalCode(createAddressRequest.getPostalCode())
                .city(createAddressRequest.getCity())
                .build();
    }

    public AddressResponse toDto(Address address){
        return AddressResponse.builder()
                .addressId(address.getAddress_id())
                .streetName(address.getStreetName())
                .houseNumber(address.getHouseNumber())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .build();
    }
}
