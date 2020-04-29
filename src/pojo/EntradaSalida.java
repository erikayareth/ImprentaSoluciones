/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Meraki
 */
public class EntradaSalida {
    private int idSalida; 
    private double cantidad;
    private boolean entrada;
    private String comentario;

    public EntradaSalida() {
    }

    public EntradaSalida(int idSalida, double cantidad, boolean entrada, String comentario) {
        this.idSalida = idSalida;
        this.cantidad = cantidad;
        this.entrada = entrada;
        this.comentario = comentario;
    }

    public EntradaSalida(double cantidad, boolean entrada, String comentario) {
        this.cantidad = cantidad;
        this.entrada = entrada;
        this.comentario = comentario;
    }

    public EntradaSalida(double cantidad, String comentario) {
        this.cantidad = cantidad;
        this.comentario = comentario;
    }
    

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }
    
    
    
}
