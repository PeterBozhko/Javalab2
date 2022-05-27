package com.bozhko.lab2.data;

import lombok.Getter;

import java.util.List;
@Getter
public class BookRequest {
    private String name;
    private List<Long> authorsIds;
    private Integer year;
}
