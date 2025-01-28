package com.myproject.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor  // Add this to allow creation without parameters
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String username;  // Remove @NonNull from here

    private String password;  // Remove @NonNull from here

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();  // Fixed capitalization

    // Add a constructor for required fields
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}