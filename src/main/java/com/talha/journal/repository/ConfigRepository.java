package com.talha.journal.repository;

import com.talha.journal.entity.ConfigJournal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<ConfigJournal, ObjectId> {
}
