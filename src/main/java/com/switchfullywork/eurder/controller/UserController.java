package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.domain.userdto.UserResponse;
import com.switchfullywork.eurder.service.UserService;
import com.switchfullywork.eurder.switchsecure.SecurityGuard;
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
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public List<UserResponse> getAllCustomers(){
        logger.info("Retrieving the customerlist.");
        List<UserResponse> userResponseList = userService.getAllCustomers();
        logger.info(String.format("Retrieved %s customers.", userResponseList.size()));
        return userResponseList;
    }

    @GetMapping(produces = "application/json", path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public UserResponse findUserByUserId(@PathVariable(name = "userId") int userId){
        logger.info("Retrieving a customer.");
        UserResponse userResponse = userService.findUserByUserId(userId);
        logger.info(String.format("Retrieved customer with id %s.", userResponse.getUserId()));
        return userResponse;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@RequestBody CreateUserRequest user) {
        logger.info("Registering new user.");
        userService.registerCustomer(user);
        logger.info("New user registered.");
    }

}
