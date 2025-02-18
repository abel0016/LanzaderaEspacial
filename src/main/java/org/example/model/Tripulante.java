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
    private List<ObjectId> lanzaderaId;

    public Tripulante(){

    }

    public Tripulante(ObjectId id, String nombre, Double peso, TipoTripulante tipo, boolean disponible, List<ObjectId> lanzaderaId) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.tipo = tipo;
        this.disponible = disponible;
        this.lanzaderaId = lanzaderaId;
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public TipoTripulante getTipo() {
        return tipo;
    }

    public void setTipo(TipoTripulante tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<ObjectId> getLanzaderaId() {
        return lanzaderaId;
    }

}
