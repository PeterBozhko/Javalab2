package com.bozhko.lab2.services;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.AuthorRequest;
import com.bozhko.lab2.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Long create(AuthorRequest newAuthor) {
        final Author author = new Author();
        author.setFirstName(newAuthor.getFirstName());
        author.setLastName(newAuthor.getLastName());
        author.setYear(newAuthor.getYear());
        return authorRepository.create(author);
    }

    @Override
    public Author get(Long id) {
        return authorRepository.get(id);
    }

    @Override
    public boolean update(AuthorRequest author, Long id) {
        final Author updatedAuthor = new Author();
        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setLastName(author.getLastName());
        updatedAuthor.setYear(author.getYear());
        return authorRepository.update(updatedAuthor, id);
    }

    @Override
    public boolean delete(Long id) {
        return  authorRepository.delete(id);
    }
}
