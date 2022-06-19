package com.bozhko.lab2.services;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.AuthorRequest;
import com.bozhko.lab2.exception.AuthorInvalidArgumentException;
import com.bozhko.lab2.exception.AuthorNotFoundException;
import com.bozhko.lab2.repository.AuthorRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.ConstructorProperties;
import java.util.List;
@Service
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public Long create(AuthorRequest newAuthor) {
        if (newAuthor.getYear() > 2022){
            throw new AuthorInvalidArgumentException("Year of birth of the author is impossible");
        }
        final Author author = new Author();
        author.setFirstName(newAuthor.getFirstName());
        author.setLastName(newAuthor.getLastName());
        author.setYear(newAuthor.getYear());
        return authorRepository.create(author);
    }

    @Override
    public Author get(Long id) {
        Author author = authorRepository.get(id);
        if (author==null){
            throw new AuthorNotFoundException("Author with id = %d not found".formatted(id));
        }
        return author;
    }

    @Override
    public boolean update(AuthorRequest author, Long id) {
        Author oldAuthor = authorRepository.get(id);
        if (oldAuthor==null){
            throw new AuthorNotFoundException("Author with id = %d not found".formatted(id));
        }
        if (author.getYear() > 2022){
            throw new AuthorInvalidArgumentException("Year of birth of the author is impossible");
        }
        final Author updatedAuthor = new Author();
        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setLastName(author.getLastName());
        updatedAuthor.setYear(author.getYear());
        return authorRepository.update(updatedAuthor, id);
    }

    @Override
    public boolean delete(Long id) {
        Author author = authorRepository.get(id);
        if (author==null){
            throw new AuthorNotFoundException("Author with id = %d not found".formatted(id));
        }
        return  authorRepository.delete(id);
    }
}
