package com.example.Wishlist.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Transactional;
// import org.springframework.data.jpa.repository.Param;


public interface UserDAO extends JpaRepository<UserDB, Long> {

    @Query("FROM UserDB WHERE id = ?1")
    UserDB findUserByUserId(int user_id);

    // @Modifying
    // @Query(value = "INSERT into UserDB (username,password) VALUES (:username,:password)", nativeQuery = true)
    // UserDB insertUser(String username, String password);

}
