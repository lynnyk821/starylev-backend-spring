package com.example.starylevbackendspring.modules;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    private String author;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Float price;
    @Column(name = "image_path")
    private String imagePath;
}