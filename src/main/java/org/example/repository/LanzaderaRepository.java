package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.example.database.MongoDBConnection;
import org.example.model.Lanzadera;

public class LanzaderaRepository {
    private MongoCollection<Lanzadera> collection;

    public LanzaderaRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.collection = database.getCollection("Lanzaderas", Lanzadera.class);
    }

}
