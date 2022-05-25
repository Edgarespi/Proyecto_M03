/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouf3;
//Herramientas que usuaremos para las funciones de añadir crear y editar
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brandon, Bruno, Edgar
 */
public class ProyectoUF3 {

    static Usuario[] usuarios;
    
    public static void main(String[] args) {
        File fichero = new File("taules.txt");
        //Crearemos un array de 100 usuarios
        usuarios = new Usuario[100];
        //Llamaremos a la funcion generarUsuarioAdmin
        generarUsuarioAdmin();
    }
    //Funcion para poder generar un usuari automanticamente, en este caso iniciaremos como administrador
    private static void generarUsuarioAdmin() {
        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("fichero.dat"));
            //Utilizando las clases queremos colocar un usuario default
            usuarios[0] = new Usuario();
            usuarios[0].rol = "Administrador";
            usuarios[0].nombre = "bbe";
            usuarios[0].contraseña = "passwd";
            fichero.writeObject(usuarios);
            fichero.close();
        } catch (Exception e) {
            //En caso de que el usuario agregue un numero distinto aparezca este mensaje
            System.out.println("Ocurrio un error dentro de la lectura del fichero :(");
        }
    }
    //Creacion del menu de camarero
    private static void camarero(File fichero) {
        Scanner lector = new Scanner(System.in);
        boolean finalizar = false;
        int menu;
        //Haremos todo dentro de un DO WHILE con la finalidad de que el usuario pueda elegir mas opciones sin necesidad de abrir otra vez
        //el programa, tambien ofrece la opcion de salir.
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
                    //Lamamos las funciones creadas
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
                    //En caso de que el usuario quiera salir colocara un 5 con lo cual se activara este booleam para salir
                    finalizar = true;
                    break;
                default:
                    //En caso de que el usuario agregue un numero distinto aparezca este mensaje
                    System.out.println("Vuelva a escribir una opcion valida.");
            }
            //Indicamos cuando queremos que el programa se acabe
        } while (finalizar != true);
    }
    
    private static void administrador() {
        Scanner lector = new Scanner(System.in);
        boolean finalizar = false;
        int menu;
        do {
            System.out.println("1. Crear Usuario");
            System.out.println("2. Usuarios");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            menu = lector.nextInt();
            switch (menu) {
                case 1:
                    añadirUsuarios();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    finalizar = true;
                    break;
                default:
                    System.out.println("Vuelva a escribir una opcion valida.");
            }
        } while (finalizar != true);
    }
    
        //Funcion que lee el fichero para poder colocar la informacion del fichero en memoria
    private static void lecturaTaules(File fichero) {
        try {
            Scanner lectorFichero = new Scanner(fichero, "UTF-8");
            while (lectorFichero.hasNext()) {
                System.out.println(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            //En caso de que el usuario agregue un numero distinto aparezca este mensaje
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }
    }
        //Funcion de listar mesas que tiene como finalidad poder colocar la informacion de cada mesa con
        //con su respectica descripcion
    private static void listarMesas(File fichero) {
        try {
            Scanner lectorFichero = new Scanner(fichero, "UTF-8");
            while (lectorFichero.hasNext()) {
                //En esta parte haremos que a la hora de leer el fichero y se encuentre con una coma, salte al siguiente dato
                //con la finalidad prevenir errores de que la informacion este junta a la hora de imprimir resultados
                String[] linea = lectorFichero.nextLine().split(",");
                System.out.println("=========================================");
                //Añadimos la id de mesas
                System.out.println("Id taula ==> " + linea[0]);
                //Colocamos la ubicacion de la mesa
                System.out.println("Descripciió o Ubicacion de la mesa  ==> " + linea[1]);
                //Colocamos la cantidad maxima de personas
                System.out.println("Cantidad máxima de persones  ==> " + linea[2]);
                //Verificamos si hay sillas de niños o no
                System.out.println("Tiene sillas de niños?  ==> " + linea[3]);
                //Colocamos la cantidad de sillas para adultos
                System.out.println("Cantidad de sillas para adultos  ==> " + linea[4]);
                //Verificamos si tienen ventilador
                System.out.println("Tiene Ventilador ?  ==> " + linea[5]);
                //Comprobamos si la mesa esta en eljardin o no
                System.out.println("Esta ubicada en el Jardin?  ==> " + linea[6]);
                System.out.println("");

            }
                //Una vez acabado cerramos el fichero
            lectorFichero.close();

        } catch (Exception e) {
            //En caso de que el usuario agregue un numero distinto aparezca este mensaje
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }
    }
    
    private static void listarUsuarios() {
        try {
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("fichero.dat"));
            Usuario[] usuarios = (Usuario[]) fichero.readObject();
            for (Usuario usuario : usuarios) {
                if (usuario != null) {
                    System.out.println("Rol: " + usuario.rol);
                    System.out.println("Usuario: " + usuario.nombre);
                    System.out.println("Contraseña: " + usuario.contraseña);
                    System.out.println("--------------------------");
                }
            }
            fichero.close();
        } catch (Exception e) {
            System.out.println("Error al abrir o leer el fichero");
        }
    }
    
        //Funcion para añadir mesas
    private static void añadirMesas(File fichero) {
        String nueva_Fila = nuevaMesa();
        try {
            FileWriter writer = new FileWriter(fichero, true);
            //Añadimos la nueva mesa utilizando write.write con lo cual escribe lo que habra dentro de la funcion nueva_fila
            writer.write(nueva_Fila);
            writer.close();

        } catch (Exception e) {
            //En caso de que el usuario agregue un numero distinto aparezca este mensaje
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }
    }
    
    private static void añadirUsuarios() {
        Scanner lector = new Scanner(System.in);
        System.out.print("Rol: ");
        String rol = lector.nextLine();
        System.out.print("Usuario: ");
        String nombre = lector.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = lector.nextLine();
        int NumUsuarios = 0;
        boolean comprobar = true;
        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("fichero.dat"));

            while (comprobar != false) {
                NumUsuarios++;
                if (usuarios[NumUsuarios] == null) {
                    usuarios[NumUsuarios] = new Usuario();
                    usuarios[NumUsuarios].rol = rol;
                    usuarios[NumUsuarios].nombre = nombre;
                    usuarios[NumUsuarios].contraseña = contraseña;
                    comprobar = false;
                }

            }

            fichero.writeObject(usuarios);
            fichero.close();
        } catch (Exception e) {
            System.out.println("Error al abrir o leer el fichero");
        }
    }
    
        //Funcion para poder crear una nueva mesa
    private static String nuevaMesa() {
        Scanner lector = new Scanner(System.in);
        //Introducimos la nueva id de la mesa
        System.out.println("Introducir Id Mesa: ");
        String id = lector.next();
        //Colocamos la descripcion de la mesa
        System.out.println("Descripcion de Mesa: ");
        String DescMesa = lector.next();
        //Introducimos la cantidad de personas de la mesa
        System.out.println("Introducir cantidad maxima de personas: ");
        String cantidadPersonas = lector.next();
        //Indicamos si hay mesas de niños o no
        System.out.println("Indica si hay mesas de niños: ");
        String sillasN = lector.next();
        //Indicamos el numero de sillas de adultos en una mesa
        System.out.println("Indica el numero de sillas de Adultos:");
        String sillasA = lector.next();
        //Indicamos si hay ventilador o no
        System.out.println("Indica si hay ventilador");
        String ventilador = lector.next();
        //Verificamos si la mesa esta en el jardin o no
        System.out.println("Indica si la mesa esta en el jardin:");
        String mesaJardin = lector.next();
        //Toda la informacion colocada anteiormente lo colocaremos dentro una variable añadiendo las comas.
        String nuevaLinea = id + "," + DescMesa + "," + cantidadPersonas + "," + sillasN + "," + sillasA + "," + ventilador + "," + mesaJardin;
        //Haremos que la funcion retorne todo lo que colocamos dentro.
        return nuevaLinea;
    }
        //Funcion para poder editar mesas
    private static void editarMesas(File fichero) {
        Scanner lector = new Scanner(System.in);
        //Creamos dos variables
        String fila_creada, fila_nueva;
        //Preguntamos al usuario que desea editar
        System.out.println("Introduce que id quieres editar: ");
        //Guardaremos en la varible fila creada la id que queremos aditar
        fila_creada = lector.next();
        //Abrimos un array en el cual guardaremos la lineas del fichero taules.txt
        //Con la finalidad de poder encontrar la id en especifico
        ArrayList<String> filas = new ArrayList<>();

        // Procedemos a abrirlo para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);
            while (lectorFichero.hasNext()) {
                filas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            //En caso de que el usuario agregue un numero distinto aparezca este mensaje
            System.out.println("Ocurrio un error dentro de la lectura del fichero :( ");
        }

        // Procedemos a escribir el fichero taules.txt
        try {
            FileWriter writer = new FileWriter(fichero);
              //Con el for haremos que leea todas las filas hasta coincidir con el la varible fila_creada
            for (String fila : filas) {
                //Haremos una comparacion con equals dentro de el colocaremos un substing el cual verifica que solo
                //utilicemos 4 caracteres de las id de la mesa que en este caso seria t_XX
                if (fila_creada.equals(fila.substring(0, 4))) {
                    //una vez encontrada utilizaremos la funcions de nueva mesa para volver a escribir los datos
                    fila_nueva = nuevaMesa();
                    //Escribiremos los nuevos datos utilizando write.write y colocando la fila_nueva
                    writer.write(fila_nueva + "\n");
                } else {
                    //en caso de no haber encontrado la fila tendra por defecto la misma
                    writer.write(fila + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            //En caso de que el usuario agregue un numero distinto aparezca este mensaje
            System.out.println("Ocurrio un error dentro de la lectura del fichero :(");
        }
    }
        //Funcion para eliminar mesas
    private static void eliminarMesas(File fichero) {
        Scanner lector = new Scanner(System.in);
        //Colocamos la siguiente variable
        String fila_eliminada;
        //Preguntaremos al usuario que id queremos eliminar
        System.out.println("Introduce que id quieres eliminar: ");
        //Guardaremos la id que eliminaremos en la siguiente variable
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
            //En caso de que el usuario agregue un numero distinto aparezca este mensaje
            System.out.println("Ocurrio un error dentro de la lectura del fichero :(");
        }
        
        // Procedemos a sobreescribir el fichero taules.txt y procederemos a eliminar la fila
        try {
            FileWriter writer = new FileWriter(fichero);
            //Con el for haremos que leea todas las filas hasta coincidir con el la varible fila_creada
            for (String fila : filas) {
                //Eliminaremos la fila 
                if (!fila_eliminada.equals(fila.substring(0, 4))) {
                    writer.write(fila + "\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            //En caso de que el usuario agregue un numero distinto aparezca este mensaje
            System.out.println("Ocurrio un error dentro de la lectura del fichero :(");
        }
    }
}
