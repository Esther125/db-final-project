package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.dto.UserLoginRequest;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginPageController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showLoginForm(Model model) {
        model.addAttribute("UserLoginRequest", new UserLoginRequest());
        return "login";
    }

    @PostMapping("/users/login-success")
    public String loginSuccess(@ModelAttribute("UserLoginRequest") UserLoginRequest userLoginRequest, Model model) {
        User user = userService.login(userLoginRequest);
        model.addAttribute("user", user);
        return "login-success"; // 返回success作为渲染成功页面的逻辑视图名
    }




}
