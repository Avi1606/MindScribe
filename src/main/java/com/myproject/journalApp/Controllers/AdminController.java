package com.myproject.journalApp.Controllers;

import com.mongodb.spi.dns.DnsWithResponseCodeException;
import com.myproject.journalApp.Repository.UserRepository;
import com.myproject.journalApp.Services.UserServices;
import com.myproject.journalApp.cache.AppCache;
import com.myproject.journalApp.entity.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserServices userServices;

    @Autowired
    AppCache appCache;

    @GetMapping("get-alluser")
    public ResponseEntity<?> getall() {
        List<User> getalluser = userServices.getalluser();
        if (!getalluser.isEmpty()) {
            return new ResponseEntity<>(getalluser, HttpStatus.OK);
        }
        return new ResponseEntity<>("No User Found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin")
    public ResponseEntity<?> create(@RequestBody User user) {
        userServices.SaveAdmin(user);
        return new ResponseEntity<>("Successfully Created Admin", HttpStatus.CREATED);
    }

    @GetMapping("clear-app-cache")
    public ResponseEntity<?> clearcache() {
        appCache.init();
        return new ResponseEntity<>("Cache Cleaned", HttpStatus.OK);
    }
}
