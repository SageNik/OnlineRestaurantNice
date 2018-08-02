package com.onlineRestaurantNice.service;


import com.onlineRestaurantNice.dao.interfaces.RoleDAO;
import com.onlineRestaurantNice.dao.interfaces.UserDAO;
import com.onlineRestaurantNice.entity.Role;
import com.onlineRestaurantNice.entity.User;
import com.onlineRestaurantNice.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServiceUnitTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDAO userDAO;
    @Mock
    private RoleDAO roleDAO;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    final private String username = "Ivan";
    final private String password = "555";
    final private String role = "ADMIN_ROLE";
    private User user = null;
    private User savedUser = null;
    private Role newRole = null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        newRole = new Role(role);
        user = new User(username, password);
        user.getRoles().add(newRole);
        savedUser = new User(username,password);
        savedUser.getRoles().add(newRole);
        savedUser.setId(1L);

    }

    @Test
    public void whenLoadUserByName_thenReturnUserDetails(){
        when(userDAO.findOneByUsername(username)).thenReturn(user);

        UserDetails result = userService.loadUserByUsername(username);
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
        assertTrue(result.getAuthorities().contains(new SimpleGrantedAuthority(role)));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void whenNotLoadUserByName_thenThrowUsernameNotFoundException(){
        when(userDAO.findOneByUsername(username)).thenReturn(null);

        userService.loadUserByUsername(username);
    }

    @Test
    public void whenRegisterUser_thenReturnTrue(){
        when(userDAO.findOneByUsername(username)).thenReturn(null);
        when(passwordEncoder.encode(password)).thenReturn(password);
        when(roleDAO.findRoleByName(role)).thenReturn(newRole);
        when(userDAO.saveUser(user)).thenReturn(savedUser);

        Boolean result = userService.registerUser(username,password,role);
        assertTrue(result);
    }

    @Test
    public void whenNotRegisterUser_thenReturnFalse(){
        when(userDAO.findOneByUsername(username)).thenReturn(user);
        when(passwordEncoder.encode(password)).thenReturn(password);
        when(roleDAO.findRoleByName(role)).thenReturn(newRole);
        when(userDAO.saveUser(user)).thenReturn(savedUser);

        Boolean result = userService.registerUser(username,password,role);
        assertFalse(result);
    }
}
