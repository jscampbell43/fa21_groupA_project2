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
        UserDB user = userDAO.findUserByName(username);
        if (user == null){
            throw new UsernameNotFoundException("User Not Found!");
        }
        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole()) // or .authorities(...)
            .build();
    }
        
}