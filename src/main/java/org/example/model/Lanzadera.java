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

    public Lanzadera(ObjectId id, String nombre, int capacidadMaximaCombustible, int combustibleDisponible, int capacidadMaximaOxigeno, int oxigenoDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaximaCombustible = capacidadMaximaCombustible;
        this.combustibleDisponible = combustibleDisponible;
        this.capacidadMaximaOxigeno = capacidadMaximaOxigeno;
        this.oxigenoDisponible = oxigenoDisponible;
    }
    public Lanzadera(){

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadMaximaCombustible() {
        return capacidadMaximaCombustible;
    }

    public void setCapacidadMaximaCombustible(int capacidadMaximaCombustible) {
        this.capacidadMaximaCombustible = capacidadMaximaCombustible;
    }

    public int getCombustibleDisponible() {
        return combustibleDisponible;
    }

    public void setCombustibleDisponible(int combustibleDisponible) {
        this.combustibleDisponible = combustibleDisponible;
    }

    public int getCapacidadMaximaOxigeno() {
        return capacidadMaximaOxigeno;
    }

    public void setCapacidadMaximaOxigeno(int capacidadMaximaOxigeno) {
        this.capacidadMaximaOxigeno = capacidadMaximaOxigeno;
    }

    public int getOxigenoDisponible() {
        return oxigenoDisponible;
    }

    public void setOxigenoDisponible(int oxigenoDisponible) {
        this.oxigenoDisponible = oxigenoDisponible;
    }
}
