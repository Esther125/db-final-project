package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.service.ItemService;
import com.liyichen125.dbfinalproject.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecordPageController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/records/add-record")
    public String showAddRecordForm(Model model) {
        RecordRequest recordRequest = new RecordRequest();
        model.addAttribute("RecordRequest",recordRequest);

        return "add-record";
    }

    @ModelAttribute("recordSituation")
    public RecordSituation[] getRecordSituation() {
        return RecordSituation.values();
    }

    @PostMapping("/records/add-record-success")
    public String showSuccessPage(Model model, @ModelAttribute("RecordRequest")RecordRequest recordRequest, @RequestParam(required = false) String search,
                                  @RequestParam(required = false)RecordSituation situation) {
        int record_id = recordService.createRecord(recordRequest);
        List<Record> records = recordService.getRecords(situation,search);
        model.addAttribute("records", records);
        return "add-record-success";
    }
}
