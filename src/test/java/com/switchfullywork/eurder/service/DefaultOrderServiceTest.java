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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class DefaultOrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
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
//        orderRepository = new DefaultOrderRepository();
//        userRepository = new DefaultUserRepository();
//        itemRepository = new DefaultItemRepository();
//        orderService = new DefaultOrderService(orderRepository, orderMapper, userRepository, itemRepository);

        invalidCustomer = new User.UserBuilder()
                .setFirstName("NoCustomer")
                .setLastName("NoCustomer")
                .setEmailAddress("NoCustomer@eurder.com")
                .setPhoneNumber("0411111111")
                .setRole(Role.CUSTOMER)
                .setAddress(Address.builder()
                        .streetName("NoCustomerStreet")
                        .houseNumber(1)
                        .postalCode("1000")
                        .city("Brussels")
                        .build())
                .build();
        validCustomer = new User.UserBuilder()
                .setFirstName("TestCustomer")
                .setLastName("TestCustomer")
                .setEmailAddress("TestCustomer@eurder.com")
                .setPhoneNumber("0422222222")
                .setRole(Role.CUSTOMER)
                .setAddress(Address.builder()
                        .streetName("CustomerStreet")
                        .houseNumber(1)
                        .postalCode("1000")
                        .city("Brussels")
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

        validItemGroupDTO1 = CreateItemGroupDTO.builder()
                .itemId(validItem.getItemId())
                .amount(5)
                .shippingDate(LocalDate.now())
                .build();
        validItemGroupDTO2 = CreateItemGroupDTO.builder()
                .itemId(validItem2.getItemId())
                .amount(5)
                .shippingDate(LocalDate.now())
                .build();

        invalidItemGroupDTO = CreateItemGroupDTO.builder()
                .itemId(UUID.randomUUID())
                .amount(5)
                .shippingDate(LocalDate.now())
                .build();

        validOrderDTO = CreateOrderDTO.builder()
                .customerId(validCustomer.getUserId())
                .listOfItemGroups(List.of(validItemGroupDTO1, validItemGroupDTO2))
                .build();

        invalidOrder1DTO = CreateOrderDTO.builder()
                .customerId(invalidCustomer.getUserId())
                .listOfItemGroups(List.of(validItemGroupDTO1))
                .build();

        invalidOrder2DTO = CreateOrderDTO.builder()
                .customerId(validCustomer.getUserId())
                .listOfItemGroups(List.of(invalidItemGroupDTO))
                .build();

        orderRepository.registerOrder(orderMapper.toOrder(validOrderDTO));
    }

    @Test
    @DisplayName("Invalid order when registered order is null.")
    public void givenTestOrderDatabase_whenRegisteredOrderIsNull_ThenThrowInvalidOrderException() {
        Assertions.assertThatThrownBy(() ->
                orderService.registerOrder(null)).isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("Invalid user when using an invalid user id.")
    public void givenTestOrderDatabase_whenOrderCustomerIdDoesntMatchAnIdFromTheUserDatabase_ThenThrowInvalidUserException() {
        Assertions.assertThatThrownBy(() ->
                orderService.registerOrder(invalidOrder1DTO)).isInstanceOf(InvalidUserException.class);
    }

    @Test
    @DisplayName("Invalid item when using an invalid item id.")
    public void givenTestOrderDatabase_whenOrderItemIdDoesntMatchAnIdFromTheItemDatabase_ThenThrowInvalidItemException() {
        Assertions.assertThatThrownBy(() ->
                orderService.registerOrder(invalidOrder2DTO)).isInstanceOf(InvalidItemException.class);
    }

    @Test
    @DisplayName("Invalid user when retrieving an order by invalid user id.")
    public void givenTestOrderDataBase_whenFindingAnOrderByInvalidUserId_thenThrowNewInvalidUserException(){
        Assertions.assertThatThrownBy(() ->
                orderService.getOrder(UUID.randomUUID())).isInstanceOf(InvalidUserException.class);
    }


//    @Test
//    public void givenTestOrderDataBase_whenFindingAnOrderByValidUserId_thenReturnNewOrder(){
//        Assertions.assertThat(orderService
//                .getOrder(validCustomer.getUserId()))
//                .isEqualTo(orderMapper.toOrderDTO(orderRepository.getOrder(validCustomer.getUserId())));
//    }

}
