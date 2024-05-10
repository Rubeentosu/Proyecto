package Proyecto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<usuario> usuarios = new ArrayList<>();

        int opcion = 0;
        int duracion;
        do {
            // Mostrar el menú
            imprimirMenu();
            // Leer la opción del usuario
            System.out.print("Seleccione una opción: ");

            opcion =leerOpcion();

            switch (opcion) {
                case 1:
                    // Registrar usuario
                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombre = leerTexto();
                    System.out.print("Ingrese la contraseña del usuario: ");
                    String contraseña = leerTexto();
                    if (usuario.validPass(contraseña)) {usuarios.add(new usuario(nombre,contraseña));}
                    System.out.println("Usuario añadido.");
                    break;
                case 2:
                    //Ver Usuarios
                    boolean hayUsuarios =false;
                    if (!hayUsuarios){
                        for (usuario usuario : usuarios){
                            System.out.println(usuario.toString());
                        }
                    }else{
                        System.out.println("No hay usuarios registrados");
                    }
                    break;
                case 3:
                    usuario.verGrupos();
                    break;
                case 4:
                    usuario.crearGrupo((ArrayList<usuario>) usuarios);

                    break;
                case 5:
                    usuario.eliminarGrupo((ArrayList<usuario>) usuarios);
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:
                    // Salir del programa
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }

        } while (opcion != 10);
    }

    public static void imprimirMenu(){
        System.out.println("\nMenú:");
        System.out.println("1. Registrarse");
        System.out.println("2. Ver usuarios");
        System.out.println("3. Ver grupos");
        System.out.println("4. Crear grupo");
        System.out.println("5. Eliminar grupo");
        System.out.println("6. Añadir usuario");
        System.out.println("7. Eliminar Usuario");
        System.out.println("8. Ver saldo");
        System.out.println("9. Añadir gastos");
        System.out.println("10. Salir");
    }
    public static int leerOpcion(){
        Scanner sc = new Scanner(System.in);
        boolean error=false;
        do {
            error=false;
            try {
                return sc.nextInt();}catch (InputMismatchException e){
                System.out.println("Error, debes introducir un numero entero");
                error=true;
            }
        }while (!error);
        return 0;
    }
    public static String leerTexto(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static int leerNumero(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
