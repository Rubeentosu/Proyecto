package Proyecto;

import java.util.ArrayList;

public class Grupo {
    private static int idGrupo = 1;
    private String nombre;
    private int idUsuario;



    public Grupo(String nombre, int idUsuario) {
        idGrupo++;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    // Getters y setters
    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "idGrupo=" + idGrupo +
                ", nombre='" + nombre + '\'' +
                ", administrador=" + idUsuario +
                '}';
    }

    public void añadirGastos(String descripcion, double cantidad, usuario pagador) {

    }
}