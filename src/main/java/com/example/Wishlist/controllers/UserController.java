package com.example.Wishlist.controllers;

import com.example.Wishlist.Database.ItemDB;
import com.example.Wishlist.Database.UserDAO;
import com.example.Wishlist.Database.UserDB;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private List<UserDB> user;
    private UserDAO userDAO;
    public UserController(UserDAO userDAO) { this.userDAO = userDAO; }

    @GetMapping(value = "/allUsers")
    public List<UserDB> getAllUsers(){return userDAO.findAll();}


   /* @GetMapping(value = "/allSavedItems")
    public Set<ItemDB> getAllSavedItems(@PathVariable int user_id){
        UserDB user = userDAO.findUserByUserId(user_id);
        return user.getUserItems();
    }*/
    /*@PostMapping("/saveItemToWishlist")
    public void saveItemToWishlist(HttpServletResponse response, ) {
        UserDB  user = userDAO.findUserByUserId()
    }*/


    @GetMapping(value = "/{username}")
    public UserDB findUserByName(@PathVariable String username){
        return userDAO.findUserByName(username);
    }

    // @PostMapping(value = "/signup")
    // @ResponseBody
    // public void insertNewUser(@RequestParam String username, @RequestParam String password){userDAO.insertUser(username, password);}
    
}