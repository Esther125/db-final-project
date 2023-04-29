package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.model.Item;

public interface ItemDao {
    Item getItemByStatus(Integer status);
}
