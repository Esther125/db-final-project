package com.liyichen125.dbfinalproject.service;

import com.liyichen125.dbfinalproject.dto.RecordRequest;

public interface RecordService {
    Integer createRecord(Integer user_id, RecordRequest recordRequest);
}
