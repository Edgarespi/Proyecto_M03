/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouf3;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brandon, Bruno, Edgar
 */
public class ProyectoUF3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File fichero = new File("taules.txt");
        File usuarios = new File("users.dat");
        rol(usuarios, fichero);
    }
    
    private static void rol(File fichero1, File fichero2) {
        Scanner lector = new Scanner(System.in);
        boolean finalizar = false;
        int menu;
        do {
            System.out.println("1.Administrador");
            System.out.println("2.Camarero");
            System.out.println("3.Salir");
            System.out.print("¿Que rol tiene? ");
            menu = lector.nextInt();
            switch (menu) {
                case 1:
                    administrador(fichero1);
                    break;
                case 2:
                    camarero(fichero2);
                    break;
                case 3:
                    finalizar = true;
                    break;
                default:
                    System.out.println("Vuelva a escribir una opcion valida.");
            }
        } while (finalizar != true);
    }
    
    private static void camarero(File fichero) {
        Scanner lector = new Scanner(System.in);
        boolean finalizar = false;
        int menu;
        do {
            System.out.println("1.Listar mesas");
            System.out.println("2.Añadir mesas");
            System.out.println("3.Editar mesas");
            System.out.println("4.Eliminar mesas");
            System.out.println("5.salir");
            System.out.print("Seleccione una opcion: ");
            menu = lector.nextInt();
            switch (menu) {
                case 1:
                    listarMesas(fichero);
                    break;
                case 2:
                    añadirMesas(fichero);
                    break;
                case 3:
                    editarMesas(fichero);
                    break;
                case 4:
                    eliminarMesas(fichero);
                    break;
                case 5:
                    finalizar = true;
                    break;
                default:
                    System.out.println("Vuelva a escribir una opcion valida.");
            }
        } while (finalizar != true);
    }
    
    private static void administrador(File usuarios) {
        Scanner lector = new Scanner(System.in);
        boolean finalizar = false;
        int menu;
        do {
            System.out.println("1.Crear Usuario");
            System.out.println("2.Ver Usuarios");
            System.out.println("3.Salir");
            System.out.print("Seleccione una opcion: ");
            menu = lector.nextInt();
            switch (menu) {
                case 1:
                    añadirUsuarios(usuarios);
                    break;
                case 2:
                    listarUsuarios(usuarios);
                    break;
                case 3:
                    finalizar = true;
                    break;
                default:
                    System.out.println("Vuelva a escribir una opcion valida.");
            }
        } while (finalizar != true);
    }

    private static void listarMesas(File fichero) {
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
    
    private static void listarUsuarios(File usuarios) {
        try {
            Scanner lectorFichero = new Scanner(usuarios, "UTF-8");
            while (lectorFichero.hasNext()) {
                String[] linea = lectorFichero.nextLine().split(",");
                System.out.println("=========================================");
                System.out.println("Rol ==> " + linea[0]);
                System.out.println("Usuario  ==> " + linea[1]);
            }
            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }
    }

    private static void añadirMesas(File fichero) {
            String nueva_Fila = nuevaMesa();
        try {
            FileWriter writer = new FileWriter(fichero, true);
            writer.write(nueva_Fila);
            writer.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }
    }
    
    private static void añadirUsuarios(File usuarios) {
            String nueva_Fila = nuevoUsuario();
        try {
            FileWriter writer = new FileWriter(usuarios, true);
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
        String DescMesa = lector.next();
        System.out.println("Introducir cantidad maxima de personas: ");
        String cantidadPersonas = lector.next();
        System.out.println("Indica si hay mesas de niños: ");
        String sillasN = lector.next();
        System.out.println("Indica el numero de sillas de Adultos:");
        String sillasA = lector.next();
        System.out.println("Indica si hay ventilador");
        String ventilador = lector.next();
        System.out.println("Indica si la mesa esta en el jardin:");
        String mesaJardin = lector.next();
        String nuevaLinea = id + "," + DescMesa + "," + cantidadPersonas + "," + sillasN + "," + sillasA + "," + ventilador + "," + mesaJardin;
        return nuevaLinea;
    }
    
    private static String nuevoUsuario() {
        Scanner lector = new Scanner(System.in);
        System.out.println("Rol: ");
        String rol = lector.next();
        System.out.println("Nombre: ");
        String nombre = lector.next();
        System.out.println("Contraseña: ");
        String passwd = lector.next();
        String nuevaLinea = rol + "," + nombre + "," + passwd + "\n";
        return nuevaLinea;
    }

    private static void editarMesas(File fichero) {
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
    private static void eliminarMesas(File fichero) {
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
