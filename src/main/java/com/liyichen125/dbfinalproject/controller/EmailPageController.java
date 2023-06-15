package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.EmailService;
import com.liyichen125.dbfinalproject.service.ItemService;
import com.liyichen125.dbfinalproject.service.RecordService;
import com.liyichen125.dbfinalproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EmailPageController {
    @Autowired
    RecordService recordService;
    @Autowired
    ItemService itemService;
    @Autowired
    UserService userService;
    @GetMapping("/send-email")
    public String mailSender() {





        return "send-email";
    }
    @Autowired
    private EmailService emailService;
    @GetMapping ("/delay-users")
    public String showAllDelay(Model model){
        List<Record> recordList = recordService.getRecords(null,null);
        List<Record>records = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        Date now = new Date();
        List<User> users = new ArrayList<>();
        for(Record record:recordList){
            if(record.getReturn_date() != null){
                if (record.getReturn_date().compareTo(now) < 0) {
                    record.setItem(itemService.getItemById(record.getItem_id()));
                    record.setUser(userService.getUserById(record.getUser_id()));

                    records.add(record);

                }

            }

        }

        model.addAttribute("records",records);

        return "delay-users";
    }



    @GetMapping("/send-email/{id}")
    public String sendEmail(@PathVariable("id") Integer record_id, Model model) {

        String subject ="[Dormy 系統歸還物品提醒]";
        Record record = recordService.getRecordById(record_id);
        String to = record.getUser_id().toString();
        User user = userService.getUserById(record.getUser_id());

        Item item = itemService.getItemById(record.getItem_id());
        String content = user.getName() + "，您的物品"+item.getItem_name()+"已經超過借用期限，請盡快將物品歸還給宿舍，謝謝您";
        emailService.sendEmail(to, subject, content);
        return "redirect:/delay-users";

    }

}
