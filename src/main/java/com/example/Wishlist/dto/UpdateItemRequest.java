package com.example.Wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Update Item request
 * Used when requesting Update Item data from the API consumer
 */
@Data //generates getters, setters, etc.
@NoArgsConstructor //generates no-argument constructor
@AllArgsConstructor //generates constructor with all fields
public class UpdateItemRequest {
    private int id;
    private int userId;
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    private String link;
    private String image;
    @NotBlank(message = "List name is required")
    private String listName;
}
