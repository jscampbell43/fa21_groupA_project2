package com.example.Wishlist.exceptions;

/**
 * Exception thrown when a Unauthorized User is not trying to access database
 */
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(int userId) {
        super("User is not Authorized to access this data!");
    }

    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}