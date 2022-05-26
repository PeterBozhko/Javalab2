package com.bozhko.lab2.services;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.AuthorRequest;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    void addAuthor(AuthorRequest newAuthor);
}
