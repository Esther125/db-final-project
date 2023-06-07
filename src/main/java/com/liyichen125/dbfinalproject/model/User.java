package com.liyichen125.dbfinalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liyichen125.dbfinalproject.constant.UserRole;
import com.sun.istack.internal.NotNull;

public class User {

    @NotNull
    Integer user_id;
    String name;
    UserRole role;
    Integer point;
    String phoneNumber;
    String roomNumber;
    String departmentGrade;
    // 隱藏變數
    @JsonIgnore
    String password;

    public Integer getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public Integer getPoint() {
        return point;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getDepartmentGrade() {
        return departmentGrade;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setDepartmentGrade(String departmentGrade) {
        this.departmentGrade = departmentGrade;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
