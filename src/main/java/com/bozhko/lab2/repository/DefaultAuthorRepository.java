package com.bozhko.lab2.repository;

import com.bozhko.lab2.data.Author;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DefaultAuthorRepository implements AuthorRepository {
    private final Map<Long, Author> authorRepositoryMap = new HashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    @Override
    public List<Author> getAll() {
        return new ArrayList<>(authorRepositoryMap.values());
    }

    @Override
    public Author get(Long id) {
        return authorRepositoryMap.get(id);
    }

    @Override
    public Long create(Author author) {
        final long authorId = ID_GENERATOR.incrementAndGet();
        author.setId(authorId);
        authorRepositoryMap.put(authorId, author);
        return authorId;
    }

    @Override
    public boolean update(Author author, Long id) {
        if (authorRepositoryMap.containsKey(id)) {
            author.setId(id);
            authorRepositoryMap.put(id, author);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return authorRepositoryMap.remove(id) != null;
    }

}
