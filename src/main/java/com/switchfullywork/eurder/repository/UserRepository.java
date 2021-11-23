package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUserId(int id);

    List<User>findAll();

    User findUserByEmailAddress(String email);
}
