package com.myproject.journalApp.Services;

import com.myproject.journalApp.JournalRepository.JournalRepository;
import com.myproject.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryServices {

    @Autowired
    private JournalRepository JournalEntryRepo;


    public void SaveEntry(JournalEntry Entry) {
        JournalEntryRepo.save(Entry);
    }

    public List<JournalEntry> getall() {
        return JournalEntryRepo.findAll();
    }

    public Optional<JournalEntry> FindById(ObjectId id) {
        return JournalEntryRepo.findById(id);
    }

    public void DeleteById(ObjectId id) {
         JournalEntryRepo.deleteById(id);
    }

    public void DeleteAll() {
        JournalEntryRepo.deleteAll();
    }
}
