package com.liyichen125.dbfinalproject.service;

import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;

public interface ItemService {
    Item getItemByStatus(Integer status);

    Item getItemById(Integer itemId);
    Integer createItem(ItemRequest itemRequest);
    void updateItem(Integer item_id, ItemRequest itemRequest);
    void deleteItemById(Integer item_id);
}
