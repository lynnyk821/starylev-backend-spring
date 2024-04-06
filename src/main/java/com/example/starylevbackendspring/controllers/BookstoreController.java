package com.example.starylevbackendspring.controllers;

import com.example.starylevbackendspring.utils.JsonUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BookstoreController {
    private static final String PATH = "static/data/books.json";
    @GetMapping(value = "/books")
    public ResponseEntity<String> getBooks() throws IOException {
        return JsonUtility.getJson(PATH);
    }
}
