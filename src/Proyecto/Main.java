package Proyecto;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<usuario> usuarios = new ArrayList<>();
        usuario user1 = new usuario("Ruben","contraseña");
        System.out.println(user1);
        usuarios.add(new usuario("Ruben","contraseña")) ;

        usuarios.add(new usuario("Juan","contraseña"));
        usuarios.add(user1);
        System.out.println(usuarios);
      //  System.out.println(usuario2);
        user1.crearGrupo(user1);
        user1.crearGrupo(user1);
        user1.crearGrupo(user1);
        user1.verGrupos();

       // usuario usuario2 = new usuario("Juan","contraseña");
      //  usuario.crearGrupo(usuario2);
        //usuario.verGrupos();

       // usuario.eliminarGrupo(usuario1);
        //usuario.verGrupos();
    }
}