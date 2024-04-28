package com.example.starylevbackendspring.models;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Book {
    private Long id;
    private String type;
    private String name;
    private Float price;
    private String authorName;
    private String authorSurname;
    private String iconUrl;
}