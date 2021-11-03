package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.item.Item;
import com.switchfullywork.eurder.domain.entity.user.Address;
import com.switchfullywork.eurder.domain.entity.user.Role;
import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.ItemAllreadyExistsException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DefaultItemServiceTest {


    @Autowired
    private ItemService itemService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    private User customer;
    private User admin;
    private CreateItemRequest createItemRequest1;
    private CreateItemRequest createItemRequest2;
    private CreateItemRequest createItemRequest3;
    private Item item;


    @BeforeEach
    public void before() {
        Address address1 = new Address("LegendStreet", "2000", "Antwerp", 1);
        Address address2 = new Address("SpookyStreet", "2000", "Antwerp", 1);
        customer = new User("Bram", "Janssens", "Bramzz@email.com", address1, "0477777777", Role.CUSTOMER);
        admin = new User("Admin", "Janssens", "Admin@email.com", address2, "0411111111", Role.ADMIN);
        createItemRequest1 = new CreateItemRequest("Dog", "A pluchy dog", 20, 8);
        createItemRequest2 = new CreateItemRequest("Rabbit", "A pluchy rabbit", 20, 8);
        createItemRequest3 = new CreateItemRequest("Frog", "A pluchy frog", 20, 8);
        item = new Item("Cat", "A pluchy cat", 20, 20);

        userRepository.registerCustomer(customer);
        userRepository.registerAdmin(admin);
        itemRepository.registerItem(item);

    }

    @AfterEach
    public void afterEachTest() {

    }

    @Test
    public void givenTestItemDatabase_whenUserWithoutAuthorizationRegistersItem_ThenThrowNewNoAuthorizationException() {
        Assertions.assertThatThrownBy(() -> itemService.registerItem(createItemRequest2, customer.getUserId()))
                .isInstanceOf(NoAuthorizationException.class);
    }

    @Test
    public void givenTestItemDatabase_whenAdminRegistersNullItem_ThenThrowNewInvalidItemException() {
        Assertions.assertThatThrownBy(() -> itemService.registerItem(null, customer.getUserId()))
                .isInstanceOf(InvalidItemException.class);
    }

    @Test
    public void givenTestItemDatabase_whenRegisteredItemAllreadyPartOfDatabase_ThenThrowNewItemAllreadyExistsException() {
        //WHEN USING CREATEITEMDTO1 CLEAN INSTALL DOESNT WORK
        itemService.registerItem(createItemRequest3, admin.getUserId());

        Assertions.assertThatThrownBy(() -> itemService.registerItem(createItemRequest3, admin.getUserId()))
                .isInstanceOf(ItemAllreadyExistsException.class);
    }

    @Test
    public void givenTestItemDatabase_whenInvalidUserTriesToUpdateAnItem_thenThrowNewInvalidUserException() {
        Assertions.assertThatThrownBy(() ->
                        itemService.updateItem(createItemRequest1, UUID.randomUUID(), item.getItemId()))
                .isInstanceOf(InvalidUserException.class);
    }

    @Test
    public void givenTestItemDatabase_whenValidUserTriesToUpdateInvalidItem_thenThrowNewInvalidItemException() {
        Assertions.assertThatThrownBy(() ->
                        itemService.updateItem(createItemRequest1, admin.getUserId(), UUID.randomUUID()))
                .isInstanceOf(InvalidItemException.class);
    }

    @Test
    public void givenTestItemDatabase_whenPassedOnObjectIsNull_thenThrowNewInvalidItemException() {
        Assertions.assertThatThrownBy(() ->
                        itemService.updateItem(null, admin.getUserId(), item.getItemId()))
                .isInstanceOf(InvalidItemException.class);
    }

    @Test
    public void givenTestItemDatabase_whenACustomerTriesToUpdateAnItem_thenThrowNewNoAuthorizationException() {
        Assertions.assertThatThrownBy(() ->
                        itemService.updateItem(createItemRequest1, customer.getUserId(), item.getItemId()))
                .isInstanceOf(NoAuthorizationException.class);
    }


//    @Test
//    public void givenTestItemDatabase_whenUserWithInvalidIdRegistersItem_ThenThrowNewInvaliduserException() {
//        Assertions.assertThatThrownBy(() -> itemService.registerItem(createItemRequest1, UUID.randomUUID()))
//                .isInstanceOf(InvalidUserException.class);
//    }


}