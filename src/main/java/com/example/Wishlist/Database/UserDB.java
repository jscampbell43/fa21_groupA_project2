package com.example.Wishlist.Database;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;


// JPA Entity representing a user account in the USERS table
@Table(name="USERS")
@Entity
public class UserDB {

    // Primary key - auto-generated unique identifier for the user
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int user_id;

    // Unique username for login
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be longer than 3 and less than 50 characters")
    private String username;

    // User's password (NOTE: should be hashed in production)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    // Role to distinguish between Admin and regular User
    private String role;

    // Constructor to create a new user with all fields populated
    public UserDB(int user_id, String username, String password, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Default constructor required by JPA/Hibernate
    public UserDB() {
    }

   /* Commented out relationship mapping for user's wishlist items
   public Set<ItemDB> getUserItems() {
        return userItems;
    }

   public void setUserItems(Set<ItemDB> userItems) {
        this.userItems = userItems;
    }

   private Set<ItemDB> userItems;*/
    // Getter for user ID
    public int getUser_id() {
        return user_id;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}

