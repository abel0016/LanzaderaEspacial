package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;
import org.example.database.MongoDBConnection;
import org.example.model.Nave;

import java.util.ArrayList;
import java.util.List;

public class NaveRepository {
    private MongoCollection<Nave> collection;

    public NaveRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.collection = database.getCollection("naves", Nave.class);
    }

    public Nave obtenerNave(ObjectId naveId) {
        return collection.find(Filters.eq("_id", naveId)).first();
    }

    public void actualizarOxigenoYCombustible(ObjectId naveId, int nuevoOxigeno, int nuevoCombustible) {
        collection.updateOne(Filters.eq("_id", naveId),
                Updates.combine(
                        Updates.set("oxigeno", nuevoOxigeno),
                        Updates.set("combustible", nuevoCombustible)
                ));
    }
    public void liberarNave(ObjectId naveId) {
        collection.updateOne(Filters.eq("_id", naveId), Updates.unset("estado"));
    }
    public List<Nave> obtenerNavesDisponibles(ObjectId idLanzadera) {
        return collection.find(Filters.eq("lanzadera_id", idLanzadera)).into(new ArrayList<>());
    }


}
