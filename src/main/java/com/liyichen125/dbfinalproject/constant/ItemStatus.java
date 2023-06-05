package com.liyichen125.dbfinalproject.constant;

public enum ItemStatus {

    AVAILABLE("可供借用"),
    UNAVAILABLE("不可供借用"),
    RESERVED("被預約");

    private final String displayName;

    ItemStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
