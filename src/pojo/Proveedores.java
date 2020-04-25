/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author erika
 */
public class Proveedores {
    private int idProveedor;
    private String nombre;
    private String telefono;
    private boolean estado;

    public Proveedores() {
    }

    public Proveedores(int idProveedor, String nombre, String telefono, boolean estado) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Proveedores(String nombre, String telefono, boolean estado) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = estado;
    }

    
    
    
  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    @Override
    public String toString() {
        return getNombre();
        
    }
    
}
