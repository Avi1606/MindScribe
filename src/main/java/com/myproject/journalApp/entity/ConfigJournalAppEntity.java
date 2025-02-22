package com.myproject.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

public class ConfigJouranlAppEntity {
    @Data
    @Document(collection = "config_journal_app")
    public class JournalEntry {

        private String key;
        private String value;
    }
}
