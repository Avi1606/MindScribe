package com.myproject.journalApp.Services;

import com.myproject.journalApp.Repository.UserRepository;
import com.myproject.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void SaveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
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
