package com.example.starylevbackendspring.repositories;

import com.example.starylevbackendspring.models.Book;
import com.example.starylevbackendspring.mapper.BookMap;
import com.example.starylevbackendspring.utils.QueriesSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "saved_books_data";
    private final String TYPE_NAME = "type_name";
    private final String NAME = "name";

    @Autowired
    public BooksRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Book> getBooksByQuery(String sql){
        return jdbcTemplate.query(sql, BookMap::mapBookFromResultSet);
    }

    public Integer getCountOfBooksByTypes(String[] types){
        QueriesSQL queriesSQL = new QueriesSQL(TABLE_NAME, TYPE_NAME);
        String sql = queriesSQL.getCountOfBooksByValues(types);
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<Book> getAllBooksFromTable() {
        QueriesSQL queriesSQL = new QueriesSQL(TABLE_NAME);
        String query = queriesSQL.selectAllFromTable();
        return getBooksByQuery(query);
    }

    public List<Book> getBooksByTypes(String[] types) {
        QueriesSQL queriesSQL = new QueriesSQL(TABLE_NAME, TYPE_NAME);
        String query = queriesSQL.selectItemsWhereColumnItemEqualsAll(types);
        return getBooksByQuery(query);
    }

    public List<Book> getBooksByName(String name){
        QueriesSQL queriesSQL = new QueriesSQL(TABLE_NAME, NAME);
        String query = queriesSQL.selectItemsWhereColumnItemLike(name);
        return getBooksByQuery(query);
    }
}
