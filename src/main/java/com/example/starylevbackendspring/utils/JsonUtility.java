package com.example.starylevbackendspring.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonUtility {
    public static ResponseEntity<String> getJson(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        InputStream inputStream = resource.getInputStream();
        String jsonString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        return ResponseEntity.ok(jsonString);
    }
}
