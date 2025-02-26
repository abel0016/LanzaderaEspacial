package org.example;

import org.bson.types.ObjectId;
import org.example.database.MongoDBConnection;
import org.example.model.*;
import org.example.repository.*;
import org.example.utils.LanzamientosUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestionBD {
    LanzaderaRepository lanzaderaRepository=new LanzaderaRepository();
    TripulanteRepository tripulanteRepository=new TripulanteRepository();
    AgendaRepository agendaRepository=new AgendaRepository();
    NaveRepository naveRepository=new NaveRepository();
    CargaRepository cargaRepository=new CargaRepository();
    Scanner teclado=new Scanner(System.in);
    public GestionBD(){
    }


    public void menuAgencia(){
        System.out.println(" Bienvenido a la Agencia Espacial ");
        System.out.println("==================================");
        System.out.println("1. Elegir Lanzadera");
        System.out.println("2. Salir");
    }
    public void menuLanzadera(){
        System.out.println(" Opciones de la lanzadera ");
        System.out.println("==========================");
        System.out.println("1. Planificar lanzamiento");
        System.out.println("2. Mostrar personal disponible");
        System.out.println("3. Mostrar estado de la lanzadera");
        System.out.println("4. Mostrar estado del próximo lanzamiento");
        System.out.println("5. Embarcar tripulación");
        System.out.println("6. Cargar suministros");
        System.out.println("7. Cancelar lanzamiento");
        System.out.println("8. Posponer lanzamiento");
        System.out.println("9. Realizar lanzamiento");
        System.out.println("10. Rellenar tanques de la lanzadera");
        System.out.println("0. Volver");
    }

    //Elegir Lanzadera
    public void gestionarAgencia(){
        int op;
        int opcionMenu;
        boolean bandera=false;
        do{
            menuAgencia();
            System.out.println("Seleccione una opción:");
            op =teclado.nextInt();
            if(op==1){
                System.out.println("Seleccione una lanzadera disponible:");
                List<Lanzadera> lanzaderas= lanzaderaRepository.mostrarLanzaderas();
                for(int i=0;i<lanzaderas.size();i++){
                    System.out.println(i+1+" "+ lanzaderas.get(i).getNombre());
                }
                int opSeleccionada= teclado.nextInt();
                Lanzadera lanzadera=lanzaderas.get(opSeleccionada-1);
                menuLanzadera();
                System.out.println("Seleccione una opción:");
                opcionMenu= teclado.nextInt();
                switch (opcionMenu){
                    case 1:
                        //Planificar lanzamiento (metodo por terminar)
                        planificarLanzamiento(lanzadera);
                        break;
                    case 2:
                        mostrarPersonalDisponible(lanzadera);
                        break;

                    case 3:
                        mostrarEstadoLanzadera(lanzadera);
                        break;
                    case 4:
                        mostrarEstadoProximoLanzamiento(lanzadera);
                        break;
                    case 5:
                        embarcarTripulacion(lanzadera);
                        break;
                    case 6:
                        cargarSuministros(lanzadera);
                }

            } else if (op==2) {
                System.out.println("Saliendo...");
                bandera=true;
            }else {
                System.out.println("Opción no válida");
            }
        }while (!bandera);

    }
    //1.Planificar Lanzamiento
    public void planificarLanzamiento(Lanzadera lanzadera) {
        System.out.println("Ingrese la fecha del lanzamiento (DD-MM-YYYY):");
        String fechainput = teclado.next();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaLanzamiento;

        try {
            fechaLanzamiento = LocalDate.parse(fechainput, formato);
            if (fechaLanzamiento.isBefore(LocalDate.now())) {
                System.out.println("No puedes programar un lanzamiento en una fecha pasada.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Inténtalo de nuevo.");
            return;
        }

        System.out.println("Selecciona la nave para el lanzamiento:");
        List<Nave> navesDisponibles = naveRepository.obtenerNavesDisponibles(lanzadera.getId());

        if (navesDisponibles.isEmpty()) {
            System.out.println("No hay naves disponibles para esta lanzadera.");
            return;
        }

        for (int i = 0; i < navesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + navesDisponibles.get(i).getNombre());
        }

        int seleccion;
        try {
            seleccion = teclado.nextInt();
            teclado.nextLine();
            if (seleccion < 1 || seleccion > navesDisponibles.size()) {
                System.out.println("Opción inválida.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Introduce un número.");
            teclado.nextLine(); // Limpia el buffer
            return;
        }

        Nave naveSeleccionada = navesDisponibles.get(seleccion - 1);

        if (agendaRepository.naveTieneLanzamiento(naveSeleccionada.getId())) {
            System.out.println("Esta nave ya está asignada a un lanzamiento.");
            return;
        }

        List<List<String>> planVuelo = LanzamientosUtils.generarPlanVuelo();

        AgendaLanzamiento nuevoLanzamiento = new AgendaLanzamiento(
                new ObjectId(),
                java.sql.Date.valueOf(fechaLanzamiento),
                lanzadera.getId(),
                naveSeleccionada.getId(),
                Estado.PLANIFICADO,
                planVuelo,
                new ArrayList<>()
        );

        agendaRepository.planificarLanzamiento(nuevoLanzamiento);
        System.out.println("Lanzamiento planificado para " + fechaLanzamiento);
    }

    //3.Mostrar estado de la lanzadera
    public void mostrarEstadoLanzadera(Lanzadera lanzadera){
        System.out.println("Estado de la lanzadera: "+lanzadera.getNombre());
        System.out.println("Combustible: "+lanzadera.getCombustibleDisponible()+" / "+lanzadera.getCapacidadMaximaCombustible());
        System.out.println("Oxígeno: "+lanzadera.getOxigenoDisponible()+" / "+lanzadera.getCapacidadMaximaOxigeno());
        System.out.println("Lanzamientos planificados:");
        List<AgendaLanzamiento> lanzamientos = agendaRepository.obtenerLanzamientosPlanificados(lanzadera.getId());
        if (lanzamientos.isEmpty()) {
            System.out.println("No hay lanzamientos planificados.");
        } else {
            for (AgendaLanzamiento lanzamiento : lanzamientos) {
                String nombreNave = naveRepository.obtenerNave(lanzamiento.getNaveId()).getNombre();
                System.out.println("Fecha: " + lanzamiento.getFecha() + " Nave: " + nombreNave + " Estado: " + lanzamiento.getEstado());
            }
        }
    }
    //2.Mostrar personal
    public void mostrarPersonalDisponible(Lanzadera lanzadera){
        Map<TipoTripulante,Integer> personal=tripulanteRepository.mostrarTripulantesLanzadera(lanzadera);
        for (Map.Entry<TipoTripulante, Integer> tripulante : personal.entrySet()) {
            System.out.println(tripulante.getKey() + ": " + tripulante.getValue());
        }
    }
    public void mostrarEstadoProximoLanzamiento(Lanzadera lanzadera) {
        AgendaLanzamiento proximoLanzamiento = agendaRepository.obtenerProximoLanzamiento(lanzadera.getId());

        if (proximoLanzamiento == null) {
            System.out.println("No hay lanzamientos planificados próximamente.");
            return;
        }
        Nave nave = naveRepository.obtenerNave(proximoLanzamiento.getNaveId());

        System.out.println("Próximo lanzamiento:");
        System.out.println("Fecha: " + proximoLanzamiento.getFecha());
        System.out.println("Nave: " + nave.getNombre());
        System.out.println("Tipo: " + nave.getTipo());
        System.out.println("Combustible: " + nave.getCombustible() + " / " + lanzadera.getCapacidadMaximaCombustible());
        System.out.println("Oxígeno: " + nave.getOxigeno() + " / " + lanzadera.getCapacidadMaximaOxigeno());
        if (proximoLanzamiento.getTripulacion() == null || proximoLanzamiento.getTripulacion().isEmpty()) {
            System.out.println("Tripulación: No embarcada");
        } else {
            System.out.println("Tripulación embarcada:");
            for (Tripulante tripulante : proximoLanzamiento.getTripulacion()) {
                System.out.println("  - " + tripulante.getNombre() + " (" + tripulante.getTipo() + ")");
            }
        }
        int duracionMision = calcularDuracionMision(nave, proximoLanzamiento);
        System.out.println("- Duración estimada de la misión: " + duracionMision + " días");
    }
    private int calcularDuracionMision(Nave nave, AgendaLanzamiento lanzamiento) {
        int numCuadriculas = 0;

        //Contamos cuantas celdas x hay en el plan de vuelo
        for (List<String> fila : lanzamiento.getPlanVuelo()) {
            for (String celda : fila) {
                if ("x".equals(celda)) {
                    numCuadriculas++;
                }
            }
        }
        TipoNave tipoNave = nave.getTipo();
        if (tipoNave == TipoNave.EXPLORACION) {
            return numCuadriculas;
        }
        if (tipoNave == TipoNave.TRANSBORDADOR) {
            return (numCuadriculas * 2) + 1;
        }
        if (tipoNave == TipoNave.INVESTIGACION) {
            return (numCuadriculas * 2) + nave.getDiasDuracionInvestigacion();
        }
        return numCuadriculas;
    }
    //Para embarcar tripulación hay que tener en cuenta varias cosas:
    //1.Si no existe ningun lanzamiento próximo no tiene sentido que embarquemos tripulación en la lanzadera.
    //2.Si no hay tripulantes disponibles, es decir si todos los tripulantes tienen de estado PLANIFICADO no se debe de poder asignar ninguno
    //3.Se deben de poder embarcar tripulantes hasta que el usuario decida no embarcar más.
    //4.Hay que tener en cuenta que es posible que se embarquen tripulantes, y después decidamos embarcar alguno más. Por tanto la Query va a ser un .addToSet porque si hacemos .set se eliminarán los tripulantes
    //en caso de haber tripulantes embarcados anteriormente.
    //5.Hay que hacer un formato decente a la hora de imprimir los datos
    public void embarcarTripulacion(Lanzadera lanzadera) {
        AgendaLanzamiento proximoLanzamiento = agendaRepository.obtenerProximoLanzamiento(lanzadera.getId());

        if (proximoLanzamiento == null) {
            System.out.println("No hay lanzamientos planificados para esta lanzadera!");
            return;
        }
        List<ObjectId> tripulantesAsignados = agendaRepository.obtenerTripulantesAsignados();
        List<Tripulante> todosLosTripulantes = tripulanteRepository.obtenerTodosLosTripulantes();
        List<Tripulante> tripulantesDisponibles = new ArrayList<>();

        for (Tripulante t : todosLosTripulantes) {
            if (!tripulantesAsignados.contains(t.getId())) {
                tripulantesDisponibles.add(t);
            }
        }
        if (tripulantesDisponibles.isEmpty()) {
            System.out.println("No hay tripulantes disponibles!");
            return;
        }
        List<Tripulante> tripulantesSeleccionados = new ArrayList<>();
        while (true) {
            System.out.println("Tripulantes disponibles:");
            for (int i = 0; i < tripulantesDisponibles.size(); i++) {
                System.out.println((i + 1) + "-" + tripulantesDisponibles.get(i).getNombre() + "(" + tripulantesDisponibles.get(i).getTipo() + ")");
            }
            System.out.println("Seleccione un número para embarcar un tripulante o 0 para finalizar:");

            int opcion= teclado.nextInt();
            teclado.nextLine();
            if (opcion == 0) {
                break;
            }
            Tripulante seleccionado = tripulantesDisponibles.remove(opcion - 1);
            tripulantesSeleccionados.add(seleccionado);
            System.out.println("Tripulante embarcado: " + seleccionado.getNombre());
            if (tripulantesDisponibles.isEmpty()) {
                System.out.println("No hay tripulantes disponibles");
                break;
            }
        }
        agendaRepository.agregarTripulantes(proximoLanzamiento,tripulantesSeleccionados);
        System.out.println("Tripulación embarcada con éxito:");
        for (Tripulante tripulante : tripulantesSeleccionados) {
            System.out.println("-" + tripulante.getNombre() + "(" + tripulante.getTipo() + ")");
        }
    }
    public void cargarSuministros(Lanzadera lanzadera) {
        AgendaLanzamiento proximoLanzamiento = agendaRepository.obtenerProximoLanzamiento(lanzadera.getId());

        if (proximoLanzamiento == null) {
            System.out.println("No hay lanzamientos planificados para esta lanzadera.");
            return;
        }

        Nave nave = naveRepository.obtenerNave(proximoLanzamiento.getNaveId());

        if (nave == null) {
            System.out.println("No se encontró la nave asociada al lanzamiento.");
            return;
        }

        int diasMision = nave.getDiasDuracionInvestigacion();
        List<Tripulante> tripulacion = proximoLanzamiento.getTripulacion();

        // Calcular oxígeno necesario
        int tripulantesNoDroides = 0;
        int pesoTripulacion = 0;
        for (Tripulante t : tripulacion) {
            if (!t.getTipo().equals(TipoTripulante.DROIDE)) {
                tripulantesNoDroides++;
                pesoTripulacion += t.getPeso();
            }
        }
        int oxigenoNecesario = tripulantesNoDroides * diasMision * 3;

        // Obtener peso de la carga usando `CargaRepository`
        int pesoCarga = cargaRepository.obtenerPesoCarga(nave.getId(), nave.getTipo());

        // Obtener modificador de combustible por tipo de nave
        double modificador;
        switch (nave.getTipo()) {
            case EXPLORACION:
                modificador = 0.01;
                break;
            case INVESTIGACION:
                modificador = 0.02;
                break;
            case TRANSBORDADOR:
                modificador = 0.03;
                break;
            default:
                modificador = 0.01;
                break;
        }

        // Calcular combustible necesario
        int combustibleNecesario = (int) ((pesoCarga + pesoTripulacion) * modificador * diasMision);

        // Verificar si la nave ya está completamente cargada
        if (nave.getOxigeno() >= oxigenoNecesario && nave.getCombustible() >= combustibleNecesario) {
            System.out.println("La nave ya tiene el oxígeno y combustible completamente cargados.");
            return;
        }

        // Verificar si la lanzadera tiene suficientes suministros
        if (lanzadera.getOxigenoDisponible() < oxigenoNecesario || lanzadera.getCombustibleDisponible() < combustibleNecesario) {
            System.out.println("No hay suficientes suministros en la lanzadera.");
            System.out.println("Oxígeno disponible: " + lanzadera.getOxigenoDisponible() + " / Requerido: " + oxigenoNecesario);
            System.out.println("Combustible disponible: " + lanzadera.getCombustibleDisponible() + " / Requerido: " + combustibleNecesario);
            return;
        }

        // Restar suministros de la lanzadera y asignarlos a la nave
        lanzadera.setOxigenoDisponible(lanzadera.getOxigenoDisponible() - oxigenoNecesario);
        lanzadera.setCombustibleDisponible(lanzadera.getCombustibleDisponible() - combustibleNecesario);
        nave.setOxigeno(oxigenoNecesario);
        nave.setCombustible(combustibleNecesario);

        // Actualizar en la base de datos
        lanzaderaRepository.actualizarOxigenoYCombustible(lanzadera.getId(), lanzadera.getOxigenoDisponible(), lanzadera.getCombustibleDisponible());
        naveRepository.actualizarOxigenoYCombustible(nave.getId(), nave.getOxigeno(), nave.getCombustible());

        // Mostrar resultado final
        System.out.println("Suministros cargados con éxito.");
        System.out.println("Oxígeno cargado: " + oxigenoNecesario);
        System.out.println("Combustible cargado: " + combustibleNecesario);
    }
    public void cancelarLanzamiento(Lanzadera lanzadera) {
        AgendaLanzamiento proximoLanzamiento = agendaRepository.obtenerProximoLanzamiento(lanzadera.getId());

        if (proximoLanzamiento == null) {
            System.out.println("No hay lanzamientos planificados para cancelar.");
            return;
        }

        agendaRepository.actualizarEstadoLanzamiento(proximoLanzamiento.getId(), Estado.CANCELADO);
        System.out.println("Lanzamiento cancelado con éxito.");
    }
    public void posponerLanzamiento(Lanzadera lanzadera) {
        AgendaLanzamiento proximoLanzamiento = agendaRepository.obtenerProximoLanzamiento(lanzadera.getId());

        if (proximoLanzamiento == null) {
            System.out.println("No hay lanzamientos planificados para posponer.");
            return;
        }

        System.out.println("Ingrese la nueva fecha del lanzamiento (DD-MM-YYYY):");
        String fechainput = teclado.next();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate nuevaFecha = LocalDate.parse(fechainput, formato);

        agendaRepository.actualizarFechaLanzamiento(proximoLanzamiento.getId(), java.sql.Date.valueOf(nuevaFecha));
        System.out.println("Lanzamiento pospuesto para " + nuevaFecha);
    }



}
