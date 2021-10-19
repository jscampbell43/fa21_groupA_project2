package com.example.Wishlist.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<UserDB, Long> {

    @Query("FROM UserDB WHERE id = ?1")
    UserDB findUserByUserId(int user_id);

}
