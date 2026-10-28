package com.example.Wishlist.controllers;


import com.example.Wishlist.Database.ItemDAO;
import com.example.Wishlist.Database.ItemDB;
import com.example.Wishlist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

import com.example.Wishlist.dto.ItemResponse;
import com.example.Wishlist.dto.AddItemRequest;
import com.example.Wishlist.dto.UpdateItemRequest;
import com.example.Wishlist.dto.DeleteItemRequest;
import com.example.Wishlist.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

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
    
    // GET /items/allItems - Retrieve all items in the database
    @GetMapping(value = "/allItems")
    public ResponseEntity<List<ItemResponse>> getAllItems(){
        List<ItemDB> items = itemDAO.findAll();
        List<ItemResponse> itemResponses = items.stream()
            .map(item -> new ItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getItem_link(),
                item.getImg_url(),
                item.getList_name(),
                item.getUser_id()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemResponses);
    }

    // GET /items/user/{user_id} - Get all items for a specific user
    @GetMapping(value = "/user/{user_id}")
    public ResponseEntity<List<ItemResponse>> getitemByUser(@PathVariable int user_id){
        List<ItemDB> items = itemDAO.findItemByUser(user_id);
        List<ItemResponse> itemResponses = items.stream()
            .map(item -> new ItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getItem_link(),
                item.getImg_url(),
                item.getList_name(),
                item.getUser_id()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemResponses);
    }
    
    // GET /items/list/{list_name}/user/{user_id} - Get items from a specific list for a user
    @GetMapping(value = "/list/{list_name}/user/{user_id}")
    public ResponseEntity<List<ItemResponse>> getItemsByList(
        @PathVariable String list_name, 
        @PathVariable int user_id) 
        {
            List<ItemDB> items = itemDAO.findItemByList(list_name, user_id);
            List<ItemResponse> itemResponses = items.stream()
            .map(item -> new ItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getItem_link(),
                item.getImg_url(),
                item.getList_name(),
                item.getUser_id()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemResponses);
        }

    // POST /items/additem - Add a new item to the database
    // Parameters: id, name, description (optional), link (optional), image (optional), list name
    @PostMapping(value = "/additem")
        public ResponseEntity<ItemResponse> addItem(
            @Valid
            @RequestBody AddItemRequest request
        ){
            // Store New Item
            itemDAO.addItem(
                request.getUserId(),      // uid
                request.getName(),         // name
                request.getDescription(),  // description
                request.getLink(),         // link
                request.getImage(),        // url
                request.getListName()      // list
            );
            ItemResponse itemResponse = new ItemResponse(
                0, // Update this after refactoring DAO
                request.getName(),
                request.getDescription(),
                request.getLink(),
                request.getImage(),
                request.getListName(),
                request.getUserId());
            return ResponseEntity.status(HttpStatus.CREATED).body(itemResponse);
        }
        

    // PUT /items/updateItem - Update an existing item
    // Request body contains the updated item data
    @PutMapping(value = "/updateItem")
    public ResponseEntity<ItemResponse> updateItem(
        @Valid
        @RequestBody UpdateItemRequest request
    ){
        // Check if item exists
        List<ItemDB> existingItems = itemDAO.findByItemId(request.getId());
        if (existingItems == null || existingItems.isEmpty()){
            throw new ItemNotFoundException(request.getId());
        }
        // Reconstruct ItemDB Entity
        ItemDB item = new ItemDB(
            request.getUserId(),
            request.getName(),
            request.getDescription(),
            request.getLink(),
            request.getImage(),
            request.getListName());
        // Update ID separately
        item.setId(request.getId());
        // Call the Service Method
        String result = itemService.updateItem(item);
        // Create Response
        ItemResponse itemResponse = new ItemResponse(
            item.getId(),
            item.getName(),
            item.getDescription(),
            item.getItem_link(),
            item.getImg_url(),
            item.getList_name(),
            item.getUser_id()
        );
        // Return Response
        return ResponseEntity.ok(itemResponse)  
    }
    
    // DELETE /items/deleteItem - Delete an item from the database
    // Request body contains the item to delete
    @DeleteMapping(value = "/deleteItem")
    public ResponseEntity<Map<String, String>> deleteItem(@Valid @RequestBody DeleteItemRequest request){
        List<ItemDB> existingItems = itemDAO.findByItemId(request.getId());
        // Check if item exists
        if (existingItems == null || existingItems.isEmpty()){
            throw new ItemNotFoundException(request.getId());
        }
        // If item exists delete first occurence in list
        ItemDB itemToDelete = existingItems.get(0);
        String result = itemService.deleteItem(itemToDelete);
        // Create Map to hold Response, Return Map
        Map<String, String> response = new HashMap<>();
        response.put("message", result);  // or use a custom message
        return ResponseEntity.ok(response);
    }
}