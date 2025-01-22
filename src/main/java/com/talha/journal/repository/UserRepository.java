package com.talha.journal.repository;

import com.talha.journal.entity.JournalEntity;
import com.talha.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);

    void deleteByUserName(String username);
}





// Controller --> Service --> Repository