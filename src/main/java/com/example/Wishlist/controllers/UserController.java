package com.example.Wishlist.controllers;

import com.example.Wishlist.Database.ItemDB;
import com.example.Wishlist.Database.UserDAO;
import com.example.Wishlist.Database.UserDB;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import jakarta.validation.constraints.NotBlank;

import com.example.Wishlist.dto.UserResponse;
import com.example.Wishlist.dto.SignupRequest;
import com.example.Wishlist.dto.LoginRequest;
import com.example.Wishlist.exceptions.UserNotFoundException;
import com.example.Wishlist.exceptions.DuplicateUsernameException;
import com.example.Wishlist.exceptions.PasswordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

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
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserDB> users = userDAO.findAll();
        List<UserResponse> userResponses = users.stream()
            .map(u -> new UserResponse(u.getUser_id(), u.getUsername(), u.getRole()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }


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
    public ResponseEntity<UserResponse> findUserByName(@PathVariable String username){
        UserDB user = userDAO.findUserByName(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        UserResponse userResponse = new UserResponse(user.getUser_id(), user.getUsername(), user.getRole());
        return ResponseEntity.ok(userResponse);
    }

    // POST /users/signup - Register a new user account
    // Parameters: username, password
    @PostMapping(value = "/signup")
    public ResponseEntity<UserResponse> insertNewUser(
        @Valid
        @RequestBody SignupRequest request
    ){
        // Check if username already exists
        UserDB existingUser = userDAO.findUserByName(request.getUsername());
        if (existingUser != null) {
            throw new DuplicateUsernameException(request.getUsername());
        }

        // Hash password before storing
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        String userRole = (request.getRole() == null || request.getRole().isEmpty() ? "USER" : request.getRole());
        userDAO.insertUser(request.getUsername(), hashedPassword, userRole);
        
        // Fetch the created user to return in response
        UserDB createdUser = userDAO.findUserByName(request.getUsername());
        UserResponse userResponse = new UserResponse(createdUser.getUser_id(), createdUser.getUsername(), createdUser.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    // POST /users/login - Authenticate a user (manual authentication for custom login page)
    // Parameters: username, password
    @PostMapping(value = "/login")
    public ResponseEntity<UserResponse> loginUser(
        @Valid
        @RequestBody LoginRequest request
    ){
        // If Username is not in database throw UserNotFoundException
        UserDB user = userDAO.findUserByName(request.getUsername());
        if (user == null) {
            throw new UserNotFoundException(request.getUsername());
        }
        // If Password does not match throw PassWordNotFoundException
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new PasswordNotFoundException(request.getUsername());
        }
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // Create authentication token
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserResponse userResponse = new UserResponse(user.getUser_id(), user.getUsername(), user.getRole());
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // GET /logout - Manual logout handler
    @GetMapping(value = "/logout")
    public ResponseEntity<Map<String, String>> logoutUser() {
        SecurityContextHolder.clearContext();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }

}
