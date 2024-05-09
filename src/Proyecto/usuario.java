package Proyecto;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class usuario implements Comparable{
    private int userID = 1;
    private String name;
    private  String pass;

    public usuario(int userID, String name, String pass) {
        this.userID = userID;
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

    @Override
    public int compareTo(Object obj) {
        usuario user =(usuario) obj;
        return this.getName().compareTo(user.getName());
    }

    static ArrayList<Grupo> grupos = new ArrayList<>();
    public static void crearGrupo(usuario usuario){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del grupo: ");
        int id = sc.nextInt();

        System.out.println("Ingrese el nombre del grupo: ");
        String nombreGrupo = sc.next();

        Grupo nuevoGrupo= new Grupo(id,nombreGrupo,usuario);

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
