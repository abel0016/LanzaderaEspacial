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

}
