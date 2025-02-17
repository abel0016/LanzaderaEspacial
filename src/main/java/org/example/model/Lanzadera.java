package org.example.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Lanzadera {
    private ObjectId id;
    private String nombre;
    @BsonProperty(value = "capacidad_maxima_combustible")
    private int capacidadMaximaCombustible;
    @BsonProperty(value = "combustible_disponible")
    private int combustibleDisponible;
    @BsonProperty(value = "capacidad_maxima_oxigeno")
    private int capacidadMaximaOxigeno;
    @BsonProperty(value = "oxigeno_disponible")
    private int oxigenoDisponible;
}
