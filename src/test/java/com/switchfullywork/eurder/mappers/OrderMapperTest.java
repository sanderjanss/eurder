package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.item.CreateItemGroupDTO;
import com.switchfullywork.eurder.domain.item.Item;
import com.switchfullywork.eurder.domain.order.CreateOrderDTO;
import com.switchfullywork.eurder.domain.order.Order;
import com.switchfullywork.eurder.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class OrderMapperTest {

    private CreateOrderDTO validOrderDTO;
    private CreateItemGroupDTO validItemGroupDTO1;
    private CreateItemGroupDTO validItemGroupDTO2;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemRepository itemRepository;


    @BeforeEach
    void setUp() {

        Item validItem = new Item.ItemBuilder()
                .setName("Dog")
                .setDescription("A pluchy dog")
                .setAmountStock(100)
                .setPrice(20)
                .build();
        Item validItem2 = new Item.ItemBuilder()
                .setName("Cat")
                .setDescription("A pluchy cat")
                .setAmountStock(4)
                .setPrice(30)
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

        validOrderDTO = CreateOrderDTO.builder()
                .customerId(UUID.randomUUID())
                .listOfItemGroups(List.of(validItemGroupDTO1, validItemGroupDTO2))
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Calculate the total price of the itemgroups.")
    public void givenAListOfItemGroups_calculateTotalPrice() {
        Order order = orderMapper.toOrder(validOrderDTO);
        Assertions.assertThat(orderMapper.calculateTotalPrice(order.getListOfItemGroups())).isEqualTo(250);
    }

    @Test
    @DisplayName("Count 1 day to the shippingdate when enough items in stock.")
    public void givenAItemGroup_whenEnoughItemsInStock_thenReturnDateIsPlus1() {
        Assertions.assertThat(orderMapper.calculateShippingDate(validItemGroupDTO1)).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    @DisplayName("Count 7 days to the shippingdate when not enough items in stock.")
    public void givenAItemGroup_whenNotEnoughItemsInStock_thenReturnDateIsPlus7() {
        Assertions.assertThat(orderMapper.calculateShippingDate(validItemGroupDTO2)).isEqualTo(LocalDate.now().plusDays(7));
    }

}