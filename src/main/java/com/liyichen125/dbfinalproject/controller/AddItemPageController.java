package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.dto.UserLoginRequest;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.service.ItemService;
import com.liyichen125.dbfinalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class AddItemPageController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items/add-item")
    public String showAddItemForm(Model model) {
        model.addAttribute("ItemRequest", new ItemRequest());
        return "add-item";
    }

//    @PostMapping("/items/add-item-success")
//    public String registerSuccess(@ModelAttribute("ItemRequest") ItemRequest itemRequest, Model model) {
//        Integer item_id = itemService.createItem(itemRequest);
//        model.addAttribute("item_id", item_id);
//        return "item-add-success";
//    }

    //配合下拉選單，拿出所有 ENUM值
    @ModelAttribute("itemTypes")
    public ItemType[] getItemTypes() {
        return ItemType.values();
    }
    @ModelAttribute("itemStatus")
    public ItemStatus[] getItemStatus() {
        return ItemStatus.values();
    }

    @PostMapping("/items/add-item-success")
    public String showSuccessPage(Model model,
                                  @RequestParam(required = false) ItemType type,
                                  @RequestParam(required = false) ItemStatus status,
                                  @RequestParam(required = false) String search) {

        List<Item> items = itemService.getItems(type,status,search);
        model.addAttribute("items", items);
        return "add-item-success";
    }

}
