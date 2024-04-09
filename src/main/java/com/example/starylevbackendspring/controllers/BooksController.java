package com.example.starylevbackendspring.controllers;

import com.example.starylevbackendspring.modules.Book;
import com.example.starylevbackendspring.services.BooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BooksController {
    private final BooksService booksService;
    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }
    @GetMapping(value = "/books")
    public List<Book> getBooks() {
        return booksService.getAllBooks();
    }
}
