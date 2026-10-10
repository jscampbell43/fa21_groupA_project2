package com.example.Wishlist.service;

import com.example.Wishlist.Database.ItemDAO;
import com.example.Wishlist.Database.ItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

// Service class for business logic related to item operations
// Acts as a middle layer between controllers and repositories
@Service
public class ItemService {
    @Autowired
    private ItemDAO itemDAO;

    // Update an existing item in the database
    // @Transactional ensures the entire operation is atomic (all changes commit together or none do)
    // Finds the item by ID, then updates all its fields with the new values
    @Transactional
    public String updateItem(ItemDB itemDB){
        List<ItemDB> currentItem = itemDAO.findByItemId(itemDB.getId());
        currentItem.stream().forEach(s-> {
            ItemDB itemToUpdate = itemDAO.findItemByName(s.getName()).get(0);
            itemToUpdate.setName(itemDB.getName());
            itemToUpdate.setDescription(itemDB.getDescription());
            itemToUpdate.setItem_link(itemDB.getItem_link());
            itemToUpdate.setImg_url(itemDB.getImg_url());
            itemToUpdate.setList_name(itemDB.getList_name());
        });
        return "Item UPDATED";
    }

    // Delete an item from the database
    // @Transactional ensures the operation is atomic
    // Finds items by name and deletes all matching items
    @Transactional
    public String deleteItem(ItemDB itemDB){
        List<ItemDB> item = itemDAO.findItemByName(itemDB.getName());
        item.stream().forEach(s-> {
            itemDAO.delete(s);
        });
        return "Item DELETED";
    }
}
