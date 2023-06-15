package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dao.UserDao;
import com.liyichen125.dbfinalproject.dto.UserLoginRequest;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.UserService;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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

        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        // 成功創建帳號
        return userDao.createUser(userRegisterRequest);
    }

//    @Override
//    public User login(UserLoginRequest userLoginRequest) {
//        User user = userDao.getUserById(userLoginRequest.getUser_id());
//        // 檢查使用者有沒有註冊過
//        if(user == null){
//            log.warn("該id尚未註冊");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//
//        // 密碼加密
//        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
//
//        //檢查密碼
//        if(user.getPassword().equals(hashedPassword)){
//            return user;
//        }else{
//            log.warn("密碼錯誤");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }
public User login(UserLoginRequest userLoginRequest) {
    User user = userDao.getUserById(userLoginRequest.getUser_id());
    // 檢查使用者有沒有註冊過
    if(user == null){
        log.warn("該id尚未註冊");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "該id尚未註冊");
    }

    // 密碼加密
    String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

    //檢查密碔
    if(user.getPassword().equals(hashedPassword)){
        return user;
    }else{
        log.warn("密碼錯誤");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "密碼錯誤");
    }
}


    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }


}
