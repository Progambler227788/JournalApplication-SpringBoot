package com.talha.journal.config;


import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement // it will tell spring to find transactional methods
public class TransactionConfig

{

    @Bean
    PlatformTransactionManager function(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }

}
