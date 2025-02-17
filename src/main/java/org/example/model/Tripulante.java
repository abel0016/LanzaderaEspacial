package org.example.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class Tripulante {
    private ObjectId id;
    private String nombre;
    private Double peso;
    private TipoTripulante tipo;
    private boolean disponible;
    @BsonProperty(value = "lanzadera_id")
    private List<Lanzadera> lanzaderaId;

}
