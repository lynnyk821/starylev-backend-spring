package com.example.starylevbackendspring.controllers;

import com.example.starylevbackendspring.modules.Book;

import com.example.starylevbackendspring.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return repository.getAllBooks();
    }

    @GetMapping(value = "/books/type/all")
    public List<Book> getALlTypeBooks() {
        return repository.getAllBooks();
    }

    @GetMapping(value = "/books/type/paper")
    public List<Book> getPaperTypeBooks() {
        return repository.getBooksByType("Паперова");
    }

    @GetMapping(value = "/books/type/package")
    public List<Book> getPackageTypeBooks() {
        return repository.getBooksByType("Набори");
    }

    @GetMapping(value = "/books/id={id}")
    public Book getBooksByName(@PathVariable String id){
        return repository.getBookByName(id);
    }
}
