package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.user.Address;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
class UserRepositoryTest {

    private UserRepository userRepository;
    private User customer1;
    private User customer2;
    private User admin1;
    private Address address1;
    private Address address2;
    private Address address3;

    @BeforeEach
    public void before(){
        userRepository = new UserRepository();
        address1 = new Address("LegendStreet", "2000", "Antwerp", 1);
        address2 = new Address("SpookyStreet", "2000", "Antwerp", 1);
        address3 = new Address("AdminStreet", "2000", "Antwerp", 1);
        customer1 = new User("Sander", "Janssens", "sanderzz@email.com", address1, "0488888888", Role.CUSTOMER);
        customer2 = new User("Bram", "Janssens", "Bramzz@email.com", address2, "0477777777", Role.CUSTOMER);
        admin1 = new User("Admin", "Janssens", "Admin@email.com", address3, "0411111111", Role.ADMIN);

    }

    @Test
    public void givenATestUserRepository_whenRegisteringValidCustomersAndNoCustomerIdIsProvided_thenReturnAllCustomersFromDatabase(){
        //when
        userRepository.registerCustomer(customer1);
        userRepository.registerCustomer(customer2);

        //then
        Assertions.assertEquals(userRepository.getAllCustomers(null), List.of(customer1, customer2));
    }

    @Test
    public void givenATestUserRepository_whenRegisteringValidCustomers_whenProvidedAValidCustomerId_thenReturnValidCustomer(){
        //when
        userRepository.registerCustomer(customer1);
        userRepository.registerCustomer(customer2);

        //then
        Assertions.assertEquals(userRepository.getAllCustomers(customer2.getUuid()), List.of(customer2));
    }






}