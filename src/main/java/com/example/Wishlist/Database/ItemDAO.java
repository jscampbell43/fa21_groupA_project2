package com.example.Wishlist.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemDAO extends JpaRepository<ItemDB, Long> {

    @Query("FROM ItemDB WHERE user_id = ?1")
    List<ItemDB> findItemByUser(int user_id);

    @Query("FROM ItemDB WHERE id = ?1")
    List<ItemDB> findByItemId(int id);

    @Query("FROM ItemDB WHERE list_name = ?1 AND user_id = ?2")
    List<ItemDB> findItemByList(String list_name, int user_id);

    @Query("FROM ItemDB WHERE name = ?1 OR id = ?1")
    List<ItemDB> findItemBySearch(String entry);

    @Modifying
    @Query(value = "INSERT INTO ITEMS (user_id, name, description, item_link, img_url, List_name) VALUES (:uid,:name,:description,:link,:url,:list)", nativeQuery = true)
    @Transactional
    void addItem(@Param("uid") int user_id, @Param("name") String name, @Param("description") String desc, @Param("link") String link, @Param("url") String url, @Param("list") String list);
}
