package com.liyichen125.dbfinalproject.model;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;

import java.util.Date;

public class Item {
    private Integer item_id;
    //可以知道商品到底有那些分類
    private ItemType type;
    private ItemStatus status;
    private Integer borrow_days;
    private Date purchase_date;
    private Integer tenure;
    private Integer compensation_price;
    private String item_name;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

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

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Integer getBorrow_day() {
        return borrow_days;
    }

    public void setBorrow_day(Integer borrow_day) {
        this.borrow_days = borrow_day;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
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
