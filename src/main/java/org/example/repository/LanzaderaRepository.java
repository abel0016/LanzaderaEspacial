package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
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

    //Mostrar lanzaderas disponibles
    public List<Lanzadera> mostrarLanzaderas(){
        MongoCursor<Lanzadera> cursor=collection.find().iterator();
        List<Lanzadera> lanzaderasMostradas=new ArrayList<>();
        while(cursor.hasNext()){
            Lanzadera lanzaderaActual=cursor.next();
            lanzaderasMostradas.add(lanzaderaActual);
        }
        return lanzaderasMostradas;
    }

}
