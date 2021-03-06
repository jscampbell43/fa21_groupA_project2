package com.example.Wishlist.Database;


import javax.persistence.*;
import java.util.Set;


@Table(name="USERS")
@Entity
public class UserDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int user_id;

    private String username;

    private String password;

    public UserDB(int user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public UserDB() {
    }

   /* public Set<ItemDB> getUserItems() {
        return userItems;
    }*/

   /* public void setUserItems(Set<ItemDB> userItems) {
        this.userItems = userItems;
    }*/

   // private Set<ItemDB> userItems;
    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

