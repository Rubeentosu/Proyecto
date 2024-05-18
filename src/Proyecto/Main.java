package Proyecto;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class Main {


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
    //Menus
    public static void imprimirMenuInicio(){
        System.out.println("TRICOUNT");
        System.out.println("Seleccione una opción: ");
        System.out.println("1.- Iniciar Sesión");
        System.out.println("2.- Registrarse");
        System.out.println("3.- Salir");
    }

    public static void imprimirMenuUsuario( ArrayList<usuario> lista, int index){
        System.out.println("BIENVENIDO " + lista.get(index).getName() + "!");
        if (lista.get(index).getGruposPertenece().isEmpty()){
            System.out.println("Aún no perteneces a ningún grupo. Seleccione una opción:");
            System.out.println("1.- Crear nuevo grupo");
            System.out.println("2.- Salir");
        }else{
            System.out.println("Seleccione el número del grupo que desea gestionar");
            int i = 0;
            for (int j=i; j < lista.get(index).getGruposPertenece().size(); j++) {
                System.out.println(j+1 + ".- "+lista.get(index).getGruposPertenece().get(j).getNombre());
                i=j+2;
            }
            System.out.println(i+".- Crear nuevo grupo");
            System.out.println(i+1+".- Salir");
        }
    }

    public static void menuUsuario(ArrayList<usuario> lista, int index){
        int opcion = -1;
        boolean salir = false;
            do{
                imprimirMenuUsuario(lista, index);
                opcion=leerNum()-1;
                if(opcion==lista.get(index).getGruposPertenece().size()){
                    lista.get(index).crearGrupo(lista.get(index));
                }else if (opcion==lista.get(index).getGruposPertenece().size()+1){
                    salir=true;
                    System.out.println("Volviendo al menú principal");
                }else if (opcion < 0 || opcion>lista.get(index).getGruposPertenece().size()+1){
                    System.out.println("Escriba una opción válida");
                }else{
                    menuGrupo(lista, index, opcion);
                }
            }while (!salir);
    }
    public static void menuGrupo(ArrayList<usuario> lista, int indexU, int indexG){
        System.out.println("Grupo: " + lista.get(indexU).getGruposPertenece().get(indexG).getNombre());
        imprimirMenuGrupo(lista, indexU, indexG);

    }
    public static void imprimirMenuGrupo(ArrayList<usuario> lista, int indexU, int indexG){
        if (lista.get(indexU).getUserID() - lista.get(indexU).getGruposPertenece().get(indexG).getIdAdmin()==0){
            System.out.println("eres el admin");
        }else{
            System.out.println("no eres el admin");
        }
    }

    //Inicio de sesión
    public static int inicioSesion(ArrayList<usuario> lista){
        System.out.println("Introduzca su nombre de usuario");
        String nombreUsuario = leerCadena();
        if(comprobarUsuarioExiste(lista, nombreUsuario)==-1){
            throw new ArrayStoreException("No existe ningún usuario con ese nombre");
        }
        System.out.println("Introduzca la contraseña");
        String pass = leerCadena();
        int index = comprobarUsuarioExiste(lista, nombreUsuario);
        if (!lista.get(index).getPass().equals(pass)){
            throw new InputMismatchException("La contraseña introducida no es correcta");
        }
        return index;
    }

    //Función que pide los datos para crear un un usuario y lo mete en la lista de usuarios
    public static int registrarUsuario(ArrayList<usuario> lista){
        System.out.println("Introduzca el nombre de usuario que desea");
        String nombreUsuario = leerCadena();
        if(comprobarUsuarioExiste(lista, nombreUsuario)!=-1){
            throw new ArrayStoreException("Ya existe un usuario con ese nombre");
        }
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

    //Función que comprueba si un nombre de usuario ya está en la lista (no pueden haber dos iguales).
    //Devuelve su indice en la lista, si no existe devuelve -1
    public static int comprobarUsuarioExiste(ArrayList<usuario> lista,String nombre){
        int index=-1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getName().equals(nombre)) index = i;
        }
        return index;
    }

    public static void main(String[] args) {
        ArrayList<usuario> usuarios = new ArrayList<>();
        usuarios.add(new usuario("Javi", "Javier1997"));
        usuarios.add(new usuario("Yasir", "Yasir2005"));
        usuarios.add(new usuario("David", "David1997"));
        usuarios.add(new usuario("Mikel", "Javier1997"));


        usuarios.get(0).crearGrupo(usuarios.get(0));
        usuarios.get(0).crearGrupo(usuarios.get(0));
        usuarios.get(0).crearGrupo(usuarios.get(0));

        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(1), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(2), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(3), usuarios);


        /*
        System.out.println(usuarios.get(0).getGruposPertenece());
        System.out.println(usuarios.get(1).getGruposPertenece());
        usuarios.get(0).eliminarGrupo(usuarios.get(0));
        System.out.println(usuarios.get(0).getGruposPertenece());
        System.out.println(usuarios.get(1).getGruposPertenece());

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
        int opcion2=10;
        int index;

        do{
            imprimirMenuInicio();
            opcion1=leerNum();
            switch (opcion1){
                case 1:
                    try{
                        index = inicioSesion(usuarios);
                        menuUsuario(usuarios, index);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try{
                        index = registrarUsuario(usuarios);
                        menuUsuario(usuarios, index);

                    }catch(ArrayStoreException e){
                        System.out.println(e.getMessage());
                    }


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