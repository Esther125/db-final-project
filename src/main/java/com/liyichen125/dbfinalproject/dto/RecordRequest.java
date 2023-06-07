package com.liyichen125.dbfinalproject.dto;

import com.liyichen125.dbfinalproject.constant.RecordSituation;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class RecordRequest {
    public void setViolation_type(Integer violation_type) {
        this.violation_type = violation_type;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    @NotNull
    private Integer user_id;

    public void setContact_person_id(Integer contact_person_id) {
        this.contact_person_id = contact_person_id;
    }

    @NotNull
    private Integer item_id;
    @NotNull
    private RecordSituation situation;
    @NotNull
    private  Integer contact_person_id;

    @NotNull
    private Integer violation_type;
    @NotNull
    private Date return_date;

    public Integer getUser_id() {
        return user_id;
    }
    public  void setUser_id(Integer user_id){this.user_id = user_id;}
    public Date getReturn_date(){
        return return_date;
    }
    public Integer getViolation_type(){
        return  violation_type;
    }

    public Integer getContact_person_id() {
        return contact_person_id;
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
}
