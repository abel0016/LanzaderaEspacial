package org.example.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Nave {
    private ObjectId id;
    private String nombre;
    @BsonProperty(value = "lanzadera_id")
    private Lanzadera lanzaderaId;
    private TipoNave tipo;
    private int combustible;
    private int oxigeno;
    @BsonProperty(value = "dias_duracion_investigacion")
    private int diasDuracionInvestigacion;

    public Nave(){

    }

    public Nave(ObjectId id, String nombre, Lanzadera lanzaderaId, TipoNave tipo, int combustible, int oxigeno, int diasDuracionInvestigacion) {
        this.id = id;
        this.nombre = nombre;
        this.lanzaderaId = lanzaderaId;
        this.tipo = tipo;
        this.combustible = combustible;
        this.oxigeno = oxigeno;
        this.diasDuracionInvestigacion = diasDuracionInvestigacion;
    }

    public Nave(ObjectId id, String nombre, Lanzadera lanzaderaId, TipoNave tipo, int combustible, int oxigeno) {
        this.id = id;
        this.nombre = nombre;
        this.lanzaderaId = lanzaderaId;
        this.tipo = tipo;
        this.combustible = combustible;
        this.oxigeno = oxigeno;
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

    public Lanzadera getLanzaderaId() {
        return lanzaderaId;
    }

    public void setLanzaderaId(Lanzadera lanzaderaId) {
        this.lanzaderaId = lanzaderaId;
    }

    public TipoNave getTipo() {
        return tipo;
    }

    public void setTipo(TipoNave tipo) {
        this.tipo = tipo;
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public int getOxigeno() {
        return oxigeno;
    }

    public void setOxigeno(int oxigeno) {
        this.oxigeno = oxigeno;
    }

    public int getDiasDuracionInvestigacion() {
        return diasDuracionInvestigacion;
    }

    public void setDiasDuracionInvestigacion(int diasDuracionInvestigacion) {
        this.diasDuracionInvestigacion = diasDuracionInvestigacion;
    }
}
