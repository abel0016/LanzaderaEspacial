package org.example.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class AgendaLanzamiento {
    private ObjectId id;
    private Date fecha;
    @BsonProperty(value = "lanzadera_id")
    private ObjectId lanzaderaId;
    @BsonProperty(value = "nave_id")
    private ObjectId naveId;
    private Estado estado;
    @BsonProperty(value = "plan_vuelo")
    private List<List<String>> planVuelo;
    private List<Tripulante> tripulacion;

    public AgendaLanzamiento(ObjectId id, Date fecha, ObjectId lanzaderaId, ObjectId naveId, Estado estado, List<List<String>> planVuelo, List<Tripulante> tripulacion) {
        this.id = id;
        this.fecha = fecha;
        this.lanzaderaId = lanzaderaId;
        this.naveId = naveId;
        this.estado = estado;
        this.planVuelo = planVuelo;
        this.tripulacion = tripulacion;
    }
    public AgendaLanzamiento(){
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ObjectId getLanzaderaId() {
        return lanzaderaId;
    }

    public void setLanzaderaId(ObjectId lanzaderaId) {
        this.lanzaderaId = lanzaderaId;
    }

    public ObjectId getNaveId() {
        return naveId;
    }

    public void setNaveId(ObjectId naveId) {
        this.naveId = naveId;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<List<String>> getPlanVuelo() {
        return planVuelo;
    }

    public void setPlanVuelo(List<List<String>> planVuelo) {
        this.planVuelo = planVuelo;
    }

    public List<Tripulante> getTripulacion() {
        return tripulacion;
    }

    public void setTripulacion(List<Tripulante> tripulacion) {
        this.tripulacion = tripulacion;
    }
}
