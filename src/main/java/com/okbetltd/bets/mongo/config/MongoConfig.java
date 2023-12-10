package com.okbetltd.bets.mongo.config;

import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";
    private static final String HOST = "localhost";

    private static final int MONGODB_PORT = 27017;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

//        int randomPort = SocketUtils.findAvailableTcpPort();

        ImmutableMongodConfig mongoDbConfig = MongodConfig.builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(HOST, MONGODB_PORT, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = starter.prepare(mongoDbConfig);
        mongodExecutable.start();
        return new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, HOST, MONGODB_PORT)), "mongo_auth");
    }
}
