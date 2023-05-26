package com.liyichen125.dbfinalproject.rowmapper;

import com.liyichen125.dbfinalproject.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i)throws SQLException{
        User user = new User();
        user.setUser_id(resultSet.getInt("user_id"));
        user.setName(resultSet.getString("name"));
        user.setRole(resultSet.getInt("role"));
        user.setPoint(resultSet.getInt("point"));
        user.setPhoneNumber(resultSet.getString("phoneNum"));
        user.setRoomNumber(resultSet.getString("roomNum"));
        user.setDepartmentGrade(resultSet.getString("department_grade"));
        user.setPassword(resultSet.getString("password"));

        return user;

    }
}
