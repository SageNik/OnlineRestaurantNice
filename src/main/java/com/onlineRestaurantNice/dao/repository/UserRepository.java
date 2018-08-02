package com.onlineRestaurantNice.dao.repository;

import com.onlineRestaurantNice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository for interact with database by Jpa
 */
public interface UserRepository extends JpaRepository<User,Long>{

    /**
     * Find one user by username in database
     * @param username name of user
     * @return found user if exist or null if not
     */
    User findByUsername(String username);
}
