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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices; // it is null without adding @Component in that class

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            entry.setDate(LocalDateTime.now());
            journalEntryServices.SaveEntry(entry ,username);
            return new ResponseEntity<>("Successfully Created Entry", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something Wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> GetAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userServices.findbyuserName(username);
        List<JournalEntry> entries = user.getJournalEntries();

        if(entries != null && !entries.isEmpty()) {
            return new ResponseEntity<>(entries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Entry Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("id/{MyId}")
    public ResponseEntity<?> GetById(@PathVariable ObjectId MyId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userServices.findbyuserName(username);
        List<JournalEntry> data = user.getJournalEntries().stream().filter(x -> x.getId().equals(MyId)).collect(Collectors.toList());
        if (!data.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryServices.FindById(MyId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Entry Not Found", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{MyId}")
    public ResponseEntity<?> deletebyid(@PathVariable ObjectId MyId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean returnvalue = journalEntryServices.DeleteById(MyId,username);
        if (returnvalue) {
            return new ResponseEntity<>("Successfully Deleted Entry",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Entry Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{username}/{MyId}")
    public ResponseEntity<?> update(
            @RequestBody JournalEntry entry,
            @PathVariable ObjectId MyId,
            @PathVariable String username ) {

        JournalEntry entryindb = journalEntryServices.FindById(MyId).orElse(null);

        if (entryindb != null) {
            entryindb.setTitle(entry.getTitle());
            entryindb.setDate(LocalDateTime.now());
            entryindb.setContent(entry.getContent());
            journalEntryServices.SaveEntry(entryindb);
            return new ResponseEntity<>(entryindb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping
    public boolean deletall() {
        journalEntryServices.DeleteAll();
        return true;
    }






}
