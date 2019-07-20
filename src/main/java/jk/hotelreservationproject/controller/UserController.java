package jk.hotelreservationproject.controller;

import jk.hotelreservationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }





}
