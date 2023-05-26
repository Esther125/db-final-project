package com.liyichen125.dbfinalproject.service;

import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.User;

public interface UserService {
    User getUserById(Integer user_id);
    Integer register(UserRegisterRequest userRegisterRequest);

}
