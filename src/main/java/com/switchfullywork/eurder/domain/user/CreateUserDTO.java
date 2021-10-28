package com.switchfullywork.eurder.domain.user;

import com.switchfullywork.eurder.exceptions.InvalidUserException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class CreateUserDTO {

    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;
    private final Role role;

    public CreateUserDTO(String firstName, String lastName, String emailAddress, Address address, String phoneNumber, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = isValidEmailAddress(emailAddress);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
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

    public String isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new InvalidUserException("Not a valid email address");
        }
        return email;
    }

    public static class CreateUserDTOBuilder {


        private String firstName;
        private String lastName;
        private String emailAddress;
        private Address address;
        private String phoneNumber;
        private Role role;


        public CreateUserDTOBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CreateUserDTOBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CreateUserDTOBuilder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public CreateUserDTOBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public CreateUserDTOBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CreateUserDTOBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public CreateUserDTO build() {
            return new CreateUserDTO(this.firstName, this.lastName, this.emailAddress, this.address, this.phoneNumber, this.role);
        }

    }
}
