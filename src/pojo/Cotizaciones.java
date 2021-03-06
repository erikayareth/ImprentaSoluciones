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
  private String servicios;
  private String folio;
  private double iva;

    public Cotizaciones() {
    }

    public Cotizaciones(int idCotizacion, String nombreCliente, String telefono, double descuento, double total, double subtotal, String servicios, String folio, double iva) {
        this.idCotizacion = idCotizacion;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.descuento = descuento;
        this.total = total;
        this.subtotal = subtotal;
        this.servicios = servicios;
        this.folio = folio;
        this.iva = iva;
    }

  

    public Cotizaciones(String nombreCliente, String telefono, double descuento, double total, double subtotal, String servicios, String folio,double iva) {
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.descuento = descuento;
        this.total = total;
        this.subtotal = subtotal;
        this.servicios = servicios;
        this.folio = folio;
        this.iva = iva;
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

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }
  
    
}
