package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.item.CreateItemGroupDTO;
import com.switchfullywork.eurder.domain.item.Item;
import com.switchfullywork.eurder.domain.order.CreateOrderDTO;
import com.switchfullywork.eurder.domain.user.Address;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.InvalidOrderException;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.mappers.OrderMapper;
import com.switchfullywork.eurder.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class DefaultOrderServiceTest {


    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private OrderService orderService;

    private ItemRepository itemRepository;

    private OrderMapper orderMapper;

    private CreateOrderDTO validOrderDTO;
    private CreateOrderDTO invalidOrder1DTO;
    private CreateOrderDTO invalidOrder2DTO;
    private CreateItemGroupDTO validItemGroupDTO1;
    private CreateItemGroupDTO validItemGroupDTO2;
    private CreateItemGroupDTO invalidItemGroupDTO;
    private User validCustomer;
    private User invalidCustomer;
    private Item validItem;
    private Item validItem2;
    private Item invalidItem;

    @BeforeEach
    public void before() {
        orderRepository = new DefaultOrderRepository();
        userRepository = new DefaultUserRepository();
        itemRepository = new DefaultItemRepository();
        orderService = new DefaultOrderService(orderRepository, orderMapper, userRepository, itemRepository);

        invalidCustomer = new User.UserBuilder()
                .setFirstName("NoCustomer")
                .setLastName("NoCustomer")
                .setEmailAddress("NoCustomer@eurder.com")
                .setPhoneNumber("0411111111")
                .setRole(Role.CUSTOMER)
                .setAddress(new Address.AddressBuilder()
                        .setStreetName("NoCustomerStreet")
                        .setHouseNumber(1)
                        .setPostalCode("1000")
                        .setCity("Brussels")
                        .build())
                .build();
        validCustomer = new User.UserBuilder()
                .setFirstName("TestCustomer")
                .setLastName("TestCustomer")
                .setEmailAddress("TestCustomer@eurder.com")
                .setPhoneNumber("0422222222")
                .setRole(Role.CUSTOMER)
                .setAddress(new Address.AddressBuilder()
                        .setStreetName("CustomerStreet")
                        .setHouseNumber(1)
                        .setPostalCode("1000")
                        .setCity("Brussels")
                        .build())
                .build();

        userRepository.registerCustomer(validCustomer);

        validItem = new Item.ItemBuilder()
                .setName("Dog")
                .setDescription("A pluchy dog")
                .setAmountStock(100)
                .setPrice(20)
                .build();
        validItem2 = new Item.ItemBuilder()
                .setName("Cat")
                .setDescription("A pluchy cat")
                .setAmountStock(100)
                .setPrice(20)
                .build();
        itemRepository.registerItem(validItem);
        itemRepository.registerItem(validItem2);

        validItemGroupDTO1 = new CreateItemGroupDTO.CreateItemGroupDTOBuilder()
                .setItemId(validItem.getItemId())
                .setAmount(5)
                .setShippingDate(LocalDate.now())
                .build();
        validItemGroupDTO2 = new CreateItemGroupDTO.CreateItemGroupDTOBuilder()
                .setItemId(validItem2.getItemId())
                .setAmount(5)
                .setShippingDate(LocalDate.now())
                .build();

        invalidItemGroupDTO = new CreateItemGroupDTO.CreateItemGroupDTOBuilder()
                .setItemId(UUID.randomUUID())
                .setAmount(5)
                .setShippingDate(LocalDate.now())
                .build();

        validOrderDTO = new CreateOrderDTO.CreateOrderDTOBuilder()
                .setCustomerId(validCustomer.getUserId())
                .setListOfItemGroups(List.of(validItemGroupDTO1, validItemGroupDTO2))
                .build();

        invalidOrder1DTO = new CreateOrderDTO.CreateOrderDTOBuilder()
                .setCustomerId(invalidCustomer.getUserId())
                .setListOfItemGroups(List.of(validItemGroupDTO1))
                .build();

        invalidOrder2DTO = new CreateOrderDTO.CreateOrderDTOBuilder()
                .setCustomerId(validCustomer.getUserId())
                .setListOfItemGroups(List.of(invalidItemGroupDTO))
                .build();
    }

    @Test
    public void givenTestOrderDatabase_whenRegisteredOrderIsNull_ThenThrowInvalidOrderException() {
        Assertions.assertThatThrownBy(() -> orderService.registerOrder(null)).isInstanceOf(InvalidOrderException.class);
    }

    @Test
    public void givenTestOrderDatabase_whenOrderCustomerIdDoesntMatchAnIdFromTheUserDatabase_ThenThrowInvalidUserException() {
        Assertions.assertThatThrownBy(() -> orderService.registerOrder(invalidOrder1DTO)).isInstanceOf(InvalidUserException.class);
    }

    @Test
    public void givenTestOrderDatabase_whenOrderItemIdDoesntMatchAnIdFromTheItemDatabase_ThenThrowInvalidItemException() {
        Assertions.assertThatThrownBy(() -> orderService.registerOrder(invalidOrder2DTO)).isInstanceOf(InvalidItemException.class);
    }

//    @Test
//    public void givenTestOrderDatabase_whenItemGroupIsNull_thenThrowInvalidItemGroupException(){
//        Assertions.assertThatThrownBy(() -> orderService.registerOrder(order)).isInstanceOf(InvalidItemGroupException.class);
//    }
//
//    @Test
//    public void givenTestOrderDatabase_whenItemNotInStock_thenSetShippingDatePlusSeven(){
//
//    }
//
//
//    @Test
//    public void givenTestOrderDatabase_whenItemInStock_thenSetShippingDatePlusOne(){
//
//    }


}
