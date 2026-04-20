package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController (AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/getall")
    public List<Author> getAll() {
        List<Author> authors = authorService.getAllAuthors();
        return authors;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        Optional<Author> author = authorService.getAuthorById(id);

        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Автор с id " + id + " не найден");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable long id, @RequestBody Author author) {
        try {
            Author authorOld = authorService.updateAuthor(id, author);
            return ResponseEntity.ok(authorOld);
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Автор с id " + id + " не найден");
        }
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        try {
            Author authorOld = authorService.createAuthor(author);
            return ResponseEntity.ok(authorOld);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
