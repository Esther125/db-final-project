package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.dao.UserDao;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.rowmapper.ItemRowMapper;
import com.liyichen125.dbfinalproject.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.management.ObjectName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserDaoImpl  implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserById(Integer user_id) {
        String sql = "SELECT * FROM user WHERE user_id = :user_id";

        Map<String, Object> map = new HashMap<>();
        map.put("user_id",user_id);

        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        if (userList.size()>0){
            return userList.get(0);
        }else{
            return null;
        }
    }

    // 使用者創建新帳戶
    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user(user_id,name,role,phoneNum,roomNum,department_grade,password)" +
                " VALUES (:user_id,:name,:role,:phoneNum,:roomNum,:department_grade,:password)";

        Map<String, Object> map = new HashMap<>();
        map.put("user_id",userRegisterRequest.getUser_id());
        map.put("name",userRegisterRequest.getName());

        map.put("role",userRegisterRequest.getRole().toString());
        map.put("phoneNum",userRegisterRequest.getPhoneNum());
        map.put("roomNum",userRegisterRequest.getRoomNum());
        map.put("department_grade",userRegisterRequest.getDepartment_grade());
        map.put("password",userRegisterRequest.getPassword());

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map));

        return userRegisterRequest.getUser_id();

    }

    @Override
    public List<User> getUsers(){
        String sql = "SELECT * FROM user;";

        Map<String, Object> map = new HashMap<>();

        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        return userList;
    }

}
