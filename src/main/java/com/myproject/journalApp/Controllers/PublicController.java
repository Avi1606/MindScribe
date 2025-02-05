package com.myproject.journalApp.Controllers;

import com.myproject.journalApp.Services.UserServices;
import com.myproject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserServices userServices;

    @Autowired
    public PublicController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User newuser) {
        userServices.SaveUser(newuser);
        return newuser;
    }
}