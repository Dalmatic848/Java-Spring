package com.example.Web.App;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminController {
    private List<String> users = new ArrayList<>();
    
    public AdminController() {
        // Инициализация при создании контроллера
        users.add("Alice");
        users.add("Bob");
        users.add("Charlie");
    }
    
    @GetMapping("/datas")
    public String getDatasPage(Model model) {
         
        model.addAttribute("users", users);
        
        return "datas-page";
    }

    @PostMapping("/datas")
    @ResponseBody
    public void addUserData(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        System.out.println("\n\n\n\n\nДобавлен: " + username + "\n\n\n\n");
        // ничего не возвращаем → Spring отправит статус 200 без тела
    }
    
    
}
