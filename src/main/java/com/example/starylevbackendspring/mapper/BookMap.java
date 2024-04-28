package com.example.starylevbackendspring.mapper;

import com.example.starylevbackendspring.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMap {
    public static Book mapBookFromResultSet(ResultSet resultSet, int index) {
        Book book = new Book();

        try {
            book.setId(resultSet.getLong("book_id"));

            book.setType(resultSet.getString("type_name"));
            book.setName(resultSet.getString("name"));
            book.setPrice(resultSet.getFloat("price"));

            book.setAuthorName(resultSet.getString("author_name"));
            book.setAuthorSurname(resultSet.getString("author_surname"));

            book.setIconUrl(resultSet.getString("image_url"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return book;
    }
}
