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
public class Cotizaciones {
  private int idCotizacion;
  private String nombreCliente;
  private String telefono;
  private double descuento;
  private double total;
  private double subtotal;

    public Cotizaciones() {
    }

    public Cotizaciones(int idCotizacion, String nombreCliente, String telefono, double descuento, double total, double subtotal) {
        this.idCotizacion = idCotizacion;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.descuento = descuento;
        this.total = total;
        this.subtotal = subtotal;
    }
    public Cotizaciones(String nombreCliente, String telefono, double descuento, double total, double subtotal) {
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.descuento = descuento;
        this.total = total;
        this.subtotal = subtotal;
    }
    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
  
    
}
