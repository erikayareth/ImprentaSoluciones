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
import java.sql.Timestamp;
import javax.swing.table.DefaultTableModel;
import pojo.Cotizaciones;
import pojo.Productos;
import pojo.Ventas;

/**
 *
 * @author erika
 */
public class VentasDAO {

    public int insertar(Ventas v) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insertarventa(?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, v.getImporte());
            st.setDouble(2, v.getTotal());
            st.setDouble(3, v.getDescuento());
            st.setDouble(4, v.getCambio());
            st.setString(5, v.getFolio());
            st.setDouble(6, v.getSubtotal());
            st.setString(7, v.getServicios());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Eror al insertar Venta ->" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    
      public DefaultTableModel cargarModelo()  {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Folio", "Importe", "Subtotal", "Total","Descuento", "Cambio", "Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_ventas()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[8];
                Ventas pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idVenta");
                ob[1] = rs.getString("folio");
                ob[2] = rs.getString("importe");
                ob[3] = rs.getString("subtotal");
                ob[5] = rs.getDouble("descuento");
                ob[4] = rs.getDouble("total");
                ob[6] = rs.getDouble("cambio");
                ob[7] = rs.getTimestamp("fecha");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla ventas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
       public DefaultTableModel cargarModelo2()  {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Folio", "Importe", "Subtotal", "Total","Descuento", "Cambio", "Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL cargar_ventas()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[8];
                Ventas pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idVenta");
                ob[1] = rs.getString("folio");
                ob[2] = rs.getString("importe");
                ob[3] = rs.getString("subtotal");
                ob[5] = rs.getDouble("descuento");
                ob[4] = rs.getDouble("total");
                ob[6] = rs.getDouble("cambio");
                ob[7] = rs.getTimestamp("fecha");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla ventas dia " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
      
    public Ventas seleccionar_venta(int i) {
        Connection con = null;
        PreparedStatement st = null;
        Ventas ventas = new Ventas();
        Productos productos = new Productos();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL seleccionar_venta(?)");
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ventas  = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar venta " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return ventas;
    }  
     
      
    private static Ventas inflaPOJO(ResultSet rs){
        Ventas pojo = new Ventas();
        try {
            pojo.setIdVenta(rs.getInt("idVenta"));
            pojo.setImporte(rs.getDouble("importe"));
            pojo.setTotal(rs.getDouble("total"));
            pojo.setDescuento(rs.getDouble("descuento"));
            pojo.setCambio(rs.getDouble("cambio"));
            pojo.setFolio(rs.getString("folio"));
            pojo.setSubtotal(rs.getDouble("subtotal"));
            pojo.setServicios(rs.getString("servicios"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Ventas .." + ex);
        }
        return pojo;
    }
}
