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
public class HomeController {
    private static final String PATH = "static/data/promotions.json";
    @GetMapping(value = "/promotions")
    public ResponseEntity<String> getHomeImages() throws IOException {
        return JsonUtility.getJson(PATH);
    }
}
