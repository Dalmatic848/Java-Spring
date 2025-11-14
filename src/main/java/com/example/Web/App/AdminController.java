package com.example.Web.App;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/datas")
    public String getDatasPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "datas-page";
    }

    @PostMapping("/datas")
    @ResponseBody
    public void addUserData(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        userService.addUser(username);
        System.out.println("\n\n\n\n\nДобавлен: " + username + "\n\n\n\n");
    }
}
