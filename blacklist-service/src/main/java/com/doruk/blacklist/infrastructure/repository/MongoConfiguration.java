package com.doruk.blacklist.infrastructure.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoConfiguration {

    @Value("${mongo-db-connection-string}")
    private String mongoDbConnectionString;
    @Value("${mongo-db-name}")
    private String mongoDbName;
    @Value("${mongo-collection-name}")
    private String mongoCollectionName;

    public String getMongoDbConnectionString() {
        return mongoDbConnectionString;
    }

    public void setMongoDbConnectionString(String mongoDbConnectionString) {
        this.mongoDbConnectionString = mongoDbConnectionString;
    }

    public String getMongoDbName() {
        return mongoDbName;
    }

    public void setMongoDbName(String mongoDbName) {
        this.mongoDbName = mongoDbName;
    }

    public String getMongoCollectionName() {
        return mongoCollectionName;
    }

    public void setMongoCollectionName(String mongoCollectionName) {
        this.mongoCollectionName = mongoCollectionName;
    }
}
