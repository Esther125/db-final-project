package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("users/{user_id}/records")
    public ResponseEntity<?> createRecord(@PathVariable Integer user_id,
                                          @RequestBody @Valid RecordRequest recordRequest){
        Integer record_id = recordService.createRecord(user_id, recordRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(record_id);
    }

}
