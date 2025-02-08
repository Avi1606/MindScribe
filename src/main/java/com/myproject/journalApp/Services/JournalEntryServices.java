package com.myproject.journalApp.Services;

import com.myproject.journalApp.Repository.JournalRepository;
import com.myproject.journalApp.entity.JournalEntry;
import com.myproject.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class JournalEntryServices {

    @Autowired
    private JournalRepository JournalEntryRepo;

    @Autowired
    private UserServices userservices;

    @Transactional
    public void SaveEntry(JournalEntry Entry, String username) {
        try {
            User user = userservices.findbyuserName(username);
            JournalEntry saved = JournalEntryRepo.save(Entry);
            user.getJournalEntries().add(saved);
            userservices.SaveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving the entry",e);
        }
    }

    public void SaveEntry(JournalEntry journalentry) { //save the existing user
        JournalEntryRepo.save(journalentry);
    }

    public List<JournalEntry> GettAll() {
        return JournalEntryRepo.findAll();
    }

    public Optional<JournalEntry> FindById(ObjectId id) {
//        System.out.println("ID is : " + id);
        //log.info("Received data : " + JournalEntryRepo.findById(id));
        return JournalEntryRepo.findById(id);
    }

    public boolean DeleteById(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userservices.findbyuserName(username);
            removed = user.getJournalEntries().removeIf(journalentry -> journalentry.equals(id));// lambda expression that removes the entry with the given id
            if (removed) {
                userservices.SaveUser(user);
                JournalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An Error Occurred While Deleting",e);
        }
        return removed;
    }

    public void DeleteAll() {
        JournalEntryRepo.deleteAll();
    }
}
