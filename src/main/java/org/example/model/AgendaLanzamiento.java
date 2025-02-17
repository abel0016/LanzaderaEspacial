package org.example.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class AgendaLanzamiento {
    private ObjectId id;
    private Date fecha;
    @BsonProperty(value = "lanzadera_id")
    private Lanzadera lanzaderaId;
    @BsonProperty(value = "nave_id")
    private Nave naveId;
    private Estado estado;
    @BsonProperty(value = "plan_vuelo")
    private List<List<String>> planVuelo;
    private List<Tripulante> tripulacion;

}
