package com.example.Wishlist.service;

import com.example.Wishlist.Database.ItemDAO;
import com.example.Wishlist.Database.ItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemDAO itemDAO;

    @Transactional
    public String updateItem(ItemDB itemDB){
        List<ItemDB> currentItem = itemDAO.findByItemId(itemDB.getId());
        currentItem.stream().forEach(s-> {
            ItemDB itemToUpdate = itemDAO.findItemBySearch(s.getName()).get(0);
            itemToUpdate.setName(itemDB.getName());
            itemToUpdate.setDescription(itemDB.getDescription());
            itemToUpdate.setItem_link(itemDB.getItem_link());
            itemToUpdate.setList_name(itemDB.getList_name());
        });
        return "Item UPDATED";
    }

    @Transactional
    public String deleteItem(ItemDB itemDB){
        List<ItemDB> item = itemDAO.findItemBySearch(itemDB.getName());
        item.stream().forEach(s-> {
            itemDAO.delete(s);
        });
        return "Item DELETED";
    }
}
