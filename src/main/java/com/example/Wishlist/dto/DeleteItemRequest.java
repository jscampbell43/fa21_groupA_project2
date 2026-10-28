package com.example.Wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object for Delete Item Request
 * Used when Deleting item data from the API consumer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteItemRequest {
    @Positive(message = "ID must be positive")
    private int id;
}
