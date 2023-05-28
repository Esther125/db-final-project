package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.dto.UserLoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginPageController {
    @GetMapping("/users")
    public String showLoginForm(Model model) {
        model.addAttribute("UserLoginRequest", new UserLoginRequest());
        return "test";
    }
}
