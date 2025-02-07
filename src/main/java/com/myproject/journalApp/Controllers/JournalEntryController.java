package com.myproject.journalApp.Controllers;

import com.myproject.journalApp.Services.JournalEntryServices;
import com.myproject.journalApp.Services.UserServices;
import com.myproject.journalApp.entity.JournalEntry;
import com.myproject.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices ServiceEntry; // it is null without adding @Component in that class

    @Autowired
    private UserServices userservices;


    @GetMapping
    public ResponseEntity<?> GetAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userservices.findbyuserName(username);
        List<JournalEntry> entries = user.getJournalEntries();

        if(entries != null && !entries.isEmpty()) {
            return new ResponseEntity<>(entries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Entry Found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            entry.setDate(LocalDateTime.now());
            ServiceEntry.SaveEntry(entry ,username);
            return new ResponseEntity<>("Successfully Created Entry", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something Wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{MyId}")
    public JournalEntry findbyid(@PathVariable ObjectId MyId) {
        Optional<JournalEntry> entry = ServiceEntry.FindById(MyId);
        log.info("Journal Entry : " + entry.toString());
        return ServiceEntry.FindById(MyId).orElse(null);
    }

    @DeleteMapping("id/{username}/{MyId}")
    public boolean deletebyid(
            @PathVariable ObjectId MyId,
            @PathVariable String username) {

        ServiceEntry.DeleteById(MyId,username);
        return true;
    }


    @DeleteMapping
    public boolean deletall() {
        ServiceEntry.DeleteAll();
        return true;
    }


    @PutMapping("id/{username}/{MyId}")
    public ResponseEntity<?> update(
            @RequestBody JournalEntry entry,
            @PathVariable ObjectId MyId,
            @PathVariable String username ) {

        JournalEntry entryindb = ServiceEntry.FindById(MyId).orElse(null);

        if (entryindb != null) {
            entryindb.setTitle(entry.getTitle());
            entryindb.setDate(LocalDateTime.now());
            entryindb.setContent(entry.getContent());
            ServiceEntry.SaveEntry(entryindb);
            return new ResponseEntity<>(entryindb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
