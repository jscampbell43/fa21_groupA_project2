package com.example.Wishlist.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<UserDB, Long> {

    @Query("FROM UserDB WHERE username = ?1")
    UserDB findUserByname(String username);

}
