package com.bozhko.lab2.repository;

import com.bozhko.lab2.data.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();
    Book get(Long id);
    Long create(Book book);
    boolean update(Book book, Long id);
    boolean delete(Long id);
}
