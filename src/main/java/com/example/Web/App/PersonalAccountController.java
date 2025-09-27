package com.example.Web.App;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonalAccountController {

    @GetMapping("/personal-account")
    public String show(@RequestParam(name = "name", required = false) String personName, Model model) {
        model.addAttribute("personName", personName);
        return "personal-account";
    }
}