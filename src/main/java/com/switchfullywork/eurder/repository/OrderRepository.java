package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.entity.order.Order;
import com.switchfullywork.eurder.domain.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAllByUser(User user);

    List<Order> findAll();
}
