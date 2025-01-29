package com.myproject.journalApp.Services;

import com.myproject.journalApp.JournalRepository.JournalRepository;
import com.myproject.journalApp.entity.JournalEntry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class JournalEntryServices {

    @Autowired
    private JournalRepository JournalEntryRepo;


    public void SaveEntry(JournalEntry Entry) {
        JournalEntryRepo.save(Entry);
    }

    public List<JournalEntry> GettAll() {
        return JournalEntryRepo.findAll();
    }

    public Optional<JournalEntry> FindById(ObjectId id) {
//        System.out.println("ID is : " + id);
        log.info("Received data : " + JournalEntryRepo.findById(id));
        return JournalEntryRepo.findById(id);
    }

    public void DeleteById(ObjectId id) {
         JournalEntryRepo.deleteById(id);
    }

    public void DeleteAll() {
        JournalEntryRepo.deleteAll();
    }
}
