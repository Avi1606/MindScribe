package com.myproject.journalApp.Repository;

import com.myproject.journalApp.entity.ConfigJournalAppEntity;
import com.myproject.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
