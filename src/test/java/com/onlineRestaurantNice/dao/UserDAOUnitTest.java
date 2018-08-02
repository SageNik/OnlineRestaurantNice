package com.onlineRestaurantNice.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.onlineRestaurantNice.config.AppConfig;
import com.onlineRestaurantNice.config.PersistenceConfig;
import com.onlineRestaurantNice.config.WebConfig;
import com.onlineRestaurantNice.dao.impl.UserDAOImpl;
import com.onlineRestaurantNice.dao.interfaces.UserDAO;
import com.onlineRestaurantNice.dao.repository.UserRepository;
import com.onlineRestaurantNice.entity.User;
import org.hibernate.annotations.Source;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


//@RunWith(SpringRunner.class)
//@DataJpaTest
public class UserDAOUnitTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserDAOImpl userDAO;

    private User user = null;
    final private String username = "Ivan";

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        user = new User(username, "555");

//        dataSource = new MysqlDataSource();
//        dataSource.setURL("jdbc:mysql://localhost:3306/restaurant_nice");
//        dataSource.setUser("root");
//        dataSource.setPassword("root");
//
//        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
//        builder.bind("java:comp/env/jdbc/RestaurantNiceDB",dataSource);
//        userDAO = new UserDAOImpl(userRepository);
//       User user = new User(username,"123");
//       userDAO.saveUser(user);
    }

    @Test
    public void whenFindOneByUsername_thenReturnFoundUser(){
       when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userDAO.findOneByUsername(username);
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    public void whenNotFindAnyByUsername_thenReturnNull(){
        when(userRepository.findByUsername(username)).thenReturn(null);

        User result = userDAO.findOneByUsername(username);
        assertNull(result);
    }

    @Test
    public void whenSaveUserSuccessfully_thenReturnSavedUser(){
        user.setId(1L);
        when(userRepository.save(user)).thenReturn(user);

        User result = userDAO.saveUser(user);
        assertNotNull(result);
        assertEquals(result.getUsername(),username);
    }

    @Test
    public void whenSaveUserFault_thenReturnUserForSave(){
        when(userRepository.save(user)).thenReturn(user);

        User result = userDAO.saveUser(user);
        assertNotNull(result);
        assertEquals(result.getUsername(),username);
        assertNull(result.getId());
    }
}
