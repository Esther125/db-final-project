package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;

import java.util.List;

public interface ItemDao {
    List<Item> getItems(ItemType item, ItemStatus status, String search);
//    Item getItemByStatus(Integer status);
    Item getItemById(Integer id);
    Integer createItem(ItemRequest itemRequest);
    void updateItem(Integer item_id, Item item);
    void deleteItemById(Integer item_id);
}
