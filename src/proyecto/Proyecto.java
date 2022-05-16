/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author EdgarFernandoEspinoz
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File fichero = new File("C:/Users/EdgarFernandoEspinoz/Desktop/test.txt");
        //lecturaTaules(fichero);
        ListarTaules(fichero);
        EliminarClassroom(fichero);
        //EditMesas(fichero);
        //AñadirMesa(fichero);
        ListarTaules(fichero);
        //lecturaTaules(fichero);

    }

    private static void lecturaTaules(File fichero) {
        try {
            Scanner lectorFichero = new Scanner(fichero, "UTF-8");
            while (lectorFichero.hasNext()) {
                System.out.println(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }
    }

    private static void ListarTaules(File fichero) {
        try {
            Scanner lectorFichero = new Scanner(fichero, "UTF-8");
            while (lectorFichero.hasNext()) {
                String[] linea = lectorFichero.nextLine().split(",");
                System.out.println("=========================================");
                System.out.println("Id taula ==> " + linea[0]);
                System.out.println("Descripciió o Ubicacion de la mesa  ==> " + linea[1]);
                System.out.println("Cantidad máxima de persones  ==> " + linea[2]);
                System.out.println("Tiene sillas de niños?  ==> " + linea[3]);
                System.out.println("Cantidad de sillas para adultos  ==> " + linea[4]);
                System.out.println("Tiene Ventilador ?  ==> " + linea[5]);
                System.out.println("Esta ubicada en el Jardin?  ==> " + linea[6]);
                System.out.println("");

            }

            lectorFichero.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }
    }

    private static void AñadirMesa(File fichero) {
        String nueva_Fila = "\n" + nuevaMesa();
        try {
            FileWriter writer = new FileWriter(fichero, true);
            writer.write(nueva_Fila);
            writer.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }
    }

    private static String nuevaMesa() {
        Scanner lector = new Scanner(System.in);
        System.out.println("Introducir Id Mesa: ");
        String id = lector.next();
        System.out.println("Descripcion de Mesa: ");
        String DescAula = lector.next();
        System.out.println("Introducir cantidad maxima de personas: ");
        String cantidadPersonas = lector.next();
        System.out.println("Indica si hay mesas de niños: ");
        String cadiresNen = lector.next();
        System.out.println("Indica el numero de sillas de Adultos:");
        String cadiresAdult = lector.next();
        System.out.println("Indica si hay ventilador");
        String ventilador = lector.next();
        System.out.println("Indica si la mesa esta en el jardin:");
        String taulaJardi = lector.next();
        String nuevaLinea = id + "," + DescAula + "," + cantidadPersonas + "," + cadiresNen + "," + cadiresAdult + "," + ventilador + "," + taulaJardi;
        return nuevaLinea;
    }

    private static void EditMesas(File fichero) {
        Scanner lector = new Scanner(System.in);
        String fila_creada, fila_nueva;
        System.out.println("Introduce que id quieres editar: ");
        fila_creada = lector.next();
        //Abrimos un array en el cual guardaremos la lineas del fichero taules.txt
        ArrayList<String> filas = new ArrayList<>();

        // Procedemos a abrirlo para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);
            while (lectorFichero.hasNext()) {
                filas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }

        // Procedemos a sobreescribir el fichero taules.txt
        try {
            FileWriter writer = new FileWriter(fichero);

            for (String fila : filas) {
                if (fila_creada.equals(fila.substring(0, 4))) {
                    fila_nueva = nuevaMesa();
                    writer.write(fila_nueva + "\n");
                } else {
                    writer.write(fila + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :(");
        }
    }
    
    private static void EliminarClassroom(File fichero) {
        Scanner lector = new Scanner(System.in);
        String fila_eliminada;
        System.out.println("Introduce que id quieres eliminar: ");
        fila_eliminada = lector.nextLine();

        //Abrimos un array en el cual guardaremos la lineas del fichero taules.txt
        ArrayList<String> filas = new ArrayList<>();

        // Procedemos a abrirlo para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);
            while (lectorFichero.hasNext()) {
                filas.add(lectorFichero.nextLine());
            }
            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :(");
        }
        
        // Procedemos a sobreescribir el fichero taules.txt y procederemos a eliminar la fila
        try {
            FileWriter writer = new FileWriter(fichero);

            for (String fila : filas) {
                if (!fila_eliminada.equals(fila.substring(0, 4))) {
                    writer.write(fila + "\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :(");
        }
    }
}
