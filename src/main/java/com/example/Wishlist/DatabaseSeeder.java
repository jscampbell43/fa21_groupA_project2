package com.example.Wishlist;

import com.example.Wishlist.Database.ItemDAO;
import com.example.Wishlist.Database.ItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

    @Component
    public class DatabaseSeeder implements CommandLineRunner {
        private ItemDAO itemDAO;

        @Autowired
        public DatabaseSeeder(ItemDAO itemDAO) {
            this.itemDAO = itemDAO;
        }

        @Override
        public void run(String... strings) throws Exception {
            itemDAO.save(new ItemDB(1, "Test1", "IDKK", "DKFSD", "adsfsdf", "asdfasdf"));
            itemDAO.save(new ItemDB(1, "Test2", "IDKjkjlK", "Dl;kjjKFSD", "adsfl;kjljsdf", "as;jkl;jdfasdf"));
        }
    }
