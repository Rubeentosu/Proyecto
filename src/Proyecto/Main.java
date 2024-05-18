package Proyecto;

import java.util.*;

public class Main {
    //Menus
    public static void imprimirMenuInicio(){
        System.out.println("TRICOUNT");
        System.out.println("Seleccione una opción: ");
        System.out.println("1.- Iniciar Sesión");
        System.out.println("2.- Registrarse");
        System.out.println("3.- Salir");
    }

    public static void imprimirMenuUsuario(){
        System.out.println("TRICOUNT");

    }
    //función que lee una cadena
    public static String leerCadena(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    //Función que lee un double
    public static int leerNum(){
        Scanner sc = new Scanner(System.in);
        int num = 0;
        boolean error;
        do{
            error= false;
            try{
                num= sc.nextInt();
                error=false;
            }catch (InputMismatchException e){
                error = true;
                System.out.println("Introduzca un número");
            }finally {
                sc.nextLine();
            }
        }while (error);
        return num;
    }


    //Función que lee un double
    public static double leerDouble(){
        Scanner sc = new Scanner(System.in);
        double num = 0;
        boolean error;
        do{
            error= false;
            try{
                num= sc.nextDouble();
                error=false;
            }catch (InputMismatchException e){
                error = true;
                System.out.println("Introduzca un número");
            }finally {
                sc.nextLine();
            }
        }while (error);
        return num;
    }

    //Función que pide los datos para crear un un usuario y lo mete en la lista de usuarios
    public static int registrarUsuario(ArrayList<usuario> lista){
        System.out.println("Introduzca el nombre de usuario que desea");
        String nombreUsuario = leerCadena();
        System.out.println("Introduzca la contraseña");
        String pass = leerCadena();
        int index = 0;
        try{
            usuario u = new usuario(nombreUsuario, pass);
            lista.add(u);
            index = lista.indexOf(u);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return index;
    }


    public static void main(String[] args) {
        ArrayList<usuario> usuarios = new ArrayList<>();
        usuarios.add(new usuario("Javi", "Javier1997"));
        usuarios.add(new usuario("Yasir", "Yasir2005"));
        usuarios.add(new usuario("David", "David1997"));
        usuarios.add(new usuario("Mikel", "Javier1997"));

        /*
        usuarios.get(0).crearGrupo(usuarios.get(0));
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(1), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(2), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(3), usuarios);
        System.out.println("El grupo 1 está compuesto por"+usuarios.get(0).getGruposPertenece().get(0).getComponentes());
        System.out.println("Yasir pertenece al grupo" +usuarios.get(1).getGruposPertenece());
        //usuarios.get(0).eliminarUsuario(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(1), usuarios);
        System.out.println("El grupo 1 está compuesto por"+usuarios.get(0).getGruposPertenece().get(0).getComponentes());

        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto1",25, usuarios.get(0));
        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto3",40, usuarios.get(1));
        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto6",7, usuarios.get(2));
        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto4",2, usuarios.get(2));
        System.out.println(usuarios.get(0).getGruposPertenece().get(0).getGastos());

        Map<usuario, Double> saldos  = usuarios.get(0).getGruposPertenece().get(0).verSaldo();

        System.out.println("El gasto medio es "+ usuarios.get(0).getGruposPertenece().get(0).gastoMedio());

        System.out.println(saldos);
        System.out.println(usuarios.get(0).getGruposPertenece().get(0).ajusteDeCuentas());
        */
        boolean salir = false;
        int opcion1=10;

        do{
            imprimirMenuInicio();
            opcion1=leerNum();
            switch (opcion1){
                case 1:

                    break;
                case 2:
                    int index = registrarUsuario(usuarios);

                    break;
                case 3:
                    System.out.println("Hasta la próxima");
                    salir=true;
                    break;
                default:
                    System.out.println("Escriba una opción válida");
            }
        }while(!salir);


    }
}