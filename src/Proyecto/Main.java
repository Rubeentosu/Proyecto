package Proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<usuario> usuarios = new ArrayList<>();
        usuarios.add(new usuario("Javi", "Javier1997"));
        usuarios.add(new usuario("Yasir", "Yasir2005"));
        usuarios.add(new usuario("David", "David1997"));
        usuarios.add(new usuario("Mikel", "Javier1997"));
        System.out.println("hola");

        usuarios.get(0).crearGrupo(usuarios.get(0));
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(1), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(2), usuarios);
        usuarios.get(0).addUser(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(3), usuarios);
        System.out.println("El grupo 1 está compuesto por"+usuarios.get(0).getGruposPertenece().get(0).getComponentes());
        System.out.println("Yasir pertenece al grupo" +usuarios.get(1).getGruposPertenece());
        //usuarios.get(0).eliminarUsuario(usuarios.get(0).getGruposPertenece().get(0),usuarios.get(1), usuarios);
        System.out.println("El grupo 1 está compuesto por"+usuarios.get(0).getGruposPertenece().get(0).getComponentes());

        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto1",25, usuarios.get(0));
        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto3",40, usuarios.get(1));
        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto6",7, usuarios.get(2));
        usuarios.get(0).añadirGastos(usuarios.get(0).getGruposPertenece().get(0),"gasto4",2, usuarios.get(2));
        System.out.println(usuarios.get(0).getGruposPertenece().get(0).getGastos());

        Map<usuario, Double> saldos  = usuarios.get(0).getGruposPertenece().get(0).verSaldo();

        System.out.println("El gasto medio es "+ usuarios.get(0).getGruposPertenece().get(0).gastoMedio());

        System.out.println(saldos);
        System.out.println(usuarios.get(0).getGruposPertenece().get(0).ajusteDeCuentas());


    }
}