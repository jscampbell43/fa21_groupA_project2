package com.example.Wishlist.controllers;

import com.example.Wishlist.Database.ItemDB;
import com.example.Wishlist.Database.UserDAO;
import com.example.Wishlist.Database.UserDB;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

// REST Controller for handling user-related HTTP requests
// Base path: /users
@RestController
@RequestMapping(value = "/users")

public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<UserDB> user;
    private UserDAO userDAO;
    
    // Constructor injection of UserDAO for database operations
    public UserController(UserDAO userDAO) { this.userDAO = userDAO; }

    // GET /users/allUsers - Retrieve all users in the database
    @GetMapping(value = "/allUsers")
    public List<UserDB> getAllUsers(){return userDAO.findAll();}


   /* Commented out method to get user's saved items
   @GetMapping(value = "/allSavedItems")
    public Set<ItemDB> getAllSavedItems(@PathVariable int user_id){
        UserDB user = userDAO.findUserByUserId(user_id);
        return user.getUserItems();
    }*/
    /* Commented out method to save item to wishlist
    @PostMapping("/saveItemToWishlist")
    public void saveItemToWishlist(HttpServletResponse response, ) {
        UserDB  user = userDAO.findUserByUserId()
    }*/


    // GET /users/{username} - Find a user by username (used for login)
    @GetMapping(value = "/{username}")
    public UserDB findUserByName(@PathVariable String username){
        return userDAO.findUserByName(username);
    }

    // POST /users/signup - Register a new user account
    // Parameters: username, password
    @PostMapping(value = "/signup")
     @ResponseBody
     public void insertNewUser(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String role
        ){
        // Hash password before storing
        String hashedPassword = passwordEncoder.encode(password);
        String userRole = (role == null || role.isEmpty() ? "USER" : role);
        userDAO.insertUser(username, hashedPassword, userRole);
    }

    // POST /users/login - Authenticate a user
    // Parameters: username, password
    @PostMapping(value = "/login")
    @ResponseBody
    public String loginUser(
        @RequestParam String username,
        @RequestParam String password
        ){
        UserDB user = userDAO.findUserByName(username);
        if (user == null) {
            return "User not found";
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid password";
        }
    }

}