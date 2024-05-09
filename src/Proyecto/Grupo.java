package Proyecto;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public String toString() {
        return "Grupo{" +
                "idGrupo=" + idGrupo +
                ", nombre='" + nombre + '\'' +
                ", administrador=" + idAdmin +
                '}';
    }
/*
    public void añadirGastos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor, introduzca una descripción del gasto");
        String descripcion = leerCadena();
        System.out.println("Por favor, introduzca cuanto ha costado");
        double cantidad  = leerNum();


        gastos.add(new Gasto(descripcion, cantidad, pagador));
    }
*/
    //public void eliminarGastos()

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
}