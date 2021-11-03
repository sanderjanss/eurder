package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.item.CreateItemDTO;
import com.switchfullywork.eurder.domain.user.Address;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.ItemAllreadyExistsException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DefaultItemServiceTest {


    @Autowired
    private ItemService itemService;
    @Autowired
    private UserRepository userRepository;

    private User customer;
    private User admin;
    private CreateItemDTO createItemDTO1;


    @BeforeEach
    public void before() {
        Address address1 = new Address("LegendStreet", "2000", "Antwerp", 1);
        Address address2 = new Address("SpookyStreet", "2000", "Antwerp", 1);
        customer = new User("Bram", "Janssens", "Bramzz@email.com", address1, "0477777777", Role.CUSTOMER);
        admin = new User("Admin", "Janssens", "Admin@email.com", address2, "0411111111", Role.ADMIN);
        createItemDTO1 = new CreateItemDTO("Dog", "A pluchy dog", 20, 8);
        userRepository.registerCustomer(customer);
        userRepository.registerAdmin(admin);
    }

    @AfterEach
    public void afterEachTest() {

    }

    @Test
    public void givenTestItemDatabase_whenUserWithoutAuthorizationRegistersItem_ThenThrowNewNoAuthorizationException() {
        Assertions.assertThatThrownBy(() -> itemService.registerItem(createItemDTO1, customer.getUserId()))
                .isInstanceOf(NoAuthorizationException.class);
    }

    @Test
    public void givenTestItemDatabase_whenAdminRegistersNullItem_ThenThrowNewInvalidItemException() {
        Assertions.assertThatThrownBy(() -> itemService.registerItem(null, customer.getUserId()))
                .isInstanceOf(InvalidItemException.class);
    }

    @Test
    public void givenTestItemDatabase_whenRegisteredItemAllreadyPartOfDatabase_ThenThrowNewItemAllreadyExistsException() {
        itemService.registerItem(createItemDTO1, admin.getUserId());

        Assertions.assertThatThrownBy(() -> itemService.registerItem(createItemDTO1, admin.getUserId()))
                .isInstanceOf(ItemAllreadyExistsException.class);
    }

//    @Test
//    public void givenTestItemDatabase_whenUserWithInvalidIdRegistersItem_ThenThrowNewInvaliduserException() {
//        Assertions.assertThatThrownBy(() -> itemService.registerItem(createItemDTO1, UUID.randomUUID()))
//                .isInstanceOf(InvalidUserException.class);
//    }


}