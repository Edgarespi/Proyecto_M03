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

        try {
            // Codificación ISO-8859-1 (ANSI) o UTF-8 dependiendo de cómo esté creado el fichero de texto
            Scanner lectorFichero = new Scanner(fichero, "UTF-8");
            while (lectorFichero.hasNext()) {
                String[] linea = lectorFichero.nextLine().split(",");
                System.out.println("=========================================");
                System.out.println("Id taula: " + linea[0]);
                System.out.println("Descripciió Taula: " + linea[1]);
                System.out.println("Cantidad máxima de persones: " + linea[2]);
                System.out.println("cadires de nen: " + linea[3]);
                System.out.println("Cantidad de cadires adult: " + linea[4]);
                System.out.println("Ventilador: " + linea[5]);
                System.out.println("Taula Jardí: " + linea[6]);
                System.out.println("");
            }

            lectorFichero.close();
            //Si da error saltara el siguiente mensaje
        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero");
        }
    }

    private static void añadirMesa(File fichero) {

        String nuevaMesa = "\n" + nuevaMesa();

        try {
            FileWriter writer = new FileWriter(fichero, true);
            writer.write(nuevaMesa);
            writer.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero");
        }
    }

    private static String nuevaMesa() {
        Scanner lector = new Scanner(System.in);
        System.out.println("Introdueix id aula: ");
        String id = lector.next();
        System.out.println("Descripció de la taula: ");
        String DescAula = lector.next();
        System.out.println("Introdueix cantidad de persones: ");
        String cantidadPersonas = lector.next();
        System.out.println("Indica si n'hi ha cadires de nen: ");
        String cadiresNen = lector.next();
        System.out.println("Indica si n'hi ha cadires adult:");
        String cadiresAdult = lector.next();
        System.out.println("Indica si n'hi ha veilador");
        String ventilador = lector.next();
        System.out.println("Indica si la taula está al jardí");
        String taulaJardi = lector.next();
        String nuevaLinea = id + "," + DescAula + "," + cantidadPersonas + "," + cadiresNen + "," + cadiresAdult + "," + ventilador + "," + taulaJardi;
        return nuevaLinea;
    }
}
