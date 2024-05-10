package Proyecto;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class usuario implements Comparable{
    private static int numUsuario=0;
    private int userID;
    private String name;
    private  String pass;

    //Constructor
    public usuario(String name, String pass) {
        numUsuario++;
        this.userID=numUsuario;
        this.name = name;
        this.pass = pass;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        usuario usuario = (usuario) o;
        return userID == usuario.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userID);
    }

    @Override
    public String toString() {
        return "usuario{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    //Compare to por Nombre
    @Override
    public int compareTo(Object obj) {
        usuario user =(usuario) obj;
        return this.getName().compareTo(user.getName());
    }

    //Función que comprueba que una contraseña es válida, con mayúsculas, minúsculas, números, ext
    public static boolean validPass (String pass){
        boolean lowerCase= false;
        boolean upperCase= false;
        boolean num = false;
        boolean space = true;
        for (int i = 0; i < pass.length(); i++){
            if (pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z'){
                lowerCase=true;
            }
            if (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z'){
                upperCase=true;
            }
            if (pass.charAt(i) >= '0' && pass.charAt(i) <= '9'){
                num=true;
            }
            if (pass.charAt(i) == ' '){
                space=false;
            }
        }
        if (lowerCase && upperCase && num && space) {
            return true;
        }
        else {
            if (!lowerCase) throw new ArithmeticException("No hay ninguna letra minúscula");
            else if (!upperCase) throw new InputMismatchException("No hay ninguna letra mayúscula");
            else if (!num) throw new ArrayIndexOutOfBoundsException("No hay ninguna número");
            else if (!space) throw new RuntimeException("Hay un espacio en la contraseña");
            return false;
        }
    }
    static ArrayList<Grupo> grupos = new ArrayList<>();
    public static void crearGrupo(ArrayList<usuario> usuarios) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el nombre del grupo: ");
        String nombreGrupo = sc.next();

        System.out.println("Ingrese el idUsuario: ");
        int idUsuario = sc.nextInt();

        // Verificar si el idUsuario especificado existe en la lista de usuarios
        boolean usuarioExiste = false;
        for (usuario user : usuarios) {
            if (user.getUserID() == idUsuario) {
                usuarioExiste = true;
                break;
            }
        }
        if (!usuarioExiste) {
            System.out.println("El usuario especificado no existe.");
            return;
        }

        Grupo nuevoGrupo = new Grupo(nombreGrupo, idUsuario);
        grupos.add(nuevoGrupo);
        System.out.println("Grupo '" + nombreGrupo + "' creado por usuario con id " + idUsuario);
    }

    //Eliminar un grupo
    public static void eliminarGrupo(ArrayList<usuario> usuarios) {
        Scanner sc = new Scanner(System.in);

        // Verificar si hay grupos para eliminar
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos para eliminar.");
            return;
        }

        //Muestra los grupos que se pueden eliminar
        for (Grupo gr : grupos) {
            System.out.println(gr.toString());
        }

        // Solicitar al usuario el ID del grupo que desea eliminar
        System.out.println("Ingrese el ID del grupo que desea eliminar:");
        int idGrupo = sc.nextInt();

        // Buscar el grupo en la lista de grupos
        Grupo grupoAEliminar = null;
        for (Grupo grupo : grupos) {
            if (grupo.getIdGrupo() == idGrupo) {
                grupoAEliminar = grupo;
                break;
            }
        }

        // Verificar si se encontró el grupo
        if (grupoAEliminar == null) {
            System.out.println("No se encontró un grupo con el ID especificado.");
            return;
        }

        // Verificar si el usuario tiene permisos para eliminar el grupo
        System.out.println("Ingrese el ID del usuario administrador para confirmar la eliminación del grupo:");
        int idAdministrador = sc.nextInt();

        // Buscar el usuario administrador en la lista de usuarios
        usuario administrador = null;
        for (usuario user : usuarios) {
            if (user.getUserID() == idAdministrador) {
                administrador = user;
                break;
            }
        }

        // Verificar si se encontró el usuario administrador
        if (administrador == null || administrador.getUserID() != grupoAEliminar.getIdUsuario()) {
            System.out.println("No tiene permisos para eliminar este grupo o el administrador especificado no existe.");
            return;
        }

        // Eliminar el grupo
        grupos.remove(grupoAEliminar);
        System.out.println("El grupo ha sido eliminado correctamente.");
    }

    //Funcion verGrupos
    public static void verGrupos(){
        boolean hayGrupo = false;
        if (!hayGrupo){
            for (Grupo grupo : grupos) {
                System.out.println("Grupos: ");
            System.out.println(grupo.toString());
            }
        }else {
            System.out.println("No hay grupos creados");
        }
    }
}