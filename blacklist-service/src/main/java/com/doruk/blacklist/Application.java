package com.doruk.blacklist;

import com.doruk.blacklist.application.BlacklistService;
import com.doruk.blacklist.domain.BlacklistFactory;
import com.doruk.blacklist.domain.BlacklistRepository;
import com.doruk.blacklist.domain.DefaultBlacklistFactory;
import com.doruk.blacklist.domain.DefaultBlacklistService;
import com.doruk.blacklist.infrastructure.repository.MongoBlacklistRepository;
import com.doruk.blacklist.infrastructure.repository.MongoConfiguration;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    BlacklistService blacklistService(BlacklistRepository blacklistRepository, BlacklistFactory blacklistFactory) {
        return new DefaultBlacklistService(blacklistRepository, blacklistFactory);
    }

    @Bean
    BlacklistFactory blacklistFactory() {
        return new DefaultBlacklistFactory();
    }

    @Bean
    public MongoClient getMongoClient(MongoConfiguration configuration) {
        final String mongoConnectionString = configuration.getMongoDbConnectionString();
        return MongoClients.create(mongoConnectionString);
    }

    @Bean
    BlacklistRepository getCourierTrackingRepository(MongoConfiguration mongoConfiguration, MongoClient mongoClient) {
        return new MongoBlacklistRepository(mongoConfiguration, mongoClient);
    }
}
