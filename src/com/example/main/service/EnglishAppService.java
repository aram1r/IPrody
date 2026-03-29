package com.example.main.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
public class EnglishAppService implements AppService {
    @Override
    public String greet() {
        return "Hello";
    }
}
