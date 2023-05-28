package com.liyichen125.dbfinalproject.dto;

import com.liyichen125.dbfinalproject.constant.RecordSituation;

import javax.validation.constraints.NotNull;

public class RecordRequest {
    @NotNull
    private Integer item_id;
    @NotNull
    private RecordSituation situation;

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
