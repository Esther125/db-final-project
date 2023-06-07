package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dao.RecordDao;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
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
    public Integer createRecord(RecordRequest recordRequest) {

        return recordDao.createRecord(recordRequest);
    }
    @Override
    public List<Record> getRecords(RecordSituation situation, String search) {
        return recordDao.getRecords(situation,search);
    }
    @Override
    public Record getRecordById(Integer recordId){
        return recordDao.getRecordById(recordId);
    }
    @Override
    public  RecordRequest covertToRecordRequest(Record record){
        RecordRequest recordRequest = new RecordRequest();

        // 假设你的Item和ItemRequest类都有以下字段
        recordRequest.setItem_id(record.getItem_id());
        recordRequest.setUser_id(record.getUser_id());
        recordRequest.setSituation(record.getSituation());
        recordRequest.setReturn_date(record.getReturn_date());
        recordRequest.setViolation_type(record.getViolation_type());
        recordRequest.setContact_person_id(record.getContact_person_id());
        return recordRequest;

    }
    @Override
    public  void updateRecord(Integer recordId,RecordRequest recordRequest){
        recordDao.updateRecord(recordId,recordRequest);
    }
}
