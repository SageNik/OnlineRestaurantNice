package com.onlineRestaurantNice.service.impl;

import com.onlineRestaurantNice.dao.interfaces.RoleDAO;
import com.onlineRestaurantNice.dao.interfaces.UserDAO;
import com.onlineRestaurantNice.entity.Role;
import com.onlineRestaurantNice.entity.User;
import com.onlineRestaurantNice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for realising business logic by implementing @interface UserService
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * This is used for encoding password from String to BCrypt before saving in database
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    /**
     * This is used for interaction with Database through User dao layer
     */
    @Autowired
    private UserDAO userDAO;
    /**
     * This is used for interaction with Database through Role dao layer
     */
    @Autowired
    private RoleDAO roleDAO;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User currentUser = userDAO.findOneByUsername(username);
        if(currentUser == null) throw new UsernameNotFoundException("User with name:"+username+" not found");
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : currentUser.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(currentUser.getUsername(),
                currentUser.getPassword(),authorities);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Boolean registerUser(String username, String password, String selectedRole) {
        User existUser = userDAO.findOneByUsername(username);
        if(existUser != null){
            return false;
        }else{
            User newUser = new User(username, passwordEncoder.encode(password));
            newUser.getRoles().add(roleDAO.findRoleByName(selectedRole));

            User savedUser = userDAO.saveUser(newUser);
            return savedUser.getId() != null;
        }
    }

}
