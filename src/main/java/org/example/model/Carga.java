package org.example.model;
import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Map;

public class Carga {
    private ObjectId id;
    private String nombre;

    @BsonProperty(value = "cantidad_por_tipo")
    private Map<String, Integer> cantidadPorTipo;

    @BsonProperty(value = "peso_por_unidad")
    private double pesoPorUnidad;

    public Carga() {}

    public Carga(ObjectId id, String nombre, Map<String, Integer> cantidadPorTipo, double pesoPorUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadPorTipo = cantidadPorTipo;
        this.pesoPorUnidad = pesoPorUnidad;
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

    public Map<String, Integer> getCantidadPorTipo() {
        return cantidadPorTipo;
    }

    public void setCantidadPorTipo(Map<String, Integer> cantidadPorTipo) {
        this.cantidadPorTipo = cantidadPorTipo;
    }

    public double getPesoPorUnidad() {
        return pesoPorUnidad;
    }

    public void setPesoPorUnidad(double pesoPorUnidad) {
        this.pesoPorUnidad = pesoPorUnidad;
    }

    public int calcularPesoTotal(int cantidad) {
        return (int) (cantidad * pesoPorUnidad);
    }
}
