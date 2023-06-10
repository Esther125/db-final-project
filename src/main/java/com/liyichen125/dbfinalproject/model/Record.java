package com.liyichen125.dbfinalproject.model;

import com.liyichen125.dbfinalproject.constant.RecordSituation;

import java.util.Date;

public class Record {
    private Integer record_id;
    private Integer user_id;
    private Integer contact_person_id;
    private Integer item_id;
    private RecordSituation situation;
    private Date borrow_date;
    private Date return_date;
    private String violation_type;
    private  Item item;
    private User user;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getContact_person_id() {
        return contact_person_id;
    }

    public void setContact_person_id(Integer contact_person_id) {
        this.contact_person_id = contact_person_id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public RecordSituation getSituation() {
        return situation;
    }

    public void setSituation(RecordSituation situation) {
        this.situation = situation;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public String getViolation_type() {
        return violation_type;
    }

    public void setViolation_type(String violation_type) {
        this.violation_type = violation_type;
    }
}
