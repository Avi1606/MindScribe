package com.myproject.journalApp.Controllers;


import com.myproject.journalApp.Repository.UserRepository;
import com.myproject.journalApp.Services.UserServices;
import com.myproject.journalApp.Services.WeatherService;
import com.myproject.journalApp.api.response.WeatherResponce;
import com.myproject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userservices;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username  = authentication.getName();
        User userindb = userservices.findbyuserName(username);
        if (userindb != null) {
            userindb.setPassword(user.getPassword());
            userindb.setUsername(user.getUsername());
            userindb.setEmail(user.getEmail());
            userservices.SaveNewUser(userindb);
        }
        return new ResponseEntity<>("User Successfully Updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deletebyid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>("User Successfully Deleted",HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> Greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String Username = authentication.getName();
        WeatherResponce weatherResponce = weatherService.getWeather("Mumbai");
        String greeting = "";
        if (weatherResponce != null) {
            greeting = "Hi " + Username + " ! Weather feels like : " + weatherResponce.getCurrent().getFeelslike();
            return new ResponseEntity<>(greeting, HttpStatus.OK);
        }
        return new ResponseEntity<>("Hi " + Username + " !" + greeting, HttpStatus.OK);
    }
}
