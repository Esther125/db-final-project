package com.liyichen125.dbfinalproject.model;

import com.sun.istack.internal.NotNull;

public class User {

    @NotNull
    Integer userId;
    String name;
    String role;
    Integer point;
    String phoneNumber;
    @NotNull
    String roomNumber;
    String departmentGrade;
    @NotNull
    String password;

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
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
