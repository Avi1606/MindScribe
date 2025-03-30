package com.myproject.journalApp.Controllers;

import com.myproject.journalApp.Services.UserServices;
import com.myproject.journalApp.Util.JwtUtil;
import com.myproject.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserServices userServices;

    @Autowired
    public PublicController(UserServices userServices) {
        this.userServices = userServices;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        userServices.SaveNewUser(user);
        return new ResponseEntity<>("User Successfully signin", HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
       try{
           authenticationManager.authenticate(new
                   UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
           UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
           String jwt = jwtUtil.generateToken(userDetails.getUsername());
           return new ResponseEntity<>(jwt, HttpStatus.OK);
       }catch(Exception e){
           log.error("Exception occurred while creating AuthenticationToken", e);
           return new ResponseEntity<>("Incorrect Username and Password", HttpStatus.UNAUTHORIZED);
        }

    }
}
