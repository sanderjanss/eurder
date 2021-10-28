package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.user.*;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.exceptions.UserAllreadyExistsException;
import com.switchfullywork.eurder.mappers.UserMapper;
import com.switchfullywork.eurder.repository.DefaultUserRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class DefaultUserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    private User customer1;
    private CreateUserDTO customer2;
    private User admin2;
    private User customer3;
    private CreateUserDTO admin1;


    @BeforeEach
    public void before(){
        userRepository = new DefaultUserRepository();
        userService = new DefaultUserService(userRepository, userMapper);
        Address address1 = new Address("LegendStreet", "2000", "Antwerp", 1);
        Address address2 = new Address("AdminStreet", "2000", "Antwerp", 1);
        customer1 = new User("Sander", "Janssens", "sanderzz@email.com", address1, "0488888888", Role.CUSTOMER);
        customer2 = new CreateUserDTO("Bram", "Hanssens", "sanderzz@email.com", address1, "0488888988", Role.CUSTOMER);
        customer3 = new User("Test1", "Janssens", "TestCustomer1@email.com", address1, "0488888888", Role.CUSTOMER);
        admin1 = new CreateUserDTO("Admin", "Janssens", "Admin@email.com", address2, "0411111111", Role.ADMIN);
        admin2 = new User("Admin2", "Janssens", "Admin@email.com", address2, "0411111111", Role.ADMIN);
        userRepository.registerCustomer(customer1);
        userRepository.registerCustomer(customer3);
    }

    @Test
    public void givenATestDatabase_whenAUserRegistersAsAnAdmin_thenThrowNoAutorizationException(){
        Assertions.assertThatThrownBy(()-> userService.registerCustomer(admin1)).isInstanceOf(NoAuthorizationException.class);
    }

    @Test
    public void givenATestDatabase_whenAUserRegistersAsAnExistingCustomer_thenThrowUserAllreadyExistsException(){
        Assertions.assertThatThrownBy(()-> userService.registerCustomer(customer2)).isInstanceOf(UserAllreadyExistsException.class);
    }

    @Test
    public void givenATestDatabase_whenACustomerTriesToAccesTheUserDatabase_thenThrowNewNoAutorizationException(){
        Assertions.assertThatThrownBy(()-> userService.getAllCustomers(customer1.getUserId(), customer3.getUserId())).isInstanceOf(NoAuthorizationException.class);
    }
    @Test
    public void givenATestDatabase_whenAUnregisteredUserTriesToAccesTheUserDatabase_thenThrowNewInvalidUserException(){
        Assertions.assertThatThrownBy(()-> userService.getAllCustomers(admin2.getUserId(), UUID.randomUUID())).isInstanceOf(InvalidUserException.class);
    }

//    @Test
//    public void givenATestDatabase_whenACustomerTriesToAccesTheUserDatabase_thenThrowNewNoAutorizationException(){
//
//        Assertions.assertThatThrownBy(()-> userService.getAllCustomers(customer1.getUuid())).isInstanceOf(NoAuthorizationException.class);
//    }
}