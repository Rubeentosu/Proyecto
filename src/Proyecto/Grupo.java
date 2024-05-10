package Proyecto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Grupo {
    private static int contadorGrupos =0;
    private int idGrupo;
    private String nombre;
    private int idAdmin;
    //Lista en el que se guardarán todos los gastos del grupo
    private ArrayList<Gasto> gastos;
    private ArrayList<usuario> componentes;



    public Grupo(String nombre, int idUsuario, usuario admin) {
        contadorGrupos++;
        this.idGrupo=contadorGrupos;
        this.nombre = nombre;
        this.idAdmin = idUsuario;
        gastos = new ArrayList<>();
        componentes = new ArrayList<>();
        componentes.add(admin);
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

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idUsuario) {
        this.idAdmin = idUsuario;
    }

    public static int getContadorGrupos() {
        return contadorGrupos;
    }

    public static void setContadorGrupos(int contadorGrupos) {
        Grupo.contadorGrupos = contadorGrupos;
    }

    public ArrayList<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(ArrayList<Gasto> gastos) {
        this.gastos = gastos;
    }

    public ArrayList<usuario> getComponentes() {
        return componentes;
    }

    public void setComponentes(ArrayList<usuario> componentes) {
        this.componentes = componentes;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "idGrupo=" + idGrupo +
                ", nombre='" + nombre + '\'' +
                ", administrador=" + idAdmin +
                '}';
    }

    //Función que devuelve un mapa con el miembro del grupo y la cantidad que debe
    public Map<usuario, Double> verSaldo(){
        //Calculamos la media, lo que en teoría debe pagar cada uno
        double cadaUnoPaga = gastoMedio();
        //Creamos el mapa donde guardaremos los valores de forma ordenada
        Map<usuario, Double> mapa = new LinkedHashMap<>();
        //Recorremos el array de miembros del grupo y comparamos lo que han pagado ya con lo que deberían pagar cada miembro
        for (usuario u : componentes){
            mapa.put(u, gastoUsuario(u.getUserID())-cadaUnoPaga);
        }
        //Devolvemos el mapa
        return mapa;
    }


    //Función que calcula el gasto medio de cada integrante de un grupo con un stream
    public double gastoMedio(){
        //Con un stream calculamos el acumulado de todos los gastos y lo dividimos entre el número de integrantes
        return gastos.stream()
                .mapToDouble(Gasto::getCantidad)
                .sum()  /   componentes.size();
    }

    //Función que calcula el total de lo que ya ha pagado un usuario
    public double gastoUsuario (int idUsuario){
        return gastos.stream()
                .filter(g -> g.getPagador()==idUsuario) //Filtramos aquellos en los quee el id coincida
                .mapToDouble(Gasto::getCantidad)//Sacamos la cantidad
                .sum(); //Hacemos la sumatoria
    }
}