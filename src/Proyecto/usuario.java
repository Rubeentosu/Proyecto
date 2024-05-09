package Proyecto;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class usuario implements Comparable{
    private static int userID = 1;
    private String name;
    private  String pass;

    //Constructor
    public usuario(String name, String pass) {
        userID++;
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
    public boolean validPass (String pass){
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
    public static void crearGrupo(usuario usuario){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del grupo: ");
        int id = sc.nextInt();

        System.out.println("Ingrese el nombre del grupo: ");
        String nombreGrupo = sc.next();

        Grupo nuevoGrupo= new Grupo(nombreGrupo,12);

        grupos.add(nuevoGrupo);
        System.out.println("Grupo '" + nombreGrupo + "' creado por " + usuario);
    }

    //Eliminar Grupo
    public static void eliminarGrupo(usuario usuario) {
        Scanner sc = new Scanner(System.in);
        int id;
        System.out.println("Id del grupo que quieres eliminar");
        id = sc.nextInt();
        grupos.remove(id);
        System.out.println("Grupo '" + id + "' eliminado por " + usuario);
    }

    //Funcion verGrupos
    public static void verGrupos(){
        System.out.println("Grupos: ");
        for (Grupo grupo : grupos) {
            System.out.println(grupo.toString());
        }
    }

}
