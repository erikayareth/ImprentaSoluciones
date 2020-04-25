/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Meraki 
 * 
 *
 */
public class Productos {
   private int idProducto;
   private String nombre;
   private String descripcion;
   private String tipoDeVenta;
   private double precio;
   private double precioMayoreo;
   private int cantidadMayoreo;
   private int Proveedor_idProveedor;

    public Productos() {
    }

    public Productos(int idProducto, String nombre, String descripcion, String tipoDeVenta, double precio, double precioMayoreo, int cantidadMayoreo, int Proveedor_idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoDeVenta = tipoDeVenta;
        this.precio = precio;
        this.precioMayoreo = precioMayoreo;
        this.cantidadMayoreo = cantidadMayoreo;
        this.Proveedor_idProveedor = Proveedor_idProveedor;
    }

    public Productos(String nombre, String descripcion, String tipoDeVenta, double precio, double precioMayoreo, int cantidadMayoreo, int Proveedor_idProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoDeVenta = tipoDeVenta;
        this.precio = precio;
        this.precioMayoreo = precioMayoreo;
        this.cantidadMayoreo = cantidadMayoreo;
        this.Proveedor_idProveedor = Proveedor_idProveedor;
    }

    public Productos(String string, int i) {
       
    }

  

   
   
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoDeVenta() {
        return tipoDeVenta;
    }

    public void setTipoDeVenta(String tipoDeVenta) {
        this.tipoDeVenta = tipoDeVenta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioMayoreo() {
        return precioMayoreo;
    }

    public void setPrecioMayoreo(double precioMayoreo) {
        this.precioMayoreo = precioMayoreo;
    }

    public int getCantidadMayoreo() {
        return cantidadMayoreo;
    }

    public void setCantidadMayoreo(int cantidadMayoreo) {
        this.cantidadMayoreo = cantidadMayoreo;
    }

    public int getProveedor_idProveedor() {
        return Proveedor_idProveedor;
    }

    public void setProveedor_idProveedor(int Proveedor_idProveedor) {
        this.Proveedor_idProveedor = Proveedor_idProveedor;
    }
   
   
    
}
