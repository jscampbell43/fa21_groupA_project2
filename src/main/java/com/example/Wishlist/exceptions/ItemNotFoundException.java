package com.example.Wishlist.exceptions;

/**
 * Exception thrown when an item is not found in the database
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(int itemId) {
        super("Item not found: " + itemId);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
