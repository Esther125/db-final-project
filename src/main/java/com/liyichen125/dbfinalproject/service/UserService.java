package com.liyichen125.dbfinalproject.service;

import com.liyichen125.dbfinalproject.dto.UserLoginRequest;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer user_id);
    Integer register(UserRegisterRequest userRegisterRequest);
    User login(UserLoginRequest userLoginRequest);
    List<User> getUsers();

}
