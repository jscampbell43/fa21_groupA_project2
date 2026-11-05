package com.example.Wishlist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object for User Update request
 * Used when requesting Update User data from the API consumer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private int userId;
    private String username;
    private String password;
}