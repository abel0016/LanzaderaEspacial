package org.example.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.example.database.MongoDBConnection;
import org.example.model.Lanzadera;
import org.example.model.TipoTripulante;
import org.example.model.Tripulante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripulanteRepository {
    private MongoCollection<Tripulante> tripulanteCollection;

    public TripulanteRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.tripulanteCollection = database.getCollection("tripulantes", Tripulante.class);
    }
    public Map<TipoTripulante, Integer> mostrarTripulantesLanzadera(Lanzadera lanzadera) {
        FindIterable<Tripulante> iterable = tripulanteCollection.find(Filters.eq("lanzadera_id", lanzadera.getId()));
        Map<TipoTripulante, Integer> tripulacion = new HashMap<>();
        for (Tripulante t : iterable) {
            TipoTripulante tipo = t.getTipo();

            if (tripulacion.containsKey(tipo)) {
                int valor = tripulacion.get(tipo);
                valor += 1;
                tripulacion.put(tipo, valor);
            } else {
                tripulacion.put(tipo, 1);
            }
        }

        return tripulacion;
    }


}
