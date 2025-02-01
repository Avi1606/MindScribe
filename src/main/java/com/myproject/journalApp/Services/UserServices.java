package com.myproject.journalApp.Services;

import com.myproject.journalApp.Repository.UserRepository;
import com.myproject.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices {

    @Autowired
    UserRepository userrepository ;

    public void SaveUser(User user) {
        userrepository.save(user);
    }

    public List<User> getalluser() {
        return userrepository.findAll();
    }

    public Optional<User> getbyid(ObjectId id) {

        return userrepository.findById(id);
    }

    public boolean deleteall() {
        userrepository.deleteAll();
        return true;
    }

    public boolean deletebyid(ObjectId id) {
        userrepository.deleteById(id);
        return true;
    }

    public User findbyuserName(String Username) {
        return userrepository.findByUsername(Username);
    }

}
