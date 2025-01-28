package com.myproject.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
//@Getter
//@Setter

@Data
@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private ObjectId Id;
    private String title;
    private String content;
    private LocalDateTime Date;

}
