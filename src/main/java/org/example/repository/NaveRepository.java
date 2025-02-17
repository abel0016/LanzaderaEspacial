package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.example.database.MongoDBConnection;
import org.example.model.Nave;

public class NaveRepository {
    private MongoCollection<Nave> collection;

    public NaveRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.collection = database.getCollection("Naves", Nave.class);
    }

}
