package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;
import org.example.database.MongoDBConnection;
import org.example.model.AgendaLanzamiento;
import org.example.model.Tripulante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgendaRepository {
    private MongoCollection<AgendaLanzamiento> collection;

    public AgendaRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.collection = database.getCollection("agendaLanzamientos", AgendaLanzamiento.class);
    }

    public List<AgendaLanzamiento> obtenerLanzamientosPlanificados(ObjectId lanzaderaId) {
        List<AgendaLanzamiento> lanzamientos = new ArrayList<>();

        // Obtener todos los lanzamientos de la lanzadera
        for (AgendaLanzamiento lanzamiento : collection.find(Filters.eq("lanzadera_id", lanzaderaId))) {
            if ("PLANIFICADO".equals(lanzamiento.getEstado())) {
                lanzamientos.add(lanzamiento);
            }
        }

        return lanzamientos;
    }
    public AgendaLanzamiento obtenerProximoLanzamiento(ObjectId lanzaderaId) {
        LocalDate fechaActual = LocalDate.now();
        AgendaLanzamiento proximoLanzamiento = null;

        for (AgendaLanzamiento lanzamiento : collection.find(Filters.eq("lanzadera_id", lanzaderaId))) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lanzamiento.getFecha());
            int anio = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH) + 1;
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            LocalDate fechaLanzamiento = LocalDate.of(anio, mes, dia);

            if (fechaLanzamiento.compareTo(fechaActual) > 0) {
                if (proximoLanzamiento == null) {
                    proximoLanzamiento = lanzamiento;
                } else {
                    calendar.setTime(proximoLanzamiento.getFecha());
                    LocalDate fechaProximo = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
                    if (fechaLanzamiento.compareTo(fechaProximo) < 0) {
                        proximoLanzamiento = lanzamiento;
                    }
                }
            }
        }

        return proximoLanzamiento;
    }
    public List<ObjectId> obtenerTripulantesAsignados() {
        List<ObjectId> tripulantesAsignados = new ArrayList<>();

        for (AgendaLanzamiento lanzamiento : collection.find(Filters.eq("estado", "PLANIFICADO"))) {
            for (Tripulante tripulante : lanzamiento.getTripulacion()) {
                if (tripulante != null && tripulante.getId() != null) {
                    tripulantesAsignados.add(tripulante.getId());
                }
            }
        }

        return tripulantesAsignados;
    }
    public void agregarTripulantes(AgendaLanzamiento lanzamiento, List<Tripulante> nuevosTripulantes) {
        collection.updateOne(
                Filters.eq("_id", lanzamiento.getId()),
                Updates.addToSet("tripulacion", nuevosTripulantes)
        );
    }
    public void actualizarEstadoLanzamiento(ObjectId lanzamientoId, Estado nuevoEstado) {
        collection.updateOne(Filters.eq("_id", lanzamientoId),
                Updates.set("estado", nuevoEstado));
    }

    public void actualizarFechaLanzamiento(ObjectId lanzamientoId, Date nuevaFecha) {
        collection.updateOne(Filters.eq("_id", lanzamientoId),
                Updates.set("fecha", nuevaFecha));
    }


}
