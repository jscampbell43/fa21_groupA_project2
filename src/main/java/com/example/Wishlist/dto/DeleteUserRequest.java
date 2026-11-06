package com.example.Wishlist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object for User Delete request
 * Used when requesting Delete User data from the API consumer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {
    private int userId;
    private String username;
    private String password;
}