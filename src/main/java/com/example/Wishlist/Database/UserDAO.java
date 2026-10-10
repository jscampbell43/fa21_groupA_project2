package com.example.Wishlist.Database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;


// Repository interface for UserDB entity - provides database operations for user accounts
// Extends JpaRepository to get built-in CRUD methods (save, findAll, delete, etc.)
public interface UserDAO extends JpaRepository<UserDB, Long> {

    // Find a user by their unique user ID
    @Query("SELECT u FROM UserDB u WHERE u.user_id = ?1")
    UserDB findUserByUserId(int user_id);

    // Find a user by their username (used for login validation)
    @Query("SELECT u FROM UserDB u WHERE u.username = ?1")
    UserDB findUserByName(String username);

    // Insert a new user into the database using native SQL
    // @Modifying indicates this query modifies data (not just reads)
    // @Transactional ensures the operation is atomic
    @Modifying
    @Query(value = "INSERT INTO USERS (username,password, role) VALUES (:username, :password, :role)", nativeQuery = true)
    @Transactional
    void insertUser(
        @Param("username") String username, 
        @Param("password") String password, 
        @Param("role") String role
        );

}
