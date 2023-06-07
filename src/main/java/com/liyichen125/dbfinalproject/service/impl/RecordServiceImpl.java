package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dao.RecordDao;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    @Transactional //修改多個table時確保數據一致
    @Override
    public Integer createRecord(Integer user_id, RecordRequest recordRequest) {
        recordDao.createRecord(user_id, recordRequest);
        return user_id;
    }
    @Override
    public List<Record> getRecords(RecordSituation situation, String search) {
        return recordDao.getRecords(situation,search);
    }
}
