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
public class Inventarios {
    private int idInventario;
    private int stock;
    private int minimo;

    public Inventarios() {
    }

    public Inventarios(int idInventario, int stock, int minimo) {
        this.idInventario = idInventario;
        this.stock = stock;
        this.minimo = minimo;
    }

    public Inventarios(int stock, int minimo) {
        this.stock = stock;
        this.minimo = minimo;
    }
    
    

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
}
