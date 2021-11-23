package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.entity.order.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAllByOrderId(int customerId);
}
