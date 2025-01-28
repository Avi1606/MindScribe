package com.myproject.journalApp.Controller;

import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.myproject.journalApp.Services.JournalEntryServices;
import com.myproject.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryServices ServiceEntry; // it is null without adding @Component in that class

    @PostMapping
    public JournalEntry CreateEntry(@RequestBody JournalEntry MyEntry) {
        MyEntry.setDate(LocalDateTime.now());
        ServiceEntry.SaveEntry(MyEntry);
        return MyEntry;
    }

    @GetMapping
    public List<JournalEntry> getall() {
        return ServiceEntry.GettAll() ;
    }

    @GetMapping("id/{MyId}")
    public JournalEntry findbyid(@PathVariable ObjectId MyId) {
        return ServiceEntry.FindById(MyId).orElse(null);
    }

    @DeleteMapping("id/{MyId}")
    public boolean deletebyid(@PathVariable ObjectId MyId) {
        ServiceEntry.DeleteById(MyId);
        return true;
    }


    @DeleteMapping
    public boolean deletall() {
        ServiceEntry.DeleteAll();
        return true;
    }


}
