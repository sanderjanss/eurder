package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.entity.user.Address;
import com.switchfullywork.eurder.domain.entity.user.Role;
import com.switchfullywork.eurder.domain.entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultUserRepositoryTest {

    private UserRepository userRepository;
    private User customer1;
    private User customer2;

    @BeforeEach
    public void before() {
        userRepository = mock(DefaultUserRepository.class);
        Address address1 = new Address("LegendStreet", "2000", "Antwerp", 1);
        Address address2 = new Address("SpookyStreet", "2000", "Antwerp", 1);
        customer1 = new User("Sander", "Janssens", "sanderzz@email.com", address1, "0488888888", Role.CUSTOMER);
        customer2 = new User("Bram", "Janssens", "Bramzz@email.com", address2, "0477777777", Role.CUSTOMER);
        userRepository.registerCustomer(customer1);
        userRepository.registerCustomer(customer2);
    }

    @Test
    @DisplayName("Return all customers when no customerId is provided.")
    public void givenATestUserRepositoryWithValidCustomers_whenNoCustomerIdIsProvided_thenReturnAllCustomersFromDatabase() {

        when(userRepository.getAllCustomers(null)).thenReturn(List.of(customer2, customer1));
    }

    @Test
    @DisplayName("Return the customer who's id is provided.")
    public void givenATestUserRepository_whenRegisteringValidCustomers_whenProvidedAValidCustomerId_thenReturnValidCustomer() {

        when(userRepository.getAllCustomers(customer2.getUserId())).thenReturn(List.of(customer2));
    }


}