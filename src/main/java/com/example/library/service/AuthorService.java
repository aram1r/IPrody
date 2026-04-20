package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService (AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) throws Exception {
        if (authorRepository.findByName(author.getName()).isEmpty()) {
            return authorRepository.save(author);
        } else {
            throw new Exception("Не удалось создать автора");
        }

    }

    public Author updateAuthor(Long id, Author author) throws EntityNotFoundException {
        Author authorOld = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));

        authorOld.setName(author.getName());
        authorOld.setBirthDate(author.getBirthDate());
        return authorRepository.save(authorOld);
    }
}
