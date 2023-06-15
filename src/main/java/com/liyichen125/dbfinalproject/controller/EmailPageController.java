package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.EmailService;
import com.liyichen125.dbfinalproject.service.ItemService;
import com.liyichen125.dbfinalproject.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailPageController {
    RecordService recordService;
    ItemService itemService;
    @GetMapping("/send-email")
    public String mailSender() {





        return "send-email";
    }
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public void sendEmail(@RequestParam String to, @RequestParam Integer record_id) {
        String subject ="[Dormy 系統歸還物品提醒]";
        Record record = recordService.getRecordById(record_id);
        User user = record.getUser();
        Item item = itemService.getItemById(record.getItem_id());
        String content = user.getName() + "，您的物品"+item.getItem_name()+"已經超過借用期限，請盡快將物品歸還給宿舍，謝謝您";
        emailService.sendEmail(to, subject, content);
    }

}
