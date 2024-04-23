package com.example.starylevbackendspring.repository;

import com.example.starylevbackendspring.modules.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Book> getBooksByQuery(String sql){
        return jdbcTemplate.query(sql, BookMap::mapBookFromResultSet);
    }

    public List<Book> getAllBooks() {
        return getBooksByQuery("SELECT * FROM books");
    }

    public List<Book> getBooksByType(String type) {
        return getBooksByQuery(String.format("SELECT * FROM books WHERE type = '%s'", type));
    }

    public Book getBookByName(String id){
        return getBooksByQuery(String.format("SELECT * FROM books WHERE id = '%s'", id)).get(0);
    }
}
