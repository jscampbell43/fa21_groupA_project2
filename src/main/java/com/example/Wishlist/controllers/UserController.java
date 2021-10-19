package com.example.Wishlist.controllers;

import com.example.Wishlist.Database.UserDAO;
import com.example.Wishlist.Database.UserDB;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private List<UserDB> user;
    private UserDAO userDAO;
    public UserController(UserDAO userDAO) { this.userDAO = userDAO; }

    @GetMapping(value = "/allUsers")
    public List<UserDB> getAllUsers(){return userDAO.findAll();}

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void insertNewUser(@RequestParam String username, @RequestParam String password){userDAO.insertUser(username, password);}
    
}