package com.switchfullywork.eurder.domain.user;

import java.util.UUID;

public class UserDTO {
    private final UUID userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;
    private final Role role;

    public UserDTO(UUID userId, String firstName, String lastName, String emailAddress, Address address, String phoneNumber, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public static class UserDTOBuilder {

        private UUID userId;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private Address address;
        private String phoneNumber;
        private Role role;

        public UserDTOBuilder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public UserDTOBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDTOBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDTOBuilder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public UserDTOBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public UserDTOBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDTOBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserDTO build(){
            return new UserDTO(this.userId, this.firstName, this.lastName, this.emailAddress, this.address, this.phoneNumber, this.role);
        }

    }
}
