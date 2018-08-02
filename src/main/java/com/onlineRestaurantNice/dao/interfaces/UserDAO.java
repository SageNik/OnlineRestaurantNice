package com.onlineRestaurantNice.dao.interfaces;

import com.onlineRestaurantNice.entity.User;

/**
 * This is user dao interface provide methods for interact with repositories
 */
public interface UserDAO {

    /**
     * Find one user by username in database
     * @param username user name
     * @return found user if exist or null if not
     */
    User findOneByUsername(String username);

    /**
     * Save user in database
     * @param user current user for save
     * @return saved user if user has been successfully save or current user if not
     */
    User saveUser(User user);
}
