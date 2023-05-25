package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dao.ItemDao;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;


    @Override
    public List<Item> getItems(ItemType type, ItemStatus status) {
        return itemDao.getItems(type,status);
    }

//    @Override
//    public Item getItemByStatus(Integer status) {
//        return itemDao.getItemByStatus(status);
//    }

    @Override
    public Item getItemById(Integer itemId) {
        return itemDao.getItemById(itemId);
    }

    @Override
    public Integer createItem(ItemRequest itemRequest) {
        return itemDao.createItem(itemRequest);
    }

    public void updateItem(Integer item_id, ItemRequest itemRequest){
        itemDao.updateItem(item_id,itemRequest);
    }

    @Override
    public void deleteItemById(Integer item_id) {
        itemDao.deleteItemById(item_id);
    }
}
