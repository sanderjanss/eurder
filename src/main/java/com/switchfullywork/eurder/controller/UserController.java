package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.user.User;
import com.switchfullywork.eurder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllCustomers(@RequestHeader(value = "adminId", required = false) UUID adminId,
                                      @RequestParam(required = false) UUID memberId){
        return userService.getAllCustomers(adminId, memberId);
    }
}
