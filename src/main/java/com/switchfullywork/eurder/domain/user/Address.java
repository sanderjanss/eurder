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

    public static class AddressBuilder{

        private String streetName;
        private String postalCode;
        private String city;
        private int houseNumber;

        public AddressBuilder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Address build(){
            return new Address(this.streetName, this.postalCode, this.city, this.houseNumber);
        }
    }
}
