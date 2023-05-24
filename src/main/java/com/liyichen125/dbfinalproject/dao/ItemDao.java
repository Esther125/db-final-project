package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;

public interface ItemDao {
    Item getItemByStatus(Integer status);
    Item getItemById(Integer id);
    Integer createItem(ItemRequest itemRequest);
    interface MemberDao {
    }
}
