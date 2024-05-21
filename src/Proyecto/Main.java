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
        //si no pertenece a ningún grupo, mostramos este menu
        if (lista.get(index).getGruposPertenece().isEmpty()){
            System.out.println("Aún no perteneces a ningún grupo. Seleccione una opción:");
            System.out.println("1.- Crear nuevo grupo");
            System.out.println("2.- Salir");
        }else{//Si tiene grupos, mostramos menu con los grupos y la opción de crear más o salir
            System.out.println("Seleccione el número del grupo que desea gestionar");
            int i = 0;
            //Imprimimos la lista de grupos a los que pertenece el usuario que ha iniciado sesión
            for (int j=i; j < lista.get(index).getGruposPertenece().size(); j++) {
                System.out.println(j+1 + ".- "+lista.get(index).getGruposPertenece().get(j).getNombre());
                i=j+2;
            }
            System.out.println(i+".- Crear nuevo grupo");
            System.out.println(i+1+".- Salir");
        }
    }
    //Menú de usuario una vez autentificado
    public static void menuUsuario(ArrayList<usuario> lista, int index){
        int opcion = -1;
        boolean salir = false;
        //Al ser opcion unos numeros variables dependiendo del numero del grupos a los que pertenezca
        //el usuario, lo hacemos con ifs en vez de swtich
            do{
                imprimirMenuUsuario(lista, index);
                opcion=leerNum()-1;//Le restamos uno porque el indice 0 del array está como opción 1
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
        boolean salir = false;
        int opcion = -1;
        do{
            imprimirMenuGrupo(lista, indexU, indexG);
            opcion=leerNum();
            if (lista.get(indexU).getUserID()==lista.get(indexU).getGruposPertenece().get(indexG).getIdAdmin()){
                switch (opcion){
                    case 1:
                        addGasto(lista, indexU, indexG);
                        break;
                    case 2:
                        eliminarGasto(lista, indexU, indexG);
                        break;
                    case 3:
                        imprimirSaldos(lista.get(indexU).getGruposPertenece().get(indexG).verSaldo());
                        break;
                    case 4:
                        try{
                            System.out.println(lista.get(0).getGruposPertenece().get(0).ajusteDeCuentas(0));
                        }catch (NoSuchElementException e){
                            System.out.println(lista.get(0).getGruposPertenece().get(0).ajusteDeCuentas(1));
                        }
                        break;
                    case 5:
                        añadirUsuario(lista, indexU, indexG);
                        break;
                    case 6:
                        System.out.println("¿Estás seguro/a de que deseas eliminar este grupo?");
                        System.out.println("1.- Si");
                        System.out.println("2.- No");
                        int o2= leerNum();
                        if (o2==1){
                            try{
                                lista.get(indexU).eliminarGrupo(lista.get(indexU), lista.get(indexU).getGruposPertenece().get(indexG));
                            }catch (RuntimeException e){
                                System.out.println(e.getMessage());
                            }
                        } else if (o2!=2 && o2!=1) {
                            System.out.println("Seleccione una opción válida");
                        }
                        salir=true;
                    case 7:
                        salir=true;
                        break;
                    default:
                        System.out.println("Seleccione una opción válida");
                        break;
                }
            }else{
                switch (opcion){
                    case 1:
                        addGasto(lista, indexU, indexG);
                        break;
                    case 2:
                        eliminarGasto(lista, indexU, indexG);
                        break;
                    case 3:
                        imprimirSaldos(lista.get(indexU).getGruposPertenece().get(indexG).verSaldo());
                        break;
                    case 4:
                        try{
                            System.out.println(lista.get(0).getGruposPertenece().get(0).ajusteDeCuentas(0));
                        }catch (NoSuchElementException e){
                            System.out.println(lista.get(0).getGruposPertenece().get(0).ajusteDeCuentas(1));
                        }
                        break;
                    case 5:
                        salir=true;
                        break;
                    default:
                        System.out.println("Seleccione una opción válida");
                        break;
                }
            }
        }while(!salir);

    }
    public static void imprimirMenuGrupo(ArrayList<usuario> lista, int indexU, int indexG){
        if (lista.get(indexU).getUserID() - lista.get(indexU).getGruposPertenece().get(indexG).getIdAdmin()==0){
            System.out.println("Eres el Administrador de este grupo");
            System.out.println("Seleccione una acción:");
            System.out.println("1.- Añadir un gasto");
            System.out.println("2.- Eliminar un gasto");
            System.out.println("3.- Ver saldos");
            System.out.println("4.- Ajustar cuentas");
            System.out.println("5.- Añadir usuario");
            System.out.println("6.- Eliminar grupo");
            System.out.println("7.- Salir");

        }else{
            System.out.println("Seleccione una acción:");
            System.out.println("1.- Añadir un gasto");
            System.out.println("2.- Eliminar un gasto");
            System.out.println("3.- Ver saldos");
            System.out.println("4.- Ajustar cuentas");
            System.out.println("5.- Salir");
        }
    }
    //Función para añadir un gasto
    public static void addGasto(ArrayList<usuario> lista, int indexU, int indexG){
        System.out.println("Escriba la descripción del gasto");
        String des = leerCadena();
        System.out.println("Introduzca el importe total");
        double cantidad = leerDouble();
        lista.get(indexU).añadirGastos(lista.get(indexU).getGruposPertenece().get(indexG), des, cantidad, lista.get(indexU));
    }

    //Función que le imprime al usuario sus gastos, para elegir cual de ellos puede eliminar. Lo pide y lo elimina
    public static void eliminarGasto(ArrayList<usuario> lista, int indexU, int indexG){
        int opcion = -1;
        boolean salir=false;
        int contador = 1;

        System.out.println("Seleccione el gasto que desea eliminar");
        //Creamos una lita con los gastos
        List<Gasto> gastosUsuario = lista.get(indexU).getGruposPertenece().get(indexG).getGastos().stream()
                .filter(g -> g.getPagador()==lista.get(indexU).getUserID())
                .toList();
        //Los imprimimos numerados
        for (Gasto g: gastosUsuario){
            System.out.println(contador+" .- "+g);
            contador++;
        }
        System.out.println(contador+" .- Salir");
        //Al ser un número variable, no podemos hacer switch, hacemos ifs
        do{
            opcion=leerNum()-1;
            if (opcion<0 || opcion>gastosUsuario.size()){
                System.out.println("Seleccione una opción válida");
            }else if (opcion==gastosUsuario.size()) {
                salir=true;
            }else{
                lista.get(indexU).eliminarGastos(lista.get(indexU).getGruposPertenece().get(indexG), gastosUsuario.get(opcion).getId());
                salir=true;
            }
        }while (!salir);
    }
    //Función que imprime el mapa de los gastos de forma bonita
    public static void imprimirSaldos (Map<usuario, Double> saldo){
        saldo.entrySet().forEach(entrada -> System.out.println(entrada.getKey().getName() + "---->" + entrada.getValue()));
    }
    //Función de administrador que añade un usuario a un grupo
    public static void añadirUsuario(ArrayList<usuario> lista, int indexU, int indexG){
        System.out.println("Escriba el nombre del usuario que desea introducir en el grupo");
        String nombre = leerCadena();
        //Comprobamos que existe (Si la función que comprueba no encuentra un usario, devuelve -1)
        if(comprobarUsuarioExiste(lista, nombre)!=-1){
            lista.get(indexU).addUser(lista.get(indexU).getGruposPertenece().get(indexG), lista.get(comprobarUsuarioExiste(lista, nombre)),lista);
        }else{
            System.out.println("El usuario "+nombre+" no está registrado en nuestra base de datos");
        }

    }

    //Inicio de sesión
    public static int inicioSesion(ArrayList<usuario> lista){
        System.out.println("Introduzca su nombre de usuario");
        String nombreUsuario = leerCadena();
        //Comprobamos que existe
        if(comprobarUsuarioExiste(lista, nombreUsuario)==-1){
            throw new ArrayStoreException("No existe ningún usuario con ese nombre");
        }
        System.out.println("Introduzca la contraseña");
        String pass = leerCadena();
        //Sacamos su posición del array
        int index = comprobarUsuarioExiste(lista, nombreUsuario);
        if (!lista.get(index).getPass().equals(pass)){
            throw new InputMismatchException("La contraseña introducida no es correcta");
        }
        //Devolvemos el index
        return index;
    }

    //Función que pide los datos para crear un un usuario y lo mete en la lista de usuarios
    public static int registrarUsuario(ArrayList<usuario> lista){
        System.out.println("Introduzca el nombre de usuario que desea");
        String nombreUsuario = leerCadena();
        //Comprobamos si existe
        if(comprobarUsuarioExiste(lista, nombreUsuario)!=-1){
            throw new ArrayStoreException("Ya existe un usuario con ese nombre");
        }
        System.out.println("Introduzca la contraseña");
        String pass = leerCadena();
        int index = -1;
        //Creamos usuario
        try{
            usuario u = new usuario(nombreUsuario, pass);
            lista.add(u);
            index = lista.indexOf(u);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Devolvemos su index
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
        usuarios.add(new usuario("Javi", "Platano17"));
        usuarios.add(new usuario("Rubén", "Gamba2000"));
        usuarios.add(new usuario("Adrian", "Mandarina1"));
        usuarios.add(new usuario("Mikel", "Javier1997"));
        usuarios.add(new usuario("Carmen", "Javier1997"));


        usuarios.get(0).crearGrupo(usuarios.get(0));
        usuarios.get(0).crearGrupo(usuarios.get(0));
        usuarios.get(0).crearGrupo(usuarios.get(0));

        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(1), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(2), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(3), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(4), usuarios);
        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto1", 17, usuarios.get(0));
        System.out.println(usuarios.get(0).getGruposPertenece().get(0).verSaldo());



        //Aplicación principal
        boolean salir = false;
        int opcion1=-1;
        int index=-1;
        //Menú
        do{
            imprimirMenuInicio();
            opcion1=leerNum();
            switch (opcion1){
                case 1://Iniciar Sesión
                    try{
                        index = inicioSesion(usuarios);
                        menuUsuario(usuarios, index);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2://Registrar Usuario
                    try{
                        index = registrarUsuario(usuarios);
                        if(index!=-1){//Si el index es el valor por defecto(al no crear usuario)
                            menuUsuario(usuarios, index);
                        }
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3://Salir
                    System.out.println("Hasta la próxima");
                    salir=true;
                    break;
                default:
                    System.out.println("Escriba una opción válida");
                    break;
            }
        }while(!salir);
    }
}