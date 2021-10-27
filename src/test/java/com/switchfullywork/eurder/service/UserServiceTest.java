package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.user.Address;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private User customer1;
    private User admin1;
    private Address address1;
    private Address address2;


    @BeforeEach
    public void before(){
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
        address1 = new Address("LegendStreet", "2000", "Antwerp", 1);
        address2 = new Address("AdminStreet", "2000", "Antwerp", 1);
        customer1 = new User("Sander", "Janssens", "sanderzz@email.com", address1, "0488888888", Role.CUSTOMER);
        admin1 = new User("Admin", "Janssens", "Admin@email.com", address2, "0411111111", Role.ADMIN);
        userRepository.registerCustomer(customer1);
    }

    @Test
    public void givenATestDatabase_whenAUserRegistersAsAnAdmin_thenThrowNoAutorizationException(){
        Assertions.assertThatThrownBy(()-> userService.registerCustomer(admin1)).isInstanceOf(NoAuthorizationException.class);
    }

    @Test
    public void givenATestDatabase_whenACustomerTriesToAccesTheUserDatabase_thenThrowNewNoAutorizationException(){
        Assertions.assertThatThrownBy(()-> userService.getAllCustomers(customer1.getUuid(), UUID.randomUUID())).isInstanceOf(NoAuthorizationException.class);
    }
    @Test
    public void givenATestDatabase_whenAUnregisteredUserTriesToAccesTheUserDatabase_thenThrowNewInvalidUserException(){
        Assertions.assertThatThrownBy(()-> userService.getAllCustomers(admin1.getUuid(), UUID.randomUUID())).isInstanceOf(InvalidUserException.class);
    }

//    @Test
//    public void givenATestDatabase_whenACustomerTriesToAccesTheUserDatabase_thenThrowNewNoAutorizationException(){
//
//        Assertions.assertThatThrownBy(()-> userService.getAllCustomers(customer1.getUuid())).isInstanceOf(NoAuthorizationException.class);
//    }
}