package com.onlineRestaurantNice.controller;

import com.onlineRestaurantNice.service.interfaces.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This is class is used for unit testing Home controller
 */
@WebAppConfiguration
public class HomeControllerUnitTest {

    private MockMvc mockMvc;
    private String username = "admin";
    private String password = "555";
    private String selectRole = "ROLE_ADMIN";
    private MultiValueMap<String, String> param = null;

    @InjectMocks
    private HomeController homeController;
    @Mock
    private UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        param = new LinkedMultiValueMap<>();
        param.add("usernameReg", username);
        param.add("passwordReg", password);
        param.add("selectRole", selectRole);
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(homeController).build();
    }

    @Test
    public void testStartPage() throws Exception{

        mockMvc.perform(get("/"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect: /login"));
    }

    @Test
    public void testLoginPageWithOutError() throws Exception{

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title","Login Page"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("logins"));
    }

    @Test
    public void testLoginPageWithError() throws Exception{

        mockMvc.perform(get("/login").param("error", "Sorry..."))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title","Login Page"))
                .andExpect(model().attribute("error","Invalid name or password"))
                .andExpect(view().name("logins"));
    }

    @Test
    public void testRegistrationPage() throws Exception{

        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registrations"));
    }

    @Test
    public void testRegisteredUserWithOutError() throws Exception{

        when(userService.registerUser(username,password,selectRole)).thenReturn(true);

        mockMvc.perform(post("/registration").params(param))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect: /login"));
    }

    @Test
    public void testRegisteredUserWithError() throws Exception{

        when(userService.registerUser(username,password,selectRole)).thenReturn(false);

        mockMvc.perform(post("/registration").params(param))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", "Sorry, user with this name already exist"))
                .andExpect(view().name("registrations"));
    }

    @Test
    public void testHomePage() throws Exception{

        mockMvc.perform(get("/OnlineRestaurantNice/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("homes"));
    }
}
