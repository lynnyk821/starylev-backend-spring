package com.example.starylevbackendspring.controllers;

import com.example.starylevbackendspring.Book;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BooksController {
    @GetMapping(value = "/books")
    public Book getMyItem(){
        Book book = new Book();
        book.setName("123");
        return book;
    }
}
