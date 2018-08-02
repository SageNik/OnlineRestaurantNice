package com.onlineRestaurantNice.entity;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Role entity
 */
@Data
@Entity
@Table(name = "role")
@ToString(exclude = "users")
public class Role {

    /**
     * Identification number of this role
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * List of users who has this role
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    /**
     * Role name
     */
    private String role;

    /**
     * Constructor of role
     * @param role role name
     */
    public Role(String role) {

        this.role = role;
        this.users = new ArrayList<>();
    }

    /**
     * Constructor of role (empty)
     */
    public Role(){}
}
