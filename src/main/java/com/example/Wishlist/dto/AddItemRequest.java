package com.example.Wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Add Item request
 * Used when requesting Add Item data from the API consumer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {
    private int userId;
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    private String link;
    private String image;
    @NotBlank(message = "List name is required")
    private String listName;
}
