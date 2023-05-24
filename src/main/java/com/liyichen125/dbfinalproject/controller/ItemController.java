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

    //利用狀態查詢 Item
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

    //創建 Item
    //這邊createItem裡面再新創一個class會比直接用原來的Item class好
    @PostMapping("/items") //記得有寫 @NotNull 這邊一定要記得加 @Valid
    public ResponseEntity<Item> createItem(@RequestBody @Valid ItemRequest itemRequest){
        Integer item_id = itemService.createItem(itemRequest);
        //從資料庫取得商品的資訊出來
        Item item = itemService.getItemById(item_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    //修改 Item
    @PutMapping("/items/{item_id}")
    public ResponseEntity<Item> updateItem(@PathVariable Integer item_id,
                                           @RequestBody @Valid ItemRequest itemRequest){
        //檢查product是否存在
        Item item = itemService.getItemById(item_id);
        if (item ==  null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改商品的數據
        itemService.updateItem(item_id,itemRequest);
        Item updatedItem = itemService.getItemById(item_id);

        //把更新後的商品數據放在ResposeBody中回傳給前端
        return ResponseEntity.status(HttpStatus.OK).body(updatedItem);
    }

    // 刪除 Item
    @DeleteMapping("/items/{item_id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer item_id){
        itemService.deleteItemById(item_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
