package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/items/{status}")
    public ResponseEntity<Item> getItemByStatus(@PathVariable Integer status){
        Item item = itemService.getItemByStatus(status);
        if (item != null){
            return ResponseEntity.status(HttpStatus.OK).body(item);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/items")
    public String home(Model model) {
        Item item = new Item();

        model.addAttribute("myItem", item);

        return "item-status";
    }

    //這邊createItem裡面再新創一個class會比直接用原來的Item class好
    @PostMapping("/items") //記得有寫 @NotNull 這邊一定要記得加 @Valid
    public ResponseEntity<Item> createItem(@RequestBody @Valid ItemRequest itemRequest){
        Integer item_id = itemService.createItem(itemRequest);
        //從資料庫取得商品的資訊出來
        Item item = itemService.getItemById(item_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
}
