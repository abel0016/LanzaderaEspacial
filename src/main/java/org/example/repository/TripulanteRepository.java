package org.example.repository;

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
        this.tripulanteCollection = database.getCollection("Tripulantes", Tripulante.class);
    }
    public Map<TipoTripulante,Integer> mostrarTripulantesLanzadera(Lanzadera lanzadera){
        List<Tripulante> listaTri= (List<Tripulante>) tripulanteCollection.find(Filters.eq("lanzadera_id",lanzadera.getId()));
        Map<TipoTripulante,Integer> tripulacion = new HashMap<>();
        for(Tripulante t : listaTri.getLanzaderaId()){
            Tripulante tri=tripulanteCollection.find(Filters.eq("tipo",t.getTipo())).first();
            if(tripulacion.containsKey(tri.getTipo())){
                int valor=tripulacion.get(tri.getTipo());
                valor+=1;
                tripulacion.put(tri.getTipo(),valor);
            }
            tripulacion.put(tri.getTipo(),1);
        }
        return tripulacion;
    }

}
