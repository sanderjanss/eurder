package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.user.CreateUserDTO;
import com.switchfullywork.eurder.domain.user.UserDTO;
import com.switchfullywork.eurder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService mapUserService) {
        this.userService = mapUserService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllCustomers(@RequestHeader(value = "adminId", required = false) UUID adminId,
                                         @RequestParam(value = "memberId", required = false) UUID memberId) {
        logger.info("Retrieving the customerlist.");
        List<UserDTO> userDTOList = userService.getAllCustomers(adminId, memberId);
        logger.info(String.format("Retrieved %s customers.", userDTOList.size()));
        return userDTOList;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@RequestBody CreateUserDTO user) {
        logger.info("Registering new user.");
        userService.registerCustomer(user);
        logger.info("New user registered.");
    }

}
