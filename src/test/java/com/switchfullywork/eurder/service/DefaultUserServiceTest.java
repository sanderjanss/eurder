package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.user.Address;
import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.entity.user.Role;
import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.exceptions.UserAllreadyExistsException;
import com.switchfullywork.eurder.mappers.UserMapper;
import com.switchfullywork.eurder.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
class DefaultUserServiceTest {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    private User customer;
    private User customer2;
    private CreateUserRequest customerDTO;
    private CreateUserRequest adminDTO;
    private User fakeAdmin;
    private User admin;


    @BeforeEach
    public void before() {

        Address address2 = new Address("AdminStreet", "2000", "Antwerp", 1);
        admin = new User("Admin", "Janssens", "AdminTest@email.com", address2, "0411111111", Role.ADMIN);
        fakeAdmin = new User("FakeAdmin", "Janssens", "FakeAdminTest2@email.com", address2, "0411111111", Role.ADMIN);

        Address address1 = new Address("LegendStreet", "2000", "Antwerp", 1);
        customer = new User("Customer", "Janssens", "CustomerTest@email.com", address1, "0488888888", Role.CUSTOMER);
        customer2 = new User("Customer2", "Janssens", "CustomerTest2@email.com", address1, "0488888888", Role.CUSTOMER);

        userRepository.registerAdmin(admin);
    }

    @Test
    public void givenATestDatabase_whenAUserRegistersAsAnAdmin_thenThrowNoAutorizationException() {
        adminDTO = CreateUserRequest.builder()
                .firstName("AdminDTO")
                .lastName("Janssens")
                .emailAddress("AdminTestDTO@email.com")
                .address(Address.builder().streetName("Adminstreet").houseNumber(1).postalCode("2000").city("Antwerp").build())
                .phoneNumber("0411111111")
                .role(Role.ADMIN)
                .build();
        Assertions.assertThatThrownBy(() -> userService.registerCustomer(adminDTO)).isInstanceOf(NoAuthorizationException.class);
//        doThrow(NoAuthorizationException.class).when(userService).registerCustomer(adminDTO);
    }

    @Test
    public void givenATestDatabase_whenAUserRegistersAsAnExistingCustomer_thenThrowUserAllreadyExistsException() {
        customerDTO = CreateUserRequest.builder()
                .firstName("CustomerDTO")
                .lastName("Janssens")
                .emailAddress("CustomerTestDTO@email.com")
                .address(Address.builder().streetName("Customerstreet").houseNumber(1).postalCode("2000").city("Antwerp").build())
                .phoneNumber("0422222222")
                .role(Role.CUSTOMER)
                .build();

        userService.registerCustomer(customerDTO);
        Assertions.assertThatThrownBy(() -> userService.registerCustomer(customerDTO)).isInstanceOf(UserAllreadyExistsException.class);

//        doThrow(UserAllreadyExistsException.class).when(userService).registerCustomer(customerDTO);
    }

    @Test
    public void givenATestDatabase_whenACustomerTriesToAccesTheUserDatabase_thenThrowNewNoAutorizationException() {

        userRepository.registerCustomer(customer);

//        doThrow(NoAuthorizationException.class).when(userService).getAllCustomers(customer.getUserId(), UUID.randomUUID());
        Assertions.assertThatThrownBy(() -> userService.getAllCustomers(customer.getUserId(), UUID.randomUUID())).isInstanceOf(NoAuthorizationException.class);

    }

    @Test
    public void givenATestDatabase_whenAnUnregisteredAdminTriesToAccesTheUserDatabase_thenThrowNewInvalidUserException() {

//        doThrow(InvalidUserException.class).when(userService).getAllCustomers(fakeAdmin.getUserId(), UUID.randomUUID());
        Assertions.assertThatThrownBy(() -> userService.getAllCustomers(fakeAdmin.getUserId(), UUID.randomUUID())).isInstanceOf(InvalidUserException.class);

    }

    @Test
    public void givenATestDatabase_whenAnAdminUserTriesToAccesTheUserDatabaseWithoutMemberId_thenReturnAllCustomers() {
        userRepository.registerCustomer(customer);
        userRepository.registerCustomer(customer2);

//        when(userService.getAllCustomers(admin.getUserId(), null)).thenReturn(userMapper.toDtoList(List.of(customer, customer2)));
        Assertions.assertThat(userService.getAllCustomers(admin.getUserId(), null)).containsAll(userMapper.toDtoList(List.of(customer, customer2)));
    }

    @Test
    public void givenATestDatabase_whenAnAdminUserTriesToAccesTheUserDatabaseWithMemberId_thenReturnThatCustomer() {
        userRepository.registerCustomer(customer);
        userRepository.registerCustomer(customer2);

//        when(userService.getAllCustomers(admin.getUserId(), customer.getUserId())).thenReturn(userMapper.toDtoList(List.of(customer)));
        Assertions.assertThat(userService.getAllCustomers(admin.getUserId(), customer.getUserId())).containsAll(userMapper.toDtoList(List.of(customer)));

    }
}