package com.bozhko.lab2.controllers;


import com.bozhko.lab2.data.Book;
import com.bozhko.lab2.data.BookRequest;
import com.bozhko.lab2.data.BookResponse;
import com.bozhko.lab2.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces="application/json")
@RequiredArgsConstructor
public class BookController {
    final private BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<List<BookResponse>> getBooks(){
        log.info("GET request all books");
        final List<BookResponse> books = bookService.getAll();
        return  books != null &&  !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponse> read(@PathVariable(name = "id") int id) {
        log.info("GET request book with id = {}",id);
        final BookResponse book = bookService.get((long) id);
        return new  ResponseEntity<>(book, HttpStatus.OK);
    }
    @PostMapping("/book")
    public ResponseEntity<?> create(@RequestBody BookRequest bookRequest) {
        log.info("POST request create book");
        Long id = bookService.create(bookRequest);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody BookRequest bookRequest) {
        log.info("PUT new book by id = {}",id);
        final boolean updated = bookService.update(bookRequest, (long) id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        log.info("DELETE book by id = {}",id);
        final boolean deleted = bookService.delete((long) id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
