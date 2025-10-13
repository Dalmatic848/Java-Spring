package com.example.Web.App.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                    .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/login").permitAll()
            .requestMatchers("/personal-account").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/datas").hasRole("ADMIN") 
            .anyRequest().authenticated() 
        )
        .httpBasic(httpBasic -> httpBasic.disable()) // ← отключаем HTTP Basic
        .formLogin(form -> form
            .loginPage("/login")           // ← ваша кастомная страница входа
            .loginProcessingUrl("/login")  // ← URL, куда форма отправляет POST
            .defaultSuccessUrl("/personal-account", false) // куда перенаправлять после входа
            .permitAll()
        )
        .rememberMe(remember -> remember
            .rememberMeCookieName("remember-me") // имя Cookie (опционально)
            .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 дней в секундах
            .userDetailsService(userDetailsService()) // ваш бин с пользователями
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/") // после выхода — на главную
            .permitAll()
        )
        .build();           
        
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
public InMemoryUserDetailsManager userDetailsService() {
    // Создаёшь пользователя "user" с паролем "password" и ролью USER
    UserDetails user = User.withUsername("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

    // Создаёшь пользователя "admin" с паролем "admin" и ролями ADMIN и USER
    UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN", "USER")
            .build();

    // Возвращаешь менеджер с этими пользователями
    return new InMemoryUserDetailsManager(user, admin);
}
}

