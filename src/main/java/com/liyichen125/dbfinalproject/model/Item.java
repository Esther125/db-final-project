package com.liyichen125.dbfinalproject.model;

import com.liyichen125.dbfinalproject.constant.ItemType;

public class Item {
    private Integer item_id;
    //可以知道商品到底有那些分類
    private ItemType type;
    private Integer status;
    private Integer borrow_day;

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBorrow_day() {
        return borrow_day;
    }

    public void setBorrow_day(Integer borrow_day) {
        this.borrow_day = borrow_day;
    }
}
