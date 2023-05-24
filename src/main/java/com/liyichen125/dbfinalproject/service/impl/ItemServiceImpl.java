package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.dao.ItemDao;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public Item getItemByStatus(Integer status) {
        return itemDao.getItemByStatus(status);
    }

    @Override
    public Item getItemById(Integer itemId) {
        return itemDao.getItemById(itemId);
    }

    @Override
    public Integer createItem(ItemRequest itemRequest) {
        return itemDao.createItem(itemRequest);
    }


}
