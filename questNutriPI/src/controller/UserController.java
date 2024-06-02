package controller;

import java.util.List;

import model.entities.User;

public class UserController {
    public static List<User> searchUsers(String searchField, String searchTerm) {
        String searchParam = "";
        if (!searchTerm.isBlank()) {
            switch (searchField) {
                case "User":
                    searchParam = "name";
                    break;
            }
            
            searchParam += " LIKE '%" + searchTerm + "%'";
        }
        
        return User.findAll(searchParam);
    }
}