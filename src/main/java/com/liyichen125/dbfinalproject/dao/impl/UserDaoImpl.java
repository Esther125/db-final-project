package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.dao.UserDao;
import com.liyichen125.dbfinalproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class UserDaoImpl  implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String CreateUser(User user) {
        // 在SQL前面加上：表示變數
        String sql ="INSERT INTO user(user_id,name,role,point,phoneNum,roomNum,department_grade,password)" +
                "VALUE(:user_id, :userName,:userRole,:userPoint,:userPhoneNum,:userRoomNum,userDepartment_grade,user_password)";

        Map<String, Object> map =new HashMap<>();
        //put(SQL變數,值）
        map.put("userId",user.getUserId());
        map.put("userName",user.getName());
        map.put("userRole",user.getRole());
        map.put("userPoint",user.getPoint());
        map.put("userPhoneNum",user.getPhoneNumber());
        map.put("userRoomNum",user.getRoomNumber());
        map.put("userDepartment_grade",user.getDepartmentGrade());
        map.put("userPassword",user.getPassword());


        namedParameterJdbcTemplate.update(sql,map);
        return "註冊成功"; //return放將來要使用的html

    }


}
