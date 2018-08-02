package com.onlineRestaurantNice.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User entity
 */
@Data
@Entity
@Table(name = "user")
@ToString(exclude = "roles")
public class User {

    /**
     * Identification number of this user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Name of this user
     */
    private String username;
    /**
     * Password of this user
     */
    private String password;
    /**
     * Flag of state this user. True if user enabled or false if disabled
     */
    private Boolean enabled;

    /**
     * List of roles
     */
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        this.enabled = true;
    }
    public User(){}
}
