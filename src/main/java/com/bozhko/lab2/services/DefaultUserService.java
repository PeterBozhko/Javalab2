package com.bozhko.lab2.services;

import com.bozhko.lab2.data.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DefaultUserService implements UserService {
    @Override
    public List<User> getAll() {
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
