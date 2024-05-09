package Proyecto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gasto {
    private static int numGastos=0;
    private int idGasto;
    private String description;
    private int  pagador;
    private double cantidad;
    private LocalDateTime date;

    public Gasto(String description, double cantidad, int pagador) {
        numGastos++;
        this.idGasto=numGastos;
        this.description = description;
        this.cantidad = cantidad;
        this.pagador = pagador;
        date= LocalDateTime.now();
    }

    // Getters and setters
    public int getId() {
        return idGasto;
    }

    public void setId(int id) {
        this.idGasto = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPagador() {
        return pagador;
    }

    public void setPagador(int pagador) {
        this.pagador = pagador;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getPayer() {
        return pagador;
    }

    public void setPayer(int pagador) {
        this.pagador = pagador;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "id=" + idGasto +
                ", description='" + description + '\'' +
                ", pagador=" + pagador +
                ", amount=" + cantidad +
                ", date=" + date +
                '}';
    }
}