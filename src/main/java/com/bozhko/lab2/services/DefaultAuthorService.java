package com.bozhko.lab2.services;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.AuthorRequest;
import com.bozhko.lab2.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultAuthorService implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public void addAuthor(AuthorRequest newAuthor) {
        Author author = new Author();
        author.setFirstName(newAuthor.getFirstName());
        author.setSecondName(newAuthor.getLastName());
        author.setYear(newAuthor.getYear());
        authorRepository.add(author);
    }
}
