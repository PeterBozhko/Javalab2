package com.bozhko.lab2.service;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.AuthorRequest;
import com.bozhko.lab2.exception.AuthorInvalidArgumentException;
import com.bozhko.lab2.exception.AuthorNotFoundException;
import com.bozhko.lab2.repository.AuthorRepository;
import com.bozhko.lab2.services.AuthorService;
import com.bozhko.lab2.services.DefaultAuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AuthorServiceTest {
    private AuthorService service;
    private AuthorRepository authorRepository;
    @BeforeEach
    public void init() {
        this.authorRepository = mock(AuthorRepository.class);
        this.service = new DefaultAuthorService(authorRepository);
    }

    @Test
    public void testSaveAuthor() {
        AuthorRequest authorRequest = AuthorRequest.builder()
                .firstName("Jack")
                .lastName("Rudolf")
                .year(1987)
                .build();

        final Author author = new Author();
        author.setFirstName("Jack");
        author.setLastName("Rudolf");
        author.setYear(1987);

        when(authorRepository.create(any())).thenReturn(1L);
        when(authorRepository.get(any())).thenReturn(author);

        Long id = service.create(authorRequest);

        Author result = service.get(id);
        assertNotNull(result);
        assertEquals(author.getFirstName(), result.getFirstName());
        assertEquals(author.getLastName(), result.getLastName());
        assertEquals(author.getYear(), result.getYear());
    }

    @Test
    public void testUpdateAuthor() {
        AuthorRequest authorRequest = AuthorRequest.builder()
                .firstName("Jack")
                .lastName("Rudolf")
                .year(1987)
                .build();

        AuthorRequest authorRequestNew = AuthorRequest.builder()
                .firstName("Jack")
                .lastName("Rudolf")
                .year(1988)
                .build();

        final Author author = new Author();
        author.setFirstName("Jack");
        author.setLastName("Rudolf");
        author.setYear(1988);

        when(authorRepository.create(any())).thenReturn(1L);
        when(authorRepository.update(any(), any())).thenReturn(true);
        when(authorRepository.get(any())).thenReturn(author);

        Long id = service.create(authorRequest);
        service.update(authorRequestNew, id);

        Author result = service.get(id);
        assertNotNull(result);
        assertEquals(author.getFirstName(), result.getFirstName());
        assertEquals(author.getLastName(), result.getLastName());
        assertEquals(author.getYear(), result.getYear());
    }

    @Test
    public void testDeleteAuthor() {
        AuthorRequest authorRequest = AuthorRequest.builder()
                .firstName("Jack")
                .lastName("Rudolf")
                .year(1987)
                .build();

        final Author author = new Author();
        author.setFirstName("Jack");
        author.setLastName("Rudolf");
        author.setYear(1987);

        when(authorRepository.create(any())).thenReturn(1L);
        when(authorRepository.get(any())).thenReturn(author);

        Long id = service.create(authorRequest);
        service.delete(id);
        verify(authorRepository, times(1)).delete(any());
    }

    @Test
    public void testGetByAuthorId() {
        AuthorRequest authorRequest = AuthorRequest.builder()
                .firstName("Jack")
                .lastName("Rudolf")
                .year(1987)
                .build();

        final Author author = new Author();
        author.setFirstName("Jack");
        author.setLastName("Rudolf");
        author.setYear(1988);

        when(authorRepository.create(any())).thenReturn(1L);
        when(authorRepository.get(any())).thenReturn(author);

        Long id = service.create(authorRequest);

        Author result = service.get(id);
        assertNotNull(result);
        assertEquals(author.getFirstName(), result.getFirstName());
        assertEquals(author.getLastName(), result.getLastName());
        assertEquals(author.getYear(), result.getYear());
    }

    @Test
    public void testGetByAuthorIdException() {
        AuthorRequest authorRequest = AuthorRequest.builder()
                .firstName("Jack")
                .lastName("Rudolf")
                .year(1987)
                .build();

        final Author author = new Author();
        author.setFirstName("Jack");
        author.setLastName("Rudolf");
        author.setYear(1988);

        when(authorRepository.create(any())).thenReturn(1L);
        when(authorRepository.get(any())).thenReturn(null);

        Long id = service.create(authorRequest);
        String errorMessage = "Author with id = %d not found".formatted(id);
        Exception exception = assertThrows(AuthorNotFoundException.class, () -> service.get(id));
        String resultMessage = exception.getMessage();
        assertTrue(resultMessage.contains(errorMessage));
        verify(authorRepository, times(1)).get(any());
    }

    @Test
    public void testInvalidArgumentException() {
        AuthorRequest authorRequest = AuthorRequest.builder()
                .firstName("Jack")
                .lastName("Rudolf")
                .year(205012)
                .build();

        String errorMessage = "Year of birth of the author is impossible";
        Exception exception = assertThrows(AuthorInvalidArgumentException.class, () -> service.create(authorRequest));
        String resultMessage = exception.getMessage();
        assertTrue(resultMessage.contains(errorMessage));
    }

}
