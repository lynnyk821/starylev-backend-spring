package com.example.starylevbackendspring.mapper;

import com.example.starylevbackendspring.models.Book;
import com.example.starylevbackendspring.models.CartItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMap {
    public static CartItem mapBookFromResultSet(ResultSet resultSet, int index) {
        CartItem book = new CartItem();

        try {
            book.setId(resultSet.getLong("cart_id"));

            book.setType(resultSet.getString("book_type"));
            book.setName(resultSet.getString("book_name"));
            book.setPrice(resultSet.getFloat("book_price"));

            book.setAuthorName(resultSet.getString("book_author_name"));
            book.setAuthorSurname(resultSet.getString("book_author_surname"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return book;
    }
}
