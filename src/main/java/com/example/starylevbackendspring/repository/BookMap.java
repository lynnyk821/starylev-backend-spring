package com.example.starylevbackendspring.repository;

import com.example.starylevbackendspring.modules.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMap {
    public static Book mapBookFromResultSet(ResultSet resultSet, int index) {
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
