package com.example.Wishlist.controllers;


import com.example.Wishlist.Database.ItemDAO;
import com.example.Wishlist.Database.ItemDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/items")
public class ItemController {
    private List<ItemDB> item;
    private ItemDAO itemDAO;
    public ItemController(ItemDAO itemDAO){
        this.itemDAO = itemDAO;
    }

    @RequestMapping(value = "/allItems")
    public List<ItemDB> getAllItems(){
        return itemDAO.findAll();
    }

    @RequestMapping(value = "/user/{user_id}")
    public List<ItemDB> getitemByUser(@PathVariable int user_id) {return itemDAO.findItemByUser(user_id); }

    @RequestMapping(value = "/list/{list_name}/user/{user_id}")
    public List<ItemDB> getItemsByList(@PathVariable String list_name, @PathVariable int user_id) {return  itemDAO.findItemByList(list_name, user_id);}

    @GetMapping(value = "/itemSearch/{searchvar}" )
    public List<ItemDB> getItemById(@PathVariable String searchvar){
        return itemDAO.findItemBySearch(searchvar);
    }
//    @Query("FROM ItemDB WHERE user_id = ?1")
//    List<ItemDB> findItemByUser(int user_id);
}