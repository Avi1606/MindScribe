package com.myproject.journalApp.Services;

import com.myproject.journalApp.Repository.UserRepository;
import com.myproject.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Slf4j

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);  use slfj4 annotation

    public void SaveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public void SaveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER" , "ADMIN"));
        userRepository.save(user);
        log.info("HYYY");
    }

    public void SaveUser(User user) {
        userRepository.save(user);
    }


    public List<User> getalluser() {
        return userRepository.findAll();
    }

    public Optional<User> getbyid(ObjectId id) {

        return userRepository.findById(id);
    }

    public boolean deleteall() {
        userRepository.deleteAll();
        return true;
    }

    public boolean deletebyid(ObjectId id) {
        userRepository.deleteById(id);
        return true;
    }

    public User findbyuserName(String Username) {
        return userRepository.findByUsername(Username);
    }

}
