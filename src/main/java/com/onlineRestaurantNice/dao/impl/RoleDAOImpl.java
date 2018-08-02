package com.onlineRestaurantNice.dao.impl;

import com.onlineRestaurantNice.dao.interfaces.RoleDAO;
import com.onlineRestaurantNice.dao.repository.RoleRepository;
import com.onlineRestaurantNice.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This is class is used for interaction with repository User entity
 */
@Repository
public class RoleDAOImpl implements RoleDAO{

    /**
     * This is used for interaction with role repository
     */
    private final RoleRepository roleRepository;

    /**
     * Constructor
     * @param roleRepository Role repository variable
     */
    @Autowired
    public RoleDAOImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findOneByRole(name);
    }
}
