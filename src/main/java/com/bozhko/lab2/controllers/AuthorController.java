package com.bozhko.lab2.controllers;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.AuthorRequest;
import com.bozhko.lab2.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces="application/json")
@RequiredArgsConstructor
public class AuthorController {
    final private AuthorService authorService;

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAuthors(){
        log.info("GET request all authors");
        final List<Author> authors = authorService.getAll();
        return  authors != null &&  !authors.isEmpty()
                ? new ResponseEntity<>(authors, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> read(@PathVariable(name = "id") int id) {
        log.info("Get request author by id = %d".formatted(id));
        final Author author = authorService.get((long) id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
    @PostMapping("/author")
    public ResponseEntity<?> create(@RequestBody AuthorRequest authorRequest) {
        log.info("POST request create author");
        Long id = authorService.create(authorRequest);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody AuthorRequest authorRequest) {
        log.info("PUT request new author by id = %d".formatted(id));
        final boolean updated = authorService.update(authorRequest, (long) id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        log.info("DELETE author by id = %d".formatted(id));
        final boolean deleted = authorService.delete((long) id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
