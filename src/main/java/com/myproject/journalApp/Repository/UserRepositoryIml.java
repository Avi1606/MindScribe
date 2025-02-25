package com.myproject.journalApp.Repository;

import com.myproject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class UserRepositoryIml {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> GetUserForSA() {
        Query query = new Query();
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(
//                  Criteria.where("email").exists(true)
//                , Criteria.where("sentimentalAnaylysis").exists(true)));

        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"));
        query.addCriteria(Criteria.where("sentimentalAnaylysis").is(true));

        return mongoTemplate.find(query, User.class);

    }

}
