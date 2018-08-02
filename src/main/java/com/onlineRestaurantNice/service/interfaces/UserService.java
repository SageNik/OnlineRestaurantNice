package com.onlineRestaurantNice.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * This is service interface provided methods to produce business logic for entity User
 */
public interface UserService extends UserDetailsService{

    /**
     * Register new User in application
     * @param username name for new user
     * @param password password for new user
     * @param selectedRole choose role for new user
     * @return true if new user has been successfully registered or false if not
     */
    Boolean registerUser(String username, String password, String selectedRole);
}
