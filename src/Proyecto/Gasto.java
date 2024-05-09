package Proyecto;

import java.time.LocalDateTime;

public class Gasto {
    private int id;
    private String description;
    private usuario pagador;
    private double cantidad;
    private LocalDateTime date;

    public Gasto(int id, String description, double cantidad, usuario pagador, LocalDateTime date) {
        this.id = id;
        this.description = description;
        this.cantidad = cantidad;
        this.pagador = pagador;
        this.date = date;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public usuario getPagador() {
        return pagador;
    }

    public void setPagador(usuario pagador) {
        this.pagador = pagador;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public usuario getPayer() {
        return pagador;
    }

    public void setPayer(usuario pagador) {
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
                "id=" + id +
                ", description='" + description + '\'' +
                ", pagador=" + pagador +
                ", amount=" + cantidad +
                ", date=" + date +
                '}';
    }
}
