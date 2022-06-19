package com.bozhko.lab2.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
final public class Book {
    private Long id;

    private String name;
    private List<Long> authors;
    private Integer year;
}
