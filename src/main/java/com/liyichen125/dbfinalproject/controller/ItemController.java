package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
//        return "item-status";
    }
}
