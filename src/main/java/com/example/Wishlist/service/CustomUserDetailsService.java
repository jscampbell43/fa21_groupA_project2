package com.example.Wishlist.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Wishlist.Database.UserDB;
import com.example.Wishlist.Database.UserDAO;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
        
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("=== DEBUG: Attempting to load user: " + username);
        UserDB user = userDAO.findUserByName(username);
        if (user == null){
            System.out.println("=== DEBUG: User not found in database: " + username);
            throw new UsernameNotFoundException("User Not Found!");
        }
        System.out.println("=== DEBUG: User found: " + user.getUsername());
        System.out.println("=== DEBUG: Stored password hash: " + user.getPassword());
        System.out.println("=== DEBUG: User role: " + user.getRole());
        
        UserDetails userDetails = User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
        
        System.out.println("=== DEBUG: UserDetails created with username: " + userDetails.getUsername());
        System.out.println("=== DEBUG: UserDetails password: " + userDetails.getPassword());
        System.out.println("=== DEBUG: UserDetails authorities: " + userDetails.getAuthorities());
        
        return userDetails;
    }
        
}