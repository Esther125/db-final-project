package com.liyichen125.dbfinalproject.constant;

public enum ItemType {
    //借用物品類別
    //固定值需要是大寫英文 兩個字可用底線連接兩個字
    CARD("臨時卡"),
    KEY("鑰匙"),
    DEHUMIDIFIER("除濕機"),
    TOOL("工具"),
    CLEANER("清掃工具"),
    CAR("推車"),
    OTHERS("其他");

    private final String displayName;

    ItemType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
