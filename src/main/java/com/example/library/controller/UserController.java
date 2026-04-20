package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getAllUsers();
        if (users!=null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user!=null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User tempUser = userService.saveUser(user);
        if (user!=null) {
            return ResponseEntity.ok(tempUser);
        } else {
            return ResponseEntity.badRequest().body("Не удалось добавить пользователя");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser (@PathVariable Long id, @RequestBody User user) {
        try {
            User tempUser = userService.updateUser(id, user);
            return ResponseEntity.ok(tempUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось обновить пользователя");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable Long id) {
        boolean result = userService.deleteUser(id);
        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body("Не удалось удалить пользователя");
    }
}
