/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Timestamp;

/**
 *
 * @author erika
 */
public class Ventas {
    
    private int idVenta;
    private double importe;
    private double total;
    private double descuento;
    private double cambio;
    private Timestamp fecha;
    private String folio;
    private double subtotal;
    private String servicios;
    private String CLA;
    private boolean tarjeta;
    
    public Ventas() {
    }

    public Ventas(int idVenta, double importe, double total, double descuento, double cambio, Timestamp fecha, String folio, double subtotal, String servicios, String CLA, boolean tarjeta) {
        this.idVenta = idVenta;
        this.importe = importe;
        this.total = total;
        this.descuento = descuento;
        this.cambio = cambio;
        this.fecha = fecha;
        this.folio = folio;
        this.subtotal = subtotal;
        this.servicios = servicios;
        this.CLA = CLA;
        this.tarjeta = tarjeta;
    }

    public Ventas(double importe, double total, double descuento, double cambio, String folio, double subtotal, String servicios, String cla, boolean tarjeta) {
        this.importe = importe;
        this.total = total;
        this.descuento = descuento;
        this.cambio = cambio;
        this.folio = folio;
        this.subtotal = subtotal;
        this.servicios = servicios;
        this.CLA = cla;
        this.tarjeta = tarjeta;
    }

   
 
    
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
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

    public String getCLA() {
        return CLA;
    }

    public void setCLA(String CLA) {
        this.CLA = CLA;
    }

    public boolean isTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(boolean tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    
    
    
    
}
