package com.example.Wishlist.exceptions;

/**
 * Exception thrown when a user is not found in the database
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User not found: " + username);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
