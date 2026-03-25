package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/add")
    public String getUserssAddPage() {
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.create(user);

        return "redirect:/users";
    }

    @GetMapping("get/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-by-id";
    }

    @GetMapping("basic/{id}")
    public String getBasicUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "basic-user-by-id";
    }
}