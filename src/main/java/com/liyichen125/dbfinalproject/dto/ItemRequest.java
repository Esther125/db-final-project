package com.liyichen125.dbfinalproject.dto;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.model.Item;
import com.sun.istack.internal.NotNull;

import java.util.Date;

public class ItemRequest {
    // 這邊只有前端傳回來的(可以更改的)值，沒有date 也沒有id
    private Integer item_id;
    @NotNull
    private ItemType type;
    @NotNull
    private ItemStatus status;

    private Integer borrow_day;
    @NotNull
    private Integer tenure;
    @NotNull
    private Integer compensation_price;

    @NotNull
    private String item_name;

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }


    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Integer getBorrow_day() {
        return borrow_day;
    }

    public void setBorrow_day(Integer borrow_day) {
        this.borrow_day = borrow_day;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public Integer getCompensation_price() {
        return compensation_price;
    }

    public void setCompensation_price(Integer compensation_price) {
        this.compensation_price = compensation_price;
    }
}
