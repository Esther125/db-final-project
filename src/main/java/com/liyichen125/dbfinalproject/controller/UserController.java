package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired //注入bean 使用jdbc執行 MySQL資料庫操作
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserService userService;

    @PostMapping("users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer user_id = userService.register(userRegisterRequest);
        User user = userService.getUserById(user_id);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }



}
