package com.bozhko.lab2.service;

import com.bozhko.lab2.data.*;
import com.bozhko.lab2.exception.AuthorInvalidArgumentException;
import com.bozhko.lab2.exception.AuthorNotFoundException;
import com.bozhko.lab2.repository.AuthorRepository;
import com.bozhko.lab2.repository.BookRepository;
import com.bozhko.lab2.services.BookService;
import com.bozhko.lab2.services.DefaultAuthorService;
import com.bozhko.lab2.services.DefaultBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BookServiceTest {
    private BookService service;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    @BeforeEach
    public void init() {
        this.bookRepository = mock(BookRepository.class);
        this.authorRepository = mock(AuthorRepository.class);
        this.service = new DefaultBookService(bookRepository, authorRepository);
    }

    @Test
    public void testSaveBook() {
        List<Long> authorsIds = new ArrayList<>();
        authorsIds.add(1L);
        BookRequest bookRequest = BookRequest.builder()
                .name("Best book")
                .authorsIds(authorsIds)
                .year(1999)
                .build();

        final Book book = new Book();
        book.setId(1L);
        book.setName("Best book");
        book.setYear(1999);
        book.setAuthors(authorsIds);

        final Author author = new Author();
        author.setFirstName("Jack");
        author.setLastName("Rudolf");
        author.setYear(1987);

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        when(bookRepository.create(any())).thenReturn(1L);
        when(bookRepository.get(any())).thenReturn(book);
        when(authorRepository.get(any())).thenReturn(author);

        Long id = service.create(bookRequest);

        BookResponse result = service.get(id);
        assertNotNull(result);
        assertEquals(bookRequest.getName(), result.getName());
        assertEquals(bookRequest.getYear(), result.getYear());
        assertEquals(authors.get(0), result.getAuthors().get(0));
    }
}
