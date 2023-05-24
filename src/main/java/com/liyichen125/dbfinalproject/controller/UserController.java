package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired //注入bean 使用jdbc執行 MySQL資料庫操作
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserService userService;


    //改post
    @PostMapping("/users") //設置url路徑對應到此方法上，並限制只能使用Post方法,
    public String create(@RequestBody User user){ //使用@RequestBody取得前端requestBody資訊

        return userService.CreateUser(user);
    }
}
