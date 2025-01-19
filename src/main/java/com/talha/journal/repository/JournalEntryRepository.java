package com.talha.journal.repository;

import com.talha.journal.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntity, ObjectId> {
}





// Controller --> Service --> Repository