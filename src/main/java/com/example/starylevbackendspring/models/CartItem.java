package com.example.starylevbackendspring.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItem {
    private Long id;
    private String type;
    private String name;
    private Float price;
    private String authorName;
    private String authorSurname;
}
