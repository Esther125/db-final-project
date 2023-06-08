package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
public class ItemPageController {

    @Autowired
    private ItemService itemService;

    // 新增物品  - 管理員
    @GetMapping("/items/add-item")
    public String showAddItemForm(Model model) {
        model.addAttribute("ItemRequest", new ItemRequest());
        return "add-item";
    }

    //配合下拉選單，拿出所有 ENUM值
    @ModelAttribute("itemTypes")
    public ItemType[] getItemTypes() {
        return ItemType.values();
    }

    //配合下拉選單，拿出所有 ENUM值
    @ModelAttribute("itemStatus")
    public ItemStatus[] getItemStatus() {
        return ItemStatus.values();
    }

    //新增物品成功後跳轉到物品管理頁面 - 管理員
    @PostMapping("/items/add-item-success")
    public String showSuccessPage(Model model,
                                  @ModelAttribute("ItemRequest") ItemRequest itemRequest,
                                  @RequestParam(required = false) ItemType type,
                                  @RequestParam(required = false) ItemStatus status,
                                  @RequestParam(required = false) String search,
                                  RedirectAttributes redirectAttributes) {

        itemService.createItem(itemRequest);
        List<Item> items = itemService.getItems(type,status,search);
        model.addAttribute("items", items);
        redirectAttributes.addFlashAttribute("success", true);
//        return "add-item-success";
        return "redirect:/items";
    }

    //物品管理頁面 - 管理員
    @GetMapping("/items")
    public String getAllItems(Model model,
            //利用條件篩選物品
            @RequestParam(required = false) ItemType type,
            @RequestParam(required = false) ItemStatus status,
            //利用關鍵字查詢物品
            @RequestParam(required = false) String search

    ){
//        List<Item> itemList = itemService.getItems(type,status,search);
        List<Item> items = itemService.getItems(type,status,search);
        model.addAttribute("items", items);
        return "show-all-items-admin";
    }

    //對物品做編輯 - 管理員
    @GetMapping("/items/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer itemId, Model model) {
        Item item = itemService.getItemById(itemId);
        // 需要创建一个从Item对象到ItemRequest对象的转换方法
        ItemRequest itemRequest = itemService.convertToItemRequest(item);
        itemRequest.setItem_id(itemId);
        model.addAttribute("ItemRequest", itemRequest);
        return "edit-item";
    }

    //提交編輯物品表單 - 管理員
    @PostMapping("/items/edit/{id}-success")
    public String updateItem(@PathVariable("id") Integer itemId,
                             @ModelAttribute("ItemRequest") ItemRequest itemRequest,
                             RedirectAttributes redirectAttributes) {
        // 需要创建一个从ItemRequest对象到Item对象的转换方法

        // 更新物品
        itemService.updateItem(itemId, itemRequest);
        System.out.println(itemId);

        redirectAttributes.addFlashAttribute("success", true);
//        return "test";
        return "redirect:/items";
    }

    //物品管理頁面 - 學生
    @GetMapping("/items2")
    public String getAllItems2(Model model,
                              //利用條件篩選物品
                              @RequestParam(required = false) ItemType type,
                              @RequestParam(required = false) ItemStatus status,
                              //利用關鍵字查詢物品
                              @RequestParam(required = false) String search

    ){
//        List<Item> itemList = itemService.getItems(type,status,search);

        // Add all ItemStatus values to the model
        model.addAttribute("itemStatuses", Arrays.asList(ItemStatus.values()));

        // If ItemType also has multiple values, you can do the same:
        model.addAttribute("itemTypes", Arrays.asList(ItemType.values()));
        List<Item> items = itemService.getItems(type,status,search);
        model.addAttribute("items", items);
        return "show-all-items-student";
    }

    @DeleteMapping("/items/delete/{id}-success")
    public String deleteItem(@PathVariable Integer id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

    @GetMapping("/items/delete/{id}")
    public String updateItem(@PathVariable("id") Integer itemId,Model model) {
//        System.out.println(itemId);
//        itemService.deleteItemById(itemId);
        Item item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        return "delete-item";
    }

}