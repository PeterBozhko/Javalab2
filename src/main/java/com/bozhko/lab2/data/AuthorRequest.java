package com.bozhko.lab2.data;


import lombok.Getter;
import lombok.NonNull;

@Getter
public class AuthorRequest {
    private String firstName;
    private String lastName;
    private Integer year;
}
