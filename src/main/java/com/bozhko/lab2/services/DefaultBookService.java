package com.bozhko.lab2.services;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.Book;
import com.bozhko.lab2.data.BookRequest;
import com.bozhko.lab2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultBookService implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Long create(BookRequest bookRequest) {
        final Book book = new Book();
        book.setName(bookRequest.getName());
        book.setYear(bookRequest.getYear());
        List<Author> authors = new ArrayList<>();
        for (Long authorId: bookRequest.getAuthorsIds()){
            authors.add(authorService.get(authorId));
        }
        book.setAuthors(authors);
        return bookRepository.create(book);
    }

    @Override
    public Book get(Long id) {
        return bookRepository.get(id);
    }

    @Override
    public boolean update(BookRequest bookRequest, Long id) {
        final Book book = new Book();
        book.setName(bookRequest.getName());
        book.setYear(bookRequest.getYear());
        List<Author> authors = new ArrayList<>();
        for (Long authorId: bookRequest.getAuthorsIds()){
            authors.add(authorService.get(authorId));
        }
        book.setAuthors(authors);
        return bookRepository.update(book, id);
    }

    @Override
    public boolean delete(Long id) {
        return bookRepository.delete(id);
    }
}
