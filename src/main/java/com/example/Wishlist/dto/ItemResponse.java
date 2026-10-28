package com.example.Wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Item responses
 * Used when returning item data to the API consumer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private int id;
    private String name;
    private String description;
    private String link;
    private String image;
    private String listName;
    private int userId;
}
