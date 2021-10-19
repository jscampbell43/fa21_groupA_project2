package com.example.Wishlist.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.*;

public interface UserDAO extends JpaRepository<UserDB, Long> {

    @Query("FROM UserDB WHERE username = ?1")
    UserDB findUserByname(String username);

    @Modifying
    @Query(value = "INSERT into UserDB (username,password) VALUES (:username,:password)", nativeQuery = true)
    @Transactional
    void insertUser(@Param("username") String username, @Param("password") String password);

}
