package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryServiceApplication {
    public static void main(String[] args) {
        // Эта строка запускает весь механизм Spring Boot
        SpringApplication.run(LibraryServiceApplication.class, args);
    }
}
