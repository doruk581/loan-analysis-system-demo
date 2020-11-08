package com.doruk.blacklist.infrastructure.repository;

import com.doruk.blacklist.domain.Blacklist;
import com.doruk.blacklist.domain.BlacklistRepository;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Slf4j
public class MongoBlacklistRepository implements BlacklistRepository {

    public static final String ID_PARAMETER = "_id";
    private final com.mongodb.client.MongoClient mongoClient;
    private final MongoCollection<Blacklist> mongoCollection;

    public MongoBlacklistRepository(
            MongoConfiguration configuration,
            com.mongodb.client.MongoClient mongoClient) {
        this.mongoClient = mongoClient;

        final String mongoDbName = configuration.getMongoDbName();
        final String mongoCollectionName = configuration.getMongoCollectionName();


        final CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        final MongoDatabase mongoDatabase = this.mongoClient.getDatabase(mongoDbName).withCodecRegistry(pojoCodecRegistry);
        mongoCollection = mongoDatabase.getCollection(mongoCollectionName, Blacklist.class);
    }

    @Override
    public void save(final Blacklist blacklist) {
        mongoCollection.insertOne(blacklist);
    }

    @Override
    public void update(Blacklist blacklist) {
        mongoCollection.findOneAndReplace(Filters.eq(ID_PARAMETER, blacklist.getIdentityNumber()), blacklist);
    }

    @Override
    public Optional<Blacklist> get(String identity) {
        final Bson identityFilter = Filters.eq(ID_PARAMETER, identity);

        final List<Blacklist> courierList = mongoCollection.
                find(identityFilter)
                .limit(1)
                .into(new ArrayList<>());

        return courierList.isEmpty() ? Optional.empty() : Optional.of(courierList.get(0));
    }
}
