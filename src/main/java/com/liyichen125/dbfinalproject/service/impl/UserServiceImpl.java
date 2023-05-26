package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.dao.UserDao;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.UserService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired//使用InterFace 發揮spring Ioc特性
    private UserDao userDao;

    @Override
    public User getUserById(Integer user_id) {
        return userDao.getUserById(user_id);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // 避免重複註冊
        User user = userDao.getUserById(userRegisterRequest.getUser_id());
        if(user != null){
            log.warn("該id已經被使用");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 成功創建帳號
        return userDao.createUser(userRegisterRequest);
    }
}
