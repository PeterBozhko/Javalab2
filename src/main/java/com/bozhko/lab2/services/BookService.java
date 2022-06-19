package com.bozhko.lab2.services;


import com.bozhko.lab2.data.Book;
import com.bozhko.lab2.data.BookRequest;
import com.bozhko.lab2.data.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> getAll();
    Long create(BookRequest bookRequest);

    BookResponse get(Long id);

    boolean update(BookRequest bookRequest, Long id);

    boolean delete(Long id);
}
