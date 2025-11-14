package com.example.Web.App;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
@EnableWebSecurity
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Отключаем CSRF для тестов
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/datas").hasAnyRole("ADMIN", "USER") // Разрешаем доступ с ролями
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
            )
            .httpBasic(httpBasic -> httpBasic.disable()) // Отключаем basic auth
            .formLogin(formLogin -> formLogin.disable()); // Отключаем form login
        
        return http.build();
    }
}