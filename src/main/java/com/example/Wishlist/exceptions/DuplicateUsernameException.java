package com.example.Wishlist.exceptions;
 
public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String username) {
        super("Username already exists: " + username);
    }

    public DuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}