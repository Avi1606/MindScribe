package com.myproject.journalApp.Controllers;

import com.myproject.journalApp.Services.UserServices;
import com.myproject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> UserCreate(@RequestBody User user) {
        userServices.SaveUser(user);
        return new ResponseEntity<>("User Successfully Created", HttpStatus.CREATED);
    }
}
