package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.ItemService;
import com.liyichen125.dbfinalproject.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecordPageController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private ItemService itemService;
    @ModelAttribute("recordSituation")
    public RecordSituation[] getRecordSituation() {
        return RecordSituation.values();
    }


    @GetMapping("/records/add-record")
    public String showAddRecordForm(Model model) {
        RecordRequest recordRequest = new RecordRequest();
        model.addAttribute("RecordRequest",recordRequest);

        return "add-record";
    }
    @GetMapping("/records")
    public String getAllRecords(Model model,
                              //利用條件篩選物品
                              @RequestParam(required = false) RecordSituation situation,
                              //利用關鍵字查詢物品
                              @RequestParam(required = false) String search
    ){
        if(search == ""){
            search = null;
        }
        List<Record> records = recordService.getRecords(situation,search);
        model.addAttribute("records", records);
        List<Item> items = new ArrayList<Item>();
        for (Record record : records){
            items.add(itemService.getItemById(record.getItem_id())) ;
        }
        model.addAttribute("items", items);
        return "show-all-records";
    }



    @PostMapping("/records/add-record-success")
    public String showSuccessPage(Model model, @ModelAttribute("RecordRequest")RecordRequest recordRequest, @RequestParam(required = false) String search,
                                  @RequestParam(required = false)RecordSituation situation) {
        int record_id = recordService.createRecord(recordRequest);
        List<Record> records = recordService.getRecords(situation,search);
        model.addAttribute("records", records);
        return "redirect:/records";
    }
    @GetMapping("/records/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer recordId, Model model) {
        Record record = recordService.getRecordById(recordId);
        // 需要创建一个从Item对象到ItemRequest对象的转换方法
        RecordRequest recordRequest = recordService.covertToRecordRequest(record);
        recordRequest.setRecord_id(recordId);
        model.addAttribute("RecordRequest", recordRequest);
        return "confirm-record";
    }
    @GetMapping("/records/confirm/{id}")
    public String showConfirmForm(@PathVariable("id") Integer recordId, Model model, HttpSession session) {
        Record record = recordService.getRecordById(recordId);
        // 需要创建一个从Item对象到ItemRequest对象的转换方法
        User user = (User) session.getAttribute("user");
        record.setContact_person_id(user.getUser_id());
        RecordRequest recordRequest = recordService.covertToRecordRequest(record);
        recordRequest.setRecord_id(recordId);
        model.addAttribute("RecordRequest", recordRequest);

        return "confirm-record";
    }

    @PostMapping("/records/edit/{id}-success")
    public String updateRecord(@PathVariable("id") Integer recordID,
                             @ModelAttribute("RecordRequest") RecordRequest recordRequest,
                             RedirectAttributes redirectAttributes) {
        // 需要创建一个从ItemRequest对象到Item对象的转换方法
        // 更新物品
        recordService.updateRecord(recordID, recordRequest);
        Record record = recordService.getRecordById(recordID);
        Item item = itemService.getItemById(record.getItem_id());
        if(record.getSituation() == RecordSituation.BORROW){
            item.setStatus(ItemStatus.UNAVAILABLE);
        }else  if(record.getSituation() == RecordSituation.RESERVE){
            item.setStatus(ItemStatus.RESERVED);
        }else {
            item.setStatus(ItemStatus.AVAILABLE);
        }
        ItemRequest itemRequest = itemService.convertToItemRequest(item);
        itemService.updateItem(record.getItem_id(),itemRequest);


        System.out.println(recordID);

        redirectAttributes.addFlashAttribute("success", true);
//        return "test";
        return "redirect:/records";
    }

}
