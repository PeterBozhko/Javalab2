package com.bozhko.lab2.data;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
final public class Author {
    private Long id;


    private String firstName;
    private String lastName;
    private Integer year;
}
