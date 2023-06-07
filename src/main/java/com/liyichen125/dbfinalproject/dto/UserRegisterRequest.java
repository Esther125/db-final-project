package com.liyichen125.dbfinalproject.dto;

import com.liyichen125.dbfinalproject.constant.UserRole;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRegisterRequest {
    @NotNull
    private Integer user_id;
    @NotNull
    private String name;
    private UserRole role;
    private String phoneNum;
    private String roomNum;
    private String department_grade;
    @NotNull
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getDepartment_grade() {
        return department_grade;
    }

    public void setDepartment_grade(String department_grade) {
        this.department_grade = department_grade;
    }


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
