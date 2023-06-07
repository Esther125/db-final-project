package com.liyichen125.dbfinalproject.constant;

public enum UserRole {
    STUDENT("學生"),
    ADMIN("管理者");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
