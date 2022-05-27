package com.bozhko.lab2.repository;

import com.bozhko.lab2.data.Author;
import com.bozhko.lab2.data.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DefaultBookRepository implements BookRepository {

    private final Map<Long, Book> bookRepositoryMap = new HashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    @Override
    public List<Book> getAll() {
        return new ArrayList<>(bookRepositoryMap.values());
    }

    @Override
    public Book get(Long id) {
        return bookRepositoryMap.get(id);
    }

    @Override
    public Long create(Book book) {

        final long bookID = ID_GENERATOR.incrementAndGet();
        book.setId(bookID);
        bookRepositoryMap.put(bookID, book);
        return bookID;
    }

    @Override
    public boolean update(Book book, Long id) {
        if (bookRepositoryMap.containsKey(id)){
            book.setId(id);
            bookRepositoryMap.put(id, book);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return bookRepositoryMap.remove(id) != null;
    }
}
