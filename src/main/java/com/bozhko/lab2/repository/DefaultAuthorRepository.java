package com.bozhko.lab2.repository;

import com.bozhko.lab2.data.Author;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DefaultAuthorRepository implements AuthorRepository {
    private final List<Author> list = new ArrayList<>();
    @Override
    public List<Author> getAll() {
        return list;
    }

    @Override
    public Author get(Long id) {
        return null;
    }

    @Override
    public void add(Author author) {
        list.add(author);
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void remove(Author author) {

    }
}
