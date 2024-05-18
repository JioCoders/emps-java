package com.springboot.empc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String conString;

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(conString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "tododb");
    }

    // @Bean
    // MongoOperations mongoTemplate(MongoClient mongoClient) {
    // MongoTemplate template = new MongoTemplate(mongoClient, "...");
    // template.setEntityCallbacks(EntityCallbacks.create(...));
    // }
}