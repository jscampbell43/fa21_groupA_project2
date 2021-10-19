package com.example.Wishlist.Database;


import javax.persistence.*;

@Table(name="ITEMS")
@Entity
public class ItemDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int user_id;

    private String name;

    private String description;

    private String item_link;

    private String img_url;

    private String list_name;


    public ItemDB(int user_id, String name, String description, String item_link, String item_url, String list_name) {
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.item_link = item_link;
        this.img_url = item_url;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }
}
