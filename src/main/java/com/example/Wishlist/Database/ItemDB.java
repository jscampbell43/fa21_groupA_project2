package com.example.Wishlist.Database;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

// JPA Entity representing a wishlist item in the ITEMS table
@Table(name="ITEMS")
@Entity
public class ItemDB {

    // Primary key - auto-generated unique identifier for the item
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Foreign key referencing the user who owns this item
    private int user_id;

    // Name/title of the wishlist item
    @NotBlank(message = "Item name must not be empty")
    private String name;

    // Detailed description of the item
    private String description;

    // URL link to the item (e.g., product page)
    private String item_link;

    // URL to an image of the item
    private String img_url;

    // Name of the wishlist list this item belongs to (for organization)
    @NotBlank(message = "List name must not be empty")
    private String list_name;


    // Constructor to create a new item with all fields populated
    public ItemDB(int user_id, String name, String description, String item_link, String item_url, String list_name) {
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.item_link = item_link;
        this.img_url = item_url;
        this.list_name = list_name;
    }

    // Default constructor required by JPA/Hibernate
    public ItemDB() {

    }

    // Getter for item ID
    public int getId() {
        return id;
    }

    // Setter for item ID (needed for update operations)
    public void setId(int id) {
        this.id = id;
    }

    // Getter for user ID (owner of the item)
    public int getUser_id() {
        return user_id;
    }

    // Setter for user ID
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    // Getter for item name
    public String getName() {
        return name;
    }

    // Setter for item name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for item description
    public String getDescription() {
        return description;
    }

    // Setter for item description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for item link URL
    public String getItem_link() {
        return item_link;
    }

    // Setter for item link URL
    public void setItem_link(String item_link) {
        this.item_link = item_link;
    }

    // Getter for image URL
    public String getImg_url() {
        return img_url;
    }

    // Setter for image URL
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    // Getter for list name
    public String getList_name() {
        return list_name;
    }

    // Setter for list name
    public void setList_name(String list_name) {
        this.list_name = list_name;
    }
}
