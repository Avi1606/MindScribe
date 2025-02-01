package com.myproject.journalApp.Controllers;


import com.myproject.journalApp.Services.UserServices;
import com.myproject.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userservices;

    @PostMapping
    public User CreateUser(@RequestBody User newuser) {
        userservices.SaveUser(newuser);
        return newuser;
    }

    @GetMapping
    public List<User> getalluser() {
        return userservices.getalluser();
    }

    @GetMapping("id/{myid}")
    public User getbyid(@PathVariable ObjectId myid) {
        return userservices.getbyid(myid).orElse(null);
    }

    @DeleteMapping
    public boolean deleteall() {
        userservices.deleteall();
        return true;
    }

    @DeleteMapping("delete/id/{myid}")
    public boolean deletebyid(@PathVariable ObjectId myid) {
        userservices.deletebyid(myid);
        return true;
    }


    @PutMapping("/{username}")
    public ResponseEntity<?> update(@RequestBody User user,@PathVariable String username) {
        User userindb = userservices.findbyuserName(username);
        if (userindb != null) {
            userindb.setPassword(user.getPassword());
            userindb.setUsername(user.getUsername());
            userservices.SaveUser(userindb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
