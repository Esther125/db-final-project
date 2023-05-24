package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.dao.UserDao;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired//使用InterFace 發揮spring Ioc特性
    private UserDao userDao;

    @Override
    public String CreateUser(User user) {
        return null;
    }
}
