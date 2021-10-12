package com.example.Wishlist.controllers;

import com.example.Wishlist.Database.UserDAO;
import com.example.Wishlist.Database.UserDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserDAO userDAO;
    @GetMapping(value = "/allUsers")
    public List<UserDB> getAllUsers(){return userDAO.findAll();}
}
