package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.example.database.MongoDBConnection;
import org.example.model.Carga;
import org.example.model.TipoNave;

import java.util.Map;

public class CargaRepository {
    private MongoCollection<Carga> collection;

    public CargaRepository() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.collection = database.getCollection("cargas", Carga.class);
    }
    public int obtenerPesoCarga(ObjectId naveId, TipoNave tipoNave) {
        Carga carga = collection.find(Filters.eq("_id", naveId)).first();

        //Si no hay carga el peso es 0
        if (carga == null) {
            return 0;
        }
        String claveTipo = tipoNaveToString(tipoNave);
        int cantidad = 0;
        Map<String, Integer> cantidades = carga.getCantidadPorTipo();

        if (cantidades != null && cantidades.containsKey(claveTipo)) {
            cantidad = cantidades.get(claveTipo);
        }
        return carga.calcularPesoTotal(cantidad);
    }

    private String tipoNaveToString(TipoNave tipoNave) {
        switch (tipoNave) {
            case EXPLORACION:
                return "exploracion";
            case TRANSBORDADOR:
                return "transbordador";
            case INVESTIGACION:
                return "investigacion";
            default:
                return "";
        }
    }
}
