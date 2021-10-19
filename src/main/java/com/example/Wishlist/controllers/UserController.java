package com.example.Wishlist.controllers;

import com.example.Wishlist.Database.UserDAO;
import com.example.Wishlist.Database.UserDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private List<UserDB> user;
    private UserDAO userDAO;
    public UserController(UserDAO userDAO) { this.userDAO = userDAO; }

    @GetMapping(value = "/allUsers")
    public List<UserDB> getAllUsers(){return userDAO.findAll();}


    @GetMapping(value = "/{username}")
    public UserDB findUserByName(@PathVariable String username){
        return userDAO.findUserByname(username);
    }

    // @PostMapping(value = "/signup")
    // @ResponseBody
    // public void insertNewUser(@RequestParam String username, @RequestParam String password){userDAO.insertUser(username, password);}
    

}