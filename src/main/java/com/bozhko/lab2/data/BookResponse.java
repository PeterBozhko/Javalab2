package com.bozhko.lab2.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
final public class BookResponse {
    private Long id;

    private String name;
    private List<Author> authors;
    private Integer year;
}
