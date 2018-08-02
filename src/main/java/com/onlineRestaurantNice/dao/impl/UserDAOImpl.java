package com.onlineRestaurantNice.dao.impl;

import com.onlineRestaurantNice.dao.interfaces.UserDAO;
import com.onlineRestaurantNice.dao.repository.UserRepository;
import com.onlineRestaurantNice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This is class is used for interaction with repository User entity
 */
@Repository
public class UserDAOImpl implements UserDAO {

    /**
     * This is used for interaction with user repository
     */
    private UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository UserRepository variable
     */
    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
