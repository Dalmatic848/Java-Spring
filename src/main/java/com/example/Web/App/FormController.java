package com.example.Web.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @Autowired
    private MessageSource messageSource;
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "Form";
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        
        
        // Проверка логина/пароля
        if (password.equals("988830")) {
            return "redirect:http://localhost:7070/personal-account?name=" + username;
        } 
        else {
             String errorMessage = messageSource.getMessage(
                "login.password.error", 
                null, 
                LocaleContextHolder.getLocale()
            );
            model.addAttribute("error", errorMessage);
            return "Form";
        }
    }
}