package Proyecto;

public class Main {
    public static void main(String[] args) {
        usuario usuario1 = new usuario("Ruben","contraseña");
        usuario.crearGrupo(usuario1);
        usuario.verGrupos();

        usuario usuario2 = new usuario("Juan","contraseña");
        usuario.crearGrupo(usuario2);
        usuario.verGrupos();

        usuario.eliminarGrupo(usuario1);
        usuario.verGrupos();
    }
}