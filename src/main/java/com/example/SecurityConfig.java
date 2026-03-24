package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer {

    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(registry -> {
            registry.requestMatchers("/users/").hasAnyAuthority("USER", "ADMIN").
                    requestMatchers("/users/**").hasAuthority("ADMIN").anyRequest().authenticated();
        }).csrf(CsrfConfigurer::disable).httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
