package com.example.Wishlist.Database;


import javax.persistence.*;
import java.util.List;

@Table(name="ITEMS")
@Entity
public class ItemDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int user_id;

    private String name;

    private String description;

    private String item_link;

    private String item_url;

    private String list_name;

    public List<ItemDB> getItems() {
        return items;
    }

    public void setItems(List<ItemDB> items) {
        this.items = items;
    }

    private List<ItemDB> items;


    public ItemDB(int user_id, String name, String description, String item_link, String item_url, String list_name) {
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.item_link = item_link;
        this.item_url = item_url;
        this.list_name = list_name;
    }

    public ItemDB() {

    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItem_link() {
        return item_link;
    }

    public void setItem_link(String item_link) {
        this.item_link = item_link;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

}

