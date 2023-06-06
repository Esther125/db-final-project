package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.service.ItemService;
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
    private ItemService itemService;

    @GetMapping("/records/add-record")
    public String showAddRecordForm(Model model) {
        model.addAttribute("RecordRequest", new RecordRequest());
        return "add-record";
    }
    @ModelAttribute("itemTypes")
    public ItemType[] getItemTypes() {
        return ItemType.values();
    }
    @ModelAttribute("itemStatus")
    public ItemStatus[] getItemStatus() {
        return ItemStatus.values();
    }

    @PostMapping("/records/add-record-success")
    public String showSuccessPage(Model model,
                                  @RequestParam(required = false) ItemType type,
                                  @RequestParam(required = false) ItemStatus status,
                                  @RequestParam(required = false) String search) {

        List<Record> items = itemService.getItems(type,status,search);
        model.addAttribute("items", items);
        return "add-item-success";
    }
}
