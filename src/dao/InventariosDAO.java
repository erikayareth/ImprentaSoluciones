/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojo.Inventarios;
import pojo.Productos;

/**
 *
 * @author erika
 */
public class InventariosDAO {
    
    
    public Inventarios seleccionar_inventario(int id) {
        Connection con = null;
        PreparedStatement st = null;
          Inventarios inventarios = new Inventarios();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL seleccionar_inventario(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                inventarios = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar inventario " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return inventarios;
    }
    
     private static Inventarios inflaPOJO(ResultSet rs){
         Inventarios pojo = new Inventarios();
        try {
            pojo.setIdInventario(rs.getInt("idInventario"));
            pojo.setStock(rs.getInt("stock"));
            pojo.setMinimo(rs.getInt("minimo"));
           
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo inventario .." + ex);
        }
        return pojo;
    }
      
    
}
