package com.example.Wishlist.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemDAO extends JpaRepository<ItemDB, Long> {

    @Query("FROM ItemDB WHERE user_id = ?1")
    List<ItemDB> findItemByUser(int user_id);
}
