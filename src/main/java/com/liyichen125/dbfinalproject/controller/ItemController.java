package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;
    //查詢商品列表
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems(
            //利用條件篩選物品
            @RequestParam(required = false) ItemType type,
            @RequestParam(required = false) ItemStatus status,

            //利用關鍵字查詢物品
            @RequestParam(required = false) String search

            ){
         List<Item> itemList = itemService.getItems(type,status,search);
         return ResponseEntity.status(HttpStatus.OK).body(itemList);
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

        //把更新後的商品數據放在 ResposeBody中回傳給前端
        return ResponseEntity.status(HttpStatus.OK).body(updatedItem);
    }

    // 刪除 Item
    @DeleteMapping("/items/{item_id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer item_id){
        itemService.deleteItemById(item_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
