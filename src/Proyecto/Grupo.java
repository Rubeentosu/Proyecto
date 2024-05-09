package Proyecto;

import java.util.ArrayList;

public class Grupo {
    private int idGrupo;
    private String nombre;
    private usuario administrador;

    public Grupo(int idGrupo, String nombre, usuario administrador) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
        this.administrador = administrador;
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

    public usuario getAdministrador() {
        return administrador;
    }

    public void setAdministrador(usuario administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "idGrupo=" + idGrupo +
                ", nombre='" + nombre + '\'' +
                ", administrador=" + administrador +
                '}';
    }

    public void añadirGastos(String descripcion, double cantidad, usuario pagador) {

    }
}
