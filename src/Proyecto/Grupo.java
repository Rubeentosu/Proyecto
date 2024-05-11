package Proyecto;

import java.time.LocalDate;
import java.util.*;

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

    //Función que nos imprime el dinero que le tiene que dar quien a quien para igualar las cuentas
    public String ajusteDeCuentas(){
        String cadena="";
        double menorDeuda=Double.MAX_VALUE;
        double menorEnElQueCabe=(-1)*Double.MAX_VALUE;
        Map<usuario, Double> saldos= verSaldo(); //Creamos el mapa con los saldos de cada usuario
        //Mientras alguien con deudas
        while(saldos.size()>1){
            //Eliminamos del mapa todos aquellos que tengan sus deudas ya saldadas
            eliminarEntrada(saldos);
            //Recorremos los valores y buscamos el que tenga la deuda (positiva) más pequeña
            for (Double n : saldos.values()){
                if(n>0 && n<menorDeuda) menorDeuda=n;
            }
            //una vez tengamos el valor, buscamos la persona que deba recibir dinero, con la cantidad de dinero
            //más pequeña que nos permita meter la cantidad anterior
            for (Double n : saldos.values()){
                if(n<0 && n>menorEnElQueCabe && (n+menorDeuda)<=0) menorEnElQueCabe=n;
            }
            //Una vez tenemos la cantidad a abonar y a la cantidad a la que se le suma, debemos sacar los usuarios que tienen los valores
            double d1=menorDeuda;
            double d2 = menorEnElQueCabe;

            usuario pagador = saldos.entrySet().stream()
                    .filter(entrada -> entrada.getValue().equals(d1))
                    .findFirst().get().getKey();

            usuario pagado = saldos.entrySet().stream()
                    .filter(entrada -> entrada.getValue().equals(d2))
                    .findFirst().get().getKey();

            //Metemos en la cadena los valores
            cadena = cadena + pagador.getName()+" paga " + menorDeuda;

            //Ahora, sobreescribimos los valores en el mapa metiendo la misma key y el valor resultante del traspaso de dinero
            //El que paga pasa a tener un valor de 0 porque ha pagado toda su deuda a la misma persona
            saldos.put(pagador, 0.0);
            //Al que recibe el dinero se le hace la cuenta
            saldos.put(pagado, menorEnElQueCabe+menorDeuda);

        }
        return cadena;
    }

    //Función que elimina del mapa de deudas todos aquellas claves que tengan un saldo/deuda de 0
    public void eliminarEntrada(Map<usuario, Double> mapa){
        Iterator<Map.Entry<usuario, Double>> it = mapa.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<usuario,Double> entrada = it.next();
            if(entrada.getValue().equals(0.0)){
                it.remove();
            }
        }
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