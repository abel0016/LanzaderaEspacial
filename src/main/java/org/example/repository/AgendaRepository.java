package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.example.database.MongoDBConnection;
import org.example.model.AgendaLanzamiento;

public class AgendaRepository {
    private MongoCollection<AgendaLanzamiento> collection;

    public AgendaRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.collection = database.getCollection("AgendaLanzamientos", AgendaLanzamiento.class);
    }

}
