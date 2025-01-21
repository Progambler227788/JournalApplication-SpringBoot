package com.talha.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}




}

// For transaction, you need replication
// connecting it to mongo db atlas

// These two classes play role for managing transactions
// Platform transaction manager
// Mongo Transaction manager
