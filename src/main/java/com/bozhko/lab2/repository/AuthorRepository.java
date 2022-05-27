package com.bozhko.lab2.repository;

import com.bozhko.lab2.data.Author;

import java.util.List;
public interface AuthorRepository {
    List<Author> getAll();
    Author get(Long id);
    Long create(Author author);
    boolean update(Author author, Long id);
    boolean delete(Long id);
}
