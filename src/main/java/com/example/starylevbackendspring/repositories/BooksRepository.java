package com.example.starylevbackendspring.repositories;

import com.example.starylevbackendspring.mapper.CartItemMap;
import com.example.starylevbackendspring.models.Book;
import com.example.starylevbackendspring.mapper.BookMap;
import com.example.starylevbackendspring.models.CartItem;
import com.example.starylevbackendspring.utils.QueriesSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BOOKS_TABLE_NAME = "saved_books_data";
    private final String CART_TABLE_NAME = "cart";
    private final String[] COLUMN_NAMES = new String[] {
            "type_name", "name", "author_name", "author_surname"
    };

    @Autowired
    public BooksRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Book> getBooksByQuery(String sql){
        return jdbcTemplate.query(sql, BookMap::mapBookFromResultSet);
    }

    private List<CartItem> getCartItemsByQuery(String sql){
        return jdbcTemplate.query(sql, CartItemMap::mapBookFromResultSet);
    }

    public Book getBookById(String id){
        QueriesSQL queriesSQL = new QueriesSQL(BOOKS_TABLE_NAME, "book_id");
        String sql = queriesSQL.getBookById(id);
        return getBooksByQuery(sql).get(0);
    }

    public Integer getCountOfBooksByTypes(String[] types){
        QueriesSQL queriesSQL = new QueriesSQL(BOOKS_TABLE_NAME, COLUMN_NAMES[0]);
        String sql = queriesSQL.getCountOfBooksByValues(types);
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<Book> getAllBooksFromTable() {
        QueriesSQL queriesSQL = new QueriesSQL(BOOKS_TABLE_NAME);
        String query = queriesSQL.selectAllFromTable();
        return getBooksByQuery(query);
    }

    public List<CartItem> getAllCartItemsFromTable() {
        QueriesSQL queriesSQL = new QueriesSQL(CART_TABLE_NAME);
        String query = queriesSQL.selectAllFromTable();
        return getCartItemsByQuery(query);
    }

    public void insertBookIntoCart(Book book){
        QueriesSQL queriesSQL = new QueriesSQL(CART_TABLE_NAME);
        String query = queriesSQL.insertBookIntoTable();
        jdbcTemplate.update(query, book.getId(), book.getType(), book.getName(), book.getAuthorName(), book.getAuthorSurname(), book.getPrice());
    }

    public CartItem getCartItemById(String id){
        QueriesSQL queriesSQL = new QueriesSQL(CART_TABLE_NAME, "cart_id");
        String query = queriesSQL.selectItemsWhereColumnItemEquals(id);
        return getCartItemsByQuery(query).get(0);
    }

    public List<Book> getBooksByTypes(String[] types) {
        QueriesSQL queriesSQL = new QueriesSQL(BOOKS_TABLE_NAME, COLUMN_NAMES[0]);
        String query = queriesSQL.selectItemsWhereColumnItemEqualsAll(types);
        return getBooksByQuery(query);
    }

    public List<Book> getBooksByValue(String value){
        QueriesSQL queriesSQL = new QueriesSQL(BOOKS_TABLE_NAME);
        String query = queriesSQL.selectForSearchBooks(COLUMN_NAMES, value);
        return getBooksByQuery(query);
    }
}
