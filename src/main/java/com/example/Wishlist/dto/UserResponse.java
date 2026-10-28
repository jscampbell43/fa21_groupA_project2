package com.example.Wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for User responses
 * Used when returning user data to the API consumer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private int userId;
    private String username;
    private String role;
}
