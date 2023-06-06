package com.liyichen125.dbfinalproject.service;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems(ItemType type, ItemStatus status, String search);
//    Item getItemByStatus(Integer status);
    Item getItemById(Integer itemId);
    Integer createItem(ItemRequest itemRequest);
    void updateItem(Integer item_id, ItemRequest itemRequest);
    void deleteItemById(Integer item_id);
    ItemRequest convertToItemRequest(Item item);
    Item convertToItem(ItemRequest itemRequest);
}
