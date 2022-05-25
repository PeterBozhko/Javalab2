package com.bozhko.lab2.controllers;

import com.bozhko.lab2.data.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(produces="application/json")
public class UsersController {

    @GetMapping("/users")
    public ArrayList<User> getUsers(){
        ArrayList<User> arrayList = new ArrayList<>();
        User userOne = new User();
        userOne.setId("1");
        userOne.setName("PersonOne");
        userOne.setData("FamilyOne");

        User userTwo = new User();
        userTwo.setId("2");
        userTwo.setName("PersonTwo");
        userTwo.setData("FamilyOne");

        arrayList.add(userOne);
        arrayList.add(userTwo);
        return arrayList;
    }
}
