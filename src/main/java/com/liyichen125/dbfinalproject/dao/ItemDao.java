package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;

import java.util.List;

public interface ItemDao {
    List<Item> getItems();
    Item getItemByStatus(Integer status);
    Item getItemById(Integer id);
    Integer createItem(ItemRequest itemRequest);
    void updateItem(Integer item_id, ItemRequest itemRequest);
    void deleteItemById(Integer item_id);
}
