package org.example.database;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBConnection {

    private static MongoDBConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private static final String CONNECTION_STRING = "mongodb+srv://root:root@clusterad.5kxme.mongodb.net/?retryWrites=true&w=majority&appName=ClusterAD";
    private static final String DATABASE_NAME = "lanzaderaespacial";

    private MongoDBConnection() {
        try {
            CodecRegistry pojoCodecRegistry = fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new com.mongodb.ConnectionString(CONNECTION_STRING))
                    .codecRegistry(pojoCodecRegistry)
                    .build();

            mongoClient = MongoClients.create(settings);

            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Conexión a MongoDB establecida");
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public static MongoDBConnection getInstance() {
        if (instance == null) {
            instance = new MongoDBConnection();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión a MongoDB cerrada");
        }
    }
}