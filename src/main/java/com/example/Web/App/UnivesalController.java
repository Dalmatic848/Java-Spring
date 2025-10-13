package com.example.Web.App;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.core.Authentication;

@Controller
public class UnivesalController {
    
    
    @GetMapping("/personal-account")
    public String getPersonalAccountPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("personName", username);
        }
        return "personal-account-page";
    }

    @GetMapping("/login")
public String getLoginPage(@RequestParam(required = false) String error, Model model) {
    if (error != null) {
        model.addAttribute("error", "Неверное имя пользователя или пароль!");
    }
    return "login-page";
}
    
    @GetMapping("/")
    public String getHomePage() {
        return "home-page";
    }
    
}