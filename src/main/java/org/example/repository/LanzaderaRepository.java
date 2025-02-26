package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;
import org.example.database.MongoDBConnection;
import org.example.model.Lanzadera;

import java.util.ArrayList;
import java.util.List;

public class LanzaderaRepository {
    private MongoCollection<Lanzadera> collection;

    public LanzaderaRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.collection = database.getCollection("lanzaderas", Lanzadera.class);
    }

    public List<Lanzadera> mostrarLanzaderas() {
        MongoCursor<Lanzadera> cursor = collection.find().iterator();
        List<Lanzadera> lanzaderasMostradas = new ArrayList<>();
        while (cursor.hasNext()) {
            lanzaderasMostradas.add(cursor.next());
        }
        return lanzaderasMostradas;
    }

    public void actualizarOxigenoYCombustible(ObjectId lanzaderaId, int nuevoOxigeno, int nuevoCombustible) {
        collection.updateOne(Filters.eq("_id", lanzaderaId),
                Updates.combine(
                        Updates.set("oxigeno_disponible", nuevoOxigeno),
                        Updates.set("combustible_disponible", nuevoCombustible)
                ));
    }
}
