package Proyecto;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<usuario> usuarios = new ArrayList<>();
        usuarios.add(new usuario("Javi", "Javier1997"));
        usuarios.add(new usuario("Yasir", "Yasir2005"));
        usuarios.add(new usuario("David", "David1997"));
        System.out.println("hola");

        usuarios.get(0).crearGrupo(usuarios.get(0));
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(1), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(2), usuarios);
        System.out.println("El grupo 1 está compuesto por"+usuarios.get(0).getGruposPertenece().get(0).getComponentes());
        System.out.println("Yasir pertenece al grupo" +usuarios.get(1).getGruposPertenece());
        usuarios.get(0).eliminarUsuario(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(1), usuarios);
        System.out.println("El grupo 1 está compuesto por"+usuarios.get(0).getGruposPertenece().get(0).getComponentes());







    }
}