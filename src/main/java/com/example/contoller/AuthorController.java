package com.example.contoller;

import com.example.model.Author;
import com.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController{

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }

    @GetMapping("/add")
    public String getAuthorsAddPage() {
        return "add-author";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute("author") Author author) {
        authorService.create(author);

        return "redirect:/authors";
    }

    @GetMapping("get/{id}")
    public String getAuthor(@PathVariable("id") int id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "author";
    }
}
