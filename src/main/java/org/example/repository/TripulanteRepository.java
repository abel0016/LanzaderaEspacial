package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.example.database.MongoDBConnection;
import org.example.model.Lanzadera;
import org.example.model.Tripulante;

import java.util.ArrayList;
import java.util.List;

public class TripulanteRepository {
    private MongoCollection<Tripulante> tripulanteCollection;
    private MongoCollection<Lanzadera> lanzaderaCollection;

    public TripulanteRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.tripulanteCollection = database.getCollection("Tripulantes", Tripulante.class);
    }
    public void mostrarTripulantesLanzadera(Lanzadera lanzadera){
        Tripulante t=tripulanteCollection.find(Filters.eq("lanzadera_id",lanzadera.getId())).first();
        List<Tripulante> tripulacion=new ArrayList<>();
        for(ObjectId lanzaderaId : t.getLanzaderaId()){
            //Lanzadera lan=lanzaderaCollection.find(Filters.eq("_id", lanzaderaId)).first();

            //Me he perdido. Necesito sacar de la colección de tripulantes el lanzadera_id que coincida con el id de la lanzadera introducida por parametros
            //Despues debo mostrar una lista de los tripulantes mostrando unicamente los tipos con el numero de cuantos hay de cada
            //Es decir Droide 3 Comandante 1 Cientifico 4... etc
            //Con la primera línea, tenemos la lanzadera que coincide con la nuestra.
            //
        }
    }

}
