package com.onlineRestaurantNice.dao.repository;

import com.onlineRestaurantNice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Role repository for interact with database by Jpa
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find one role by role name in database
     * @param role role name
     * @return found role if exist or null if not
     */
    Role findOneByRole(String role);
}
