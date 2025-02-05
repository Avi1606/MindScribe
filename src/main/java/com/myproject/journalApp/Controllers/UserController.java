package com.myproject.journalApp.Controllers;


import com.myproject.journalApp.Repository.UserRepository;
import com.myproject.journalApp.Services.UserServices;
import com.myproject.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userservices;

    @Autowired
    UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username  = authentication.getName();
        User userindb = userservices.findbyuserName(username);
        if (userindb != null) {
            userindb.setPassword(user.getPassword());
            userindb.setUsername(user.getUsername());
            userservices.SaveUser(userindb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deletebyid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
