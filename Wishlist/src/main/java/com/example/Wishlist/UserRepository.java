package com.example.Wishlist;
import com.example.Wishlist.Database.UserDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDB, Long> {

    @Query("FROM UserDB WHERE username = ?1")
    UserDB findUserByname(String username);
}