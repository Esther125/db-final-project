package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.dto.UserLoginRequest;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
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

    //使用者登入頁面
    @GetMapping("/users/login")
    public String showLoginForm(Model model) {
        model.addAttribute("UserLoginRequest", new UserLoginRequest());
        return "login-student";
    }

    //登入成功的歡迎頁面
    @PostMapping("/users/login-success")
    public String loginSuccess(@ModelAttribute("UserLoginRequest") UserLoginRequest userLoginRequest, Model model) {
        User user = userService.login(userLoginRequest);
        model.addAttribute("user", user);
        return "login-success"; // 返回success作为渲染成功页面的逻辑视图名
    }

    @GetMapping("/users/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("UserRegisterRequest", new UserRegisterRequest());
        return "register";
    }

    @PostMapping("/users/register-success")
    public String registerSuccess(@ModelAttribute("UserRegisterRequest") UserRegisterRequest userRegisterRequest, Model model) {
        model.addAttribute("UserRegisterRequest", new UserRegisterRequest());
        Integer user_id = userService.register(userRegisterRequest);
        model.addAttribute("user_id", user_id);
        return "register-success";
    }





}
