package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.dao.UserDao;

import java.nio.charset.StandardCharsets;


@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;



    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        var user = userDao.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        // Преобразуем byte[] в строку с указанием кодировки (стандарт для BCrypt - UTF_8)
        String passwordFromDb = new String(user.getPassword().getBytes(), StandardCharsets.UTF_8);

        log.info("Данные из БД для {}:", email);
        log.info("   - Хеш пароля: {}", passwordFromDb);
        log.info("   - Статус (enabled): {}", user.getEnabled());
        log.info("   - Роли: {}", user.getAuthorities());

        String[] roles = user.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .toArray(String[]::new);

        return User.builder().username(user.getEmail()).
                password(user.getPassword()).
                disabled(!user.getEnabled())
                .authorities(roles).build();
    }
}
