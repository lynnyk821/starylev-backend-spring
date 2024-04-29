package com.example.starylevbackendspring.controllers;

import com.example.starylevbackendspring.models.Book;

import com.example.starylevbackendspring.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BooksController {
    private final BooksRepository repository;

    @Autowired
    public BooksController(BooksRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/books")
    public List<Book> getBooks() {
        return repository.getAllBooksFromTable();
    }

    @GetMapping(value = "/books/types={data}")
    public List<Book> getBooksByTypes(@PathVariable String data) {
        if(data.equals("all")) return getBooks();

        String[] types = getParamsFromString(data);
        return repository.getBooksByTypes(types);
    }

    @GetMapping(value = "books/types={data}/count")
    public Integer getCountOfBooksByType(@PathVariable String data){
        if(data.equals("all")) return getBooks().size();

        String[] types = getParamsFromString(data);
        return repository.getCountOfBooksByTypes(types);
    }

    @GetMapping(value = "books/search={value}")
    public List<Book> searchBooksByValue(@PathVariable String value){
        return repository.getBooksByValue(value);
    }

    @GetMapping(value = "books/id={id}")
    public Book getBookById(@PathVariable String id){
        return repository.getBookById(id);
    }

    private String[] getParamsFromString(String data){
        String[] allTypes = data.split("&");
        HashSet<String> setOfTypes = new HashSet<>(allTypes.length);
        setOfTypes.addAll(Arrays.asList(allTypes));
        return setOfTypes.toArray(new String[0]);
    }
}
