package com.bozhko.lab2.services;


import com.bozhko.lab2.data.Book;
import com.bozhko.lab2.data.BookRequest;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Long create(BookRequest bookRequest);

    Book get(Long id);

    boolean update(BookRequest bookRequest, Long id);

    boolean delete(Long id);
}
