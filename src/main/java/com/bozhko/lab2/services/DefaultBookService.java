package com.bozhko.lab2.services;

import com.bozhko.lab2.data.*;
import com.bozhko.lab2.exception.AuthorNotFoundException;
import com.bozhko.lab2.exception.BookInvalidArgumentException;
import com.bozhko.lab2.exception.BookNotFoundException;
import com.bozhko.lab2.repository.AuthorRepository;
import com.bozhko.lab2.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public DefaultBookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookResponse> getAll() {
        List<BookResponse> response = new ArrayList<>();
        for (Book book : bookRepository.getAll()){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setId(book.getId());
            bookResponse.setName(book.getName());
            bookResponse.setYear(book.getYear());
            List<Author> authors = new ArrayList<>();
            for (Long authorId: book.getAuthors()){
                Author author = authorRepository.get(authorId);
                if (author == null) continue;
                authors.add(author);
            }
            bookResponse.setAuthors(authors);
            response.add(bookResponse);
        }
        return response;
    }

    @Override
    public Long create(BookRequest bookRequest) {
        if (bookRequest.getYear() > 2022){
            log.error("Year of publication of the book is not possible");
            throw new BookInvalidArgumentException("Year of publication of the book is not possible");
        }
        final Book newBook = new Book();
        newBook.setName(bookRequest.getName());
        newBook.setYear(bookRequest.getYear());
        for (Long authorId: bookRequest.getAuthorsIds()){
            if (authorRepository.get(authorId)==null){
                log.error("Author with id = {} not found", authorId);
                throw new AuthorNotFoundException("Author with id = %d not found".formatted(authorId));
            }
        }
        newBook.setAuthors(bookRequest.getAuthorsIds());
        return bookRepository.create(newBook);
    }

    @Override
    public BookResponse get(Long id) {
        final Book book = bookRepository.get(id);
        if (book==null){
            log.error("Book with id = {} not found", id);
            throw new BookNotFoundException("Book with id = %d not found".formatted(id));
        }
        final BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setYear(book.getYear());
        List<Author> authors = new ArrayList<>();
        for (Long authorId: book.getAuthors()){
            Author author = authorRepository.get(authorId);
            if (author == null) continue;
            authors.add(author);
        }
        response.setAuthors(authors);
        return response;
    }

    @Override
    public boolean update(BookRequest bookRequest, Long id) {
        if (bookRequest.getYear() > 2022){
            log.error("Year of publication of the book is not possible");
            throw new BookInvalidArgumentException("Year of publication of the book is not possible");
        }
        final Book book = bookRepository.get(id);
        if (book==null){
            log.error("Book with id = {} not found", id);
            throw new BookNotFoundException("Book with id = %d not found".formatted(id));
        }
        final Book updatedBook = new Book();
        updatedBook.setName(bookRequest.getName());
        updatedBook.setYear(bookRequest.getYear());
        updatedBook.setAuthors(bookRequest.getAuthorsIds());
        return bookRepository.update(updatedBook, id);
    }

    @Override
    public boolean delete(Long id) {
        final Book book = bookRepository.get(id);
        if (book==null){
            log.error("Book with id = {} not found", id);
            throw new BookNotFoundException("Book with id = %d not found".formatted(id));
        }
        return bookRepository.delete(id);
    }
}
