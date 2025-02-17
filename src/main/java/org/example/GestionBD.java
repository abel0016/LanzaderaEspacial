package org.example;

import java.util.Scanner;

public class GestionBD {
    Scanner teclado=new Scanner(System.in);

    public void MenuAgencia(){
        System.out.println(" Bienvenido a la Agencia Espacial ");
        System.out.println("==================================");
        System.out.println("1. Elegir Lanzadera");
        System.out.println("2. Salir");
    }
    public void MenuLanzadera(){
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
    public void GestionarLanzadera(){
        int op;
        boolean bandera=false;
        do{
            MenuAgencia();
            System.out.println("Seleccione una opción:");
            op =teclado.nextInt();
            if(op==1){
                System.out.println("Seleccione una lanzadera disponible:");
                //llamada al metodo que me muestra las lanzaderas disponibles
                MenuLanzadera();
                System.out.println("Seleccione una opción:");

            } else if (op==2) {
                System.out.println("Saliendo...");
                bandera=true;
            }else {
                System.out.println("Opción no válida");
            }
        }while (!bandera);

    }
}
