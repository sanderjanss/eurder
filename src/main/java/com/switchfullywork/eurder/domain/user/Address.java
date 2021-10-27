package com.switchfullywork.eurder.domain.user;

public class Address {

    private final String streetName;
    private final String postalCode;
    private final String city;
    private final int houseNumber;

    public Address(String streetName, String postalCode, String city, int houseNumber) {
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}
