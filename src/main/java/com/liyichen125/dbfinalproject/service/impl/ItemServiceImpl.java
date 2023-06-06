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
    public List<Item> getItems(ItemType type, ItemStatus status, String search) {
        return itemDao.getItems(type,status,search);
    }

    @Override
    public Item getItemById(Integer itemId) {
        return itemDao.getItemById(itemId);
    }

    @Override
    public Integer createItem(ItemRequest itemRequest) {
        return itemDao.createItem(itemRequest);
    }

    public void updateItem(Integer item_id, ItemRequest itemRequest){
        Item item = convertToItem(itemRequest);
        itemDao.updateItem(item_id, item);
    }


    @Override
    public void deleteItemById(Integer item_id) {
        itemDao.deleteItemById(item_id);
    }

    public ItemRequest convertToItemRequest(Item item) {
        ItemRequest itemRequest = new ItemRequest();

        // 假设你的Item和ItemRequest类都有以下字段
        itemRequest.setItem_name(item.getItem_name());
        itemRequest.setType(item.getType());
        itemRequest.setStatus(item.getStatus());
        itemRequest.setBorrow_day(item.getBorrow_day());
        itemRequest.setTenure(item.getTenure());
        itemRequest.setCompensation_price(item.getCompensation_price());

        return itemRequest;
    }

    public Item convertToItem(ItemRequest itemRequest) {
        Item item = new Item();

        item.setItem_name(itemRequest.getItem_name());
        item.setType(itemRequest.getType());
        item.setStatus(itemRequest.getStatus());
        item.setBorrow_day(itemRequest.getBorrow_day());
        item.setTenure(itemRequest.getTenure());
        item.setCompensation_price(itemRequest.getCompensation_price());

        return item;
    }

}
