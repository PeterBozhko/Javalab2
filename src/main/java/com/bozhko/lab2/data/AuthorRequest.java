package com.bozhko.lab2.data;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
final public class AuthorRequest {
    private String firstName;
    private String lastName;
    private Integer year;
}
