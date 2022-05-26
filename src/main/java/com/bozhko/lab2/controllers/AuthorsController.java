package com.bozhko.lab2.controllers;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.AuthorRequest;
import com.bozhko.lab2.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces="application/json")
@RequiredArgsConstructor
public class AuthorsController {
    final private AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAuthors(){
        log.info("Request GET");
        return authorService.getAll();
    }
    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAuthor(@Validated @RequestBody AuthorRequest newAuthor) {
        log.info("Request POST");
        authorService.addAuthor(newAuthor);
        return "HTTP Post";
    }

    @DeleteMapping("/users")
    public String deleteAuthor(){
        return "HTTP Delete";
    }
}
