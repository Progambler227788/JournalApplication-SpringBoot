package com.talha.journal.repository;

import com.talha.journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

// for criteria based queries
// for complex queries
public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate; // provides abstraction to interact with database




 public List<User> getUsersForSentiment(){
  // import should be correct
     Query query = new Query();
//     query.addCriteria(Criteria.where("username").is("helo"));
     // you can fetch users whose field sentimentAnalysis is of boolean type

     query.addCriteria(Criteria.where("email").exists(true));
     query.addCriteria(Criteria.where("email").ne(null).ne(""));
     query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"));
     query.addCriteria(Criteria.where("sentimenAnalysis").is(true));



     return   mongoTemplate.find(query, User.class);

     // there is collection name on User class so no need to mention collection name for this purpose

     }
}
