package com.bozhko.lab2.repository;

import com.bozhko.lab2.data.Author;

import java.util.List;
public interface AuthorRepository {
    List<Author> getAll();
    Author get(Long id);
    void add(Author author);
    void update(Author author);
    void remove(Author author);
}
