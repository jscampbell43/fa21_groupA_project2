package com.example.Wishlist.exceptions;

/**
 * Exception thrown when a password is not found in the database
 */
public class PasswordNotFoundException extends RuntimeException {
    public PasswordNotFoundException(String username) {
        super("Password not found!");
    }

    public PasswordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
