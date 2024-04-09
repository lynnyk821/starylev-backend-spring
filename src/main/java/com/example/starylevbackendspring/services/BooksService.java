package com.example.starylevbackendspring.services;

import com.example.starylevbackendspring.modules.Book;
import com.example.starylevbackendspring.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository bookRepository) {
        this.booksRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }
}
