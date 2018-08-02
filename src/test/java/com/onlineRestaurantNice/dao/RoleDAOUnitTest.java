package com.onlineRestaurantNice.dao;

import com.onlineRestaurantNice.dao.impl.RoleDAOImpl;
import com.onlineRestaurantNice.dao.repository.RoleRepository;
import com.onlineRestaurantNice.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class RoleDAOUnitTest {

    @InjectMocks
    private RoleDAOImpl roleDAO;

    @Mock
    private RoleRepository roleRepository;
    private Role role = null;
    final private String roleName = "ROLE_ADMIN";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        role = new Role(roleName);
    }

    @Test
    public void whenFindRoleByName_thenReturnFoundRole(){
        when(roleRepository.findOneByRole(roleName)).thenReturn(role);

        Role result = roleDAO.findRoleByName(roleName);
        assertNotNull(result);
        assertEquals(roleName, result.getRole());
    }

    @Test
    public void whenNotFoundAnyRoleByName_thenReturnNull(){
        when(roleRepository.findOneByRole(roleName)).thenReturn(null);

        Role result = roleDAO.findRoleByName(roleName);
        assertNull(result);
    }
}
