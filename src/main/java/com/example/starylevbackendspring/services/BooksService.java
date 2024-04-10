package com.example.starylevbackendspring.services;

import com.example.starylevbackendspring.modules.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
public class BooksService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return getBooksByQuery("SELECT * FROM books");
    }

    public List<Book> getBooksByType(String type) {
        return getBooksByQuery(String.format("SELECT * FROM books WHERE type = '%s'", type));
    }

    private List<Book> getBooksByQuery(String sql){
        return jdbcTemplate.query(sql, this::mapBookFromResultSet);
    }

    private Book mapBookFromResultSet(ResultSet resultSet, int index) {
        Book book = new Book();
        try {
            book.setId(resultSet.getLong("id"));
            book.setType(resultSet.getString("type"));
            book.setAuthor(resultSet.getString("author"));
            book.setName(resultSet.getString("name"));
            book.setPrice(resultSet.getFloat("price"));
            book.setImagePath(resultSet.getString("image_path"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }
}
