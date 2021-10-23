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

@RestController
@RequestMapping(value = "/items")
public class ItemController {
    private List<ItemDB> item;
    private ItemDAO itemDAO;

    @Autowired
    private ItemService itemService;

    public ItemController(ItemDAO itemDAO){
        this.itemDAO = itemDAO;
    }
//        public ItemController(){
//        List<ItemDB> items = new ArrayList<>();
//        items.add(new ItemDB(1, "TESTITEM1", "IDKKKK", "NUN", "JNDSF", "DJKSDF"));
//        itemDAO.add(items.get(0));
////        items.add(new ItemDB(3, 3, "CST250"));
////        items.add(new ItemDB(4, 4, "CST101"));
//    }
    @RequestMapping(value = "/allItems")
    public List<ItemDB> getAllItems(){
        return itemDAO.findAll();
    }

    @GetMapping(value = "/itemSearch")
    public List<ItemDB> searchItem(@PathVariable String item_name) {
        return itemDAO.findItemBySearch(item_name);
    }

    @RequestMapping(value = "/user/{user_id}")
    public List<ItemDB> getitemByUser(@PathVariable int user_id) {return itemDAO.findItemByUser(user_id); }

    @RequestMapping(value = "/list/{list_name}/user/{user_id}")
    public List<ItemDB> getItemsByList(@PathVariable String list_name, @PathVariable int user_id) {return  itemDAO.findItemByList(list_name, user_id);}

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    @ResponseBody
    public void addItem(@RequestParam int id, @RequestParam String name, @RequestParam(required = false, defaultValue = "Description Goes Here") String desc, @RequestParam(required = false, defaultValue = "https://www.google.com/") String link, @RequestParam(required = false, defaultValue = "https://via.placeholder.com/150/") String image, @RequestParam String list) {
        itemDAO.addItem(id, name, desc, link, image, list);
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public String updateItem(@RequestBody ItemDB itemDB){
        return itemService.updateItem(itemDB);
    }
    @RequestMapping(value = "/deleteItem", method = RequestMethod.DELETE)
    public String deleteItem(@RequestBody ItemDB itemDB){
        return itemService.deleteItem(itemDB);
    }

//    @Query("FROM ItemDB WHERE user_id = ?1")
//    List<ItemDB> findItemByUser(int user_id);
}