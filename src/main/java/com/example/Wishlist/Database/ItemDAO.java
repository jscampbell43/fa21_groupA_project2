package com.example.Wishlist.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import java.util.List;

// Repository interface for ItemDB entity - provides database operations for wishlist items
// Extends JpaRepository to get built-in CRUD methods (save, findAll, delete, etc.)
public interface ItemDAO extends JpaRepository<ItemDB, Long> {

    // Find all items belonging to a specific user
    @Query("SELECT i FROM ItemDB i WHERE i.user_id = ?1")
    List<ItemDB> findItemByUser(int user_id);

    // Find items by their unique ID
    @Query("SELECT i FROM ItemDB i WHERE i.id = ?1")
    List<ItemDB> findByItemId(int id);

    // Find items in a specific list for a specific user
    @Query("SELECT i FROM ItemDB i WHERE i.list_name = ?1 AND i.user_id = ?2")
    List<ItemDB> findItemByList(String list_name, int user_id);

    // Find items by name (search functionality)
    @Query("SELECT i FROM ItemDB i WHERE i.name = ?1")
    List<ItemDB> findItemByName(String name);

    // Insert a new item into the database using native SQL
    // @Modifying indicates this query modifies data (not just reads)
    // @Transactional ensures the operation is atomic
    @Modifying
    @Query(value = "INSERT INTO ITEMS (user_id, name, description, item_link, img_url, List_name) VALUES (:uid,:name,:description,:link,:url,:list)", nativeQuery = true)
    @Transactional
    void addItem(@Param("uid") int user_id, @Param("name") String name, @Param("description") String desc, @Param("link") String link, @Param("url") String url, @Param("list") String list);
}
