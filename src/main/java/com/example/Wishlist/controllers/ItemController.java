package com.example.Wishlist.controllers;


import com.example.Wishlist.Database.ItemDAO;
import com.example.Wishlist.Database.ItemDB;
import com.example.Wishlist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// REST Controller for handling item-related HTTP requests
// Base path: /items
@RestController
@RequestMapping(value = "/items")
public class ItemController {
    private List<ItemDB> item;
    private ItemDAO itemDAO;

    @Autowired
    private ItemService itemService;

    // Constructor injection of ItemDAO for database operations
    public ItemController(ItemDAO itemDAO){
        this.itemDAO = itemDAO;
    }
    // Commented out test constructor
//        public ItemController(){
//        List<ItemDB> items = new ArrayList<>();
//        items.add(new ItemDB(1, "TESTITEM1", "IDKKKK", "NUN", "JNDSF", "DJKSDF"));
//        itemDAO.add(items.get(0));
////        items.add(new ItemDB(3, 3, "CST250"));
////        items.add(new ItemDB(4, 4, "CST101"));
//    }
    
    // GET /items/allItems - Retrieve all items in the database
    @RequestMapping(value = "/allItems")
    public List<ItemDB> getAllItems(){
        return itemDAO.findAll();
    }

    // GET /items/itemSearch/{item_name} - Search for items by name
    @GetMapping(value = "/itemSearch/{item_name}")
    public List<ItemDB> searchItem(@PathVariable String item_name) {
        return itemDAO.findItemByName(item_name);
    }

    // GET /items/user/{user_id} - Get all items for a specific user
    @RequestMapping(value = "/user/{user_id}")
    public List<ItemDB> getitemByUser(@PathVariable int user_id) {return itemDAO.findItemByUser(user_id); }

    // GET /items/list/{list_name}/user/{user_id} - Get items from a specific list for a user
    @RequestMapping(value = "/list/{list_name}/user/{user_id}")
    public List<ItemDB> getItemsByList(
        @PathVariable String list_name, 
        @PathVariable int user_id) 
        {return  itemDAO.findItemByList(list_name, user_id);}

    // POST /items/additem - Add a new item to the database
    // Parameters: id, name, description (optional), link (optional), image (optional), list name
    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    @ResponseBody
    public void addItem(
        @RequestParam int id, 
        @RequestParam String name, 
        @RequestParam(required = false, defaultValue = "Description Goes Here") String desc, 
        @RequestParam(required = false, defaultValue = "https://www.google.com/") String link, 
        @RequestParam(required = false, defaultValue = "https://via.placeholder.com/150/") String image, 
        @RequestParam String list) {
        itemDAO.addItem(id, name, desc, link, image, list);
    }

    // PUT /items/updateItem - Update an existing item
    // Request body contains the updated item data
    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public String updateItem(@RequestBody ItemDB itemDB){
        return itemService.updateItem(itemDB);
    }
    
    // DELETE /items/deleteItem - Delete an item from the database
    // Request body contains the item to delete
    @RequestMapping(value = "/deleteItem", method = RequestMethod.DELETE)
    public String deleteItem(@RequestBody ItemDB itemDB){
        return itemService.deleteItem(itemDB);
    }

// Commented out query method - now handled in ItemDAO
//    @Query("FROM ItemDB WHERE user_id = ?1")
//    List<ItemDB> findItemByUser(int user_id);
}