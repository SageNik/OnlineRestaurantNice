package com.onlineRestaurantNice.dao.interfaces;


import com.onlineRestaurantNice.entity.Role;

/**
 * This is role dao interface provide methods for interact with repositories
 */
public interface RoleDAO {

    /**
     * Find one role by role name in database
     * @param name name of role
     * @return found role if exist or null if not
     */
    Role findRoleByName(String name);
}
