package com.onlineRestaurantNice.controller;

import com.onlineRestaurantNice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Home Controller for mapping '/login', '/registration', 'OnlineRestaurantNiceRun/home' requests
 */
@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String startPage (){
        return "redirect: /login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam(value="error",required = false)String error, Model model){

        if(error != null){
            model.addAttribute("error","Invalid name or password");
        }
        model.addAttribute("title", "Login Page");
        return "logins";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("title", "Registration Page");
        return "registrations";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userRegistration(@RequestParam("usernameReg")String usernameReg,
                                   @RequestParam("passwordReg")String passwordReg,
                                   @RequestParam("selectRole")String selectedRole, Model model){

        if(userService.registerUser(usernameReg, passwordReg,selectedRole )){
            return "redirect: /login";
        }
            else {
            model.addAttribute("error", "Sorry, user with this name already exist");
            return "registrations";
        }
    }

    @RequestMapping(value = "/OnlineRestaurantNice/home",method = RequestMethod.GET)
    public String home(){
        return "homes";
    }
}
