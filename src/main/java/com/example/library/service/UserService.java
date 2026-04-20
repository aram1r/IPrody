package com.example.library.service;


import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User saveUser (User user) {
        return userRepository.save(user);
    }

    public User updateUser (Long id, User user) throws Exception {
        User tempUser = userRepository.findUserById(id);
        if (tempUser!=null) {
            user.setId(tempUser.getId());
            userRepository.save(user);
        } else {
            throw new Exception("Пользователь не найден");
        }
        return user;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.deleteUserById(id);
    }
}
