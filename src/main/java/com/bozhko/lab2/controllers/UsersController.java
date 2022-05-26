package com.bozhko.lab2.controllers;

import com.bozhko.lab2.data.User;
import com.bozhko.lab2.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces="application/json")
@RequiredArgsConstructor
public class UsersController {
    final private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        log.info("Request getAll");
        return userService.getAll();
    }
    @PostMapping("/users")
    public String createUser() {
        return "HTTP Post";
    }

    @DeleteMapping("/users")
    public String deleteUser(){
        return "HTTP Delete";
    }
}
