package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.example.database.MongoDBConnection;
import org.example.model.Tripulante;

public class TripulanteRepository {
    private MongoCollection<Tripulante> collection;

    public TripulanteRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.collection = database.getCollection("Tripulantes", Tripulante.class);
    }

}
