package com.myproject.journalApp.Repository;


import com.myproject.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//public interface UserRepository extends MongoRepository<User, ObjectId> {
//
//    User findbyusername(String username);
//
//}
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);
}


