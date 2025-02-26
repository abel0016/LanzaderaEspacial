package org.example.database;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.mongodb.ConnectionString;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBConnection {
    private static MongoDBConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoDBConnection() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("No se encontró el archivo config.properties");
            }

            // Cargar propiedades
            Properties properties = new Properties();
            properties.load(input);

            String user = properties.getProperty("mongo.user");
            String password = properties.getProperty("mongo.password");
            String cluster = properties.getProperty("mongo.cluster");
            String databaseName = properties.getProperty("mongo.database");
            String options = properties.getProperty("mongo.options");

            String connectionString = "mongodb+srv://" + user + ":" + password + "@" + cluster + "/" + options;

            CodecRegistry pojoCodecRegistry = fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .codecRegistry(pojoCodecRegistry)
                    .build();

            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(databaseName);

            System.out.println("Conexión a MongoDB establecida");
        } catch (IOException e) {
            System.err.println("Error al cargar config.properties: " + e.getMessage());
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
