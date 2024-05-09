package Proyecto;

import java.lang.reflect.Array;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class usuario implements Comparable{
    private static int numUsuarios=0;
    private int userID;
    private String name;
    private String pass;
    private ArrayList<Grupo> gruposPertenece;

    //Constructor
    public usuario(String name, String pass) {
        numUsuarios++;
        this.userID=numUsuarios;
        this.name = name;
        this.pass = pass;
        gruposPertenece= new ArrayList<>();
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

    public ArrayList<Grupo> getGruposPertenece() {
        return gruposPertenece;
    }

    public void setGruposPertenece(ArrayList<Grupo> gruposPertenece) {
        this.gruposPertenece = gruposPertenece;
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
    public void crearGrupo(usuario usuario){
        System.out.println("Ingrese el nombre del grupo: ");
        String nombreGrupo = leerCadena();

        Grupo nuevoGrupo= new Grupo(nombreGrupo,this.getUserID(), usuario);

        this.gruposPertenece.add(nuevoGrupo);
        System.out.println("Grupo '" + nombreGrupo + "' creado por " + usuario);
    }

    //Eliminar Grupo
    public void eliminarGrupo(usuario usuario) {
        Scanner sc = new Scanner(System.in);
        int id;
        System.out.println("Id del grupo que quieres eliminar");
        id = sc.nextInt();
        gruposPertenece.remove(id);
        System.out.println("Grupo '" + id + "' eliminado por " + usuario);
    }

    //Funcion verGrupos
    public void verGrupos(){
        System.out.println("Grupos: ");
        gruposPertenece.forEach(System.out::println);
    }

    //Función que añade un usuario a un grupo
    public boolean addUser(Grupo gr, usuario user, ArrayList<usuario> usuarios){
        boolean exito = false;
        //Primero comprobamos que el usuario que hace la función es el administrador del grupo
        if (this.userID==gr.getIdAdmin()){
            //Comprobamos que el usuario existe en la base de datos
            if (!usuarios.contains(user)){
                throw new InputMismatchException("El usuario no existe");
            }else {
                //Si exite, comprobamos si ya forma parte del grupo
                if (gr.getComponentes().contains(user)) {
                    throw new RuntimeException("El usuario ya forma parte del grupo");
                } else {
                    //Añadimos el usuario al grupo
                    gr.getComponentes().add(user);
                    //Añadimos a lista de grupos del nuevo usuario este grupo
                    user.getGruposPertenece().add(gr);
                    exito = true;
                }
            }
        }else{
           throw new ArithmeticException("Solo el administrador del grupo puede añadir o eliminar miembros");
        }
        return exito;
    }

    //Función que elimina un usario de un grupo
    public boolean eliminarUsuario(Grupo gr, usuario user, ArrayList<usuario> usuarios){
        boolean exito = false;
        //Primero comprobamos que el usuario que hace la función es el administrador del grupo
        if (this.userID==gr.getIdAdmin()){
            //Comprobamos que el usuario existe en la base de datos
            if (!usuarios.contains(user)){
                throw new InputMismatchException("El usuario no existe");
            }else {
                //Si exite, comprobamos si ya forma parte del grupo
                if (gr.getComponentes().contains(user)) {
                    //Eliminamos eel usuario del grupo
                    gr.getComponentes().remove(user);
                    //Eliminamos el grupo de la lista de grupos del usuario
                    user.getGruposPertenece().remove(gr);
                    exito = true;

                } else {
                    throw new RuntimeException("El usuario no forma parte del grupo");
                }
            }
        }else{
            throw new ArithmeticException("Solo el administrador del grupo puede añadir o eliminar miembros");
        }
        return exito;
    }

    //función que lee una cadena
    public static String leerCadena(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    //Función que lee un double
    public static double leerNum(){
        Scanner sc = new Scanner(System.in);
        double num = 0;
        boolean error;
        do{
            error= false;
            try{
                num= sc.nextDouble();
                error=false;
            }catch (NumberFormatException e){
                error = true;
                System.out.println("Introduzca un número");
            }finally {
                sc.nextLine();
            }
        }while (error);
        return num;
    }

    //Función que comprueba si una id de un grupo pertenece a un grupo en el que está dentro el usuario
    public boolean usuarioPerteneceGrupo (String nombreGrupo){
        boolean pertenece = false;
        for (Grupo gr : gruposPertenece){
            if (gr.getNombre().equals(nombreGrupo)) pertenece = true;
        }
        return pertenece;
    }

}
