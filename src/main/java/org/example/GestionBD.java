package org.example;

import org.example.database.MongoDBConnection;
import org.example.model.Lanzadera;
import org.example.model.TipoTripulante;
import org.example.repository.LanzaderaRepository;
import org.example.repository.TripulanteRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GestionBD {
    LanzaderaRepository lanzaderaRepository=new LanzaderaRepository();
    TripulanteRepository tripulanteRepository=new TripulanteRepository();
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
                        //Mostrar personal disponible
                        mostrarPersonalDisponible(lanzadera);
                        break;

                    case 3:
                        //Mostrar estado de la lanzadera (metodo por terminar)
                        mostrarEstadoLanzadera(lanzadera);
                        break;
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
    public void planificarLanzamiento(Lanzadera lanzadera){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaLanzamiento=null;
        System.out.println("Ingrese la fecha del lanzamiento (DD-MM-YYYY)");
        String fechainput=teclado.nextLine();
        fechaLanzamiento=LocalDate.parse(fechainput,formato);
        Lanzadera lanzaderaLanzamiento=lanzadera;
        //Metodo por completar debido a gran confusion, lo dejo para mas adelante
        //Entiendo que necesito datos en la coleccion de AgendaLanzamiento ya que necesito las naves que no tienen un viaje planificado
        //Como la coleccion agendalanzamiento aun no existe, no entiendo muy bien como seguir por ahora por lo tanto lo dejo para más adelante

    }
    //3.Mostrar estado de la lanzadera
    public void mostrarEstadoLanzadera(Lanzadera lanzadera){
        System.out.println("Estado de la lanzadera: "+lanzadera.getNombre());
        System.out.println("Combustible: "+lanzadera.getCombustibleDisponible()+" / "+lanzadera.getCapacidadMaximaCombustible());
        System.out.println("Oxígeno: "+lanzadera.getOxigenoDisponible()+" / "+lanzadera.getCapacidadMaximaOxigeno());
        System.out.println("Lanzamientos planificados:");
        //Metodo por completar debido a que no entiendo muy bien como saco los lanzamientos planificados
        //Entiendo que debo de conseguirlo de AgendaLanzamiento ya que sabemos el id de la lanzadera pero como no hay datos en agendalanzamiento no puedo seguir

    }
    //2.Mostrar personal
    public void mostrarPersonalDisponible(Lanzadera lanzadera){
        Map<TipoTripulante,Integer> personal=tripulanteRepository.mostrarTripulantesLanzadera(lanzadera);
        for (Map.Entry<TipoTripulante, Integer> tripulante : personal.entrySet()) {
            System.out.println(tripulante.getKey() + ": " + tripulante.getValue());
        }
    }
}
