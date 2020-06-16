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
            st = con.prepareStatement("call insertarventa(?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, v.getImporte());
            st.setDouble(2, v.getTotal());
            st.setDouble(3, v.getDescuento());
            st.setDouble(4, v.getCambio());
            st.setString(5, v.getFolio());
            st.setDouble(6, v.getSubtotal());
            st.setString(7, v.getServicios());
            st.setString(8, v.getCLA());
            st.setBoolean(9, v.isTarjeta());
            st.setInt(10, v.getMerma());
            st.setBoolean(11, v.isEstado());
           
            
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
        String encabezados[] = {"ID", "Folio", "C/L/A","Importe", "Subtotal","Descuento", "Total", "Cambio","Merma", "Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_ventas2()");
           
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[10];
                Ventas pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idVenta");
                ob[1] = rs.getString("folio");
                ob[3] = rs.getString("importe");
                ob[4] = rs.getString("subtotal");
                ob[5] = rs.getDouble("descuento");
                ob[6] = rs.getDouble("total");
                ob[7] = rs.getDouble("cambio");
                ob[8] = rs.getInt("merma");
                ob[9] = rs.getTimestamp("fecha");
                ob[2] = rs.getString("CLA");
                
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
        String encabezados[] = {"ID", "Folio","C/L/A", "Importe", "Subtotal","Descuento", "Total", "Cambio", "Merma","Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL cargar_ventas()");
          
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[10];
                Ventas pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idVenta");
                ob[1] = rs.getString("folio");
                ob[3] = rs.getString("importe");
                ob[4] = rs.getString("subtotal");
                ob[5] = rs.getDouble("descuento");
                ob[6] = rs.getDouble("total");
                ob[7] = rs.getDouble("cambio");
                ob[8] = rs.getInt("merma");
                ob[9] = rs.getTimestamp("fecha");
                ob[2] = rs.getString("CLA");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla ventas dia con tarjeta " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
      public DefaultTableModel cargarModelo3()  {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Folio","C/L/A", "Importe", "Subtotal", "Descuento","Total", "Cambio", "Merma","Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL cargar_ventasS()");
           
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[10];
                Ventas pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idVenta");
                ob[1] = rs.getString("folio");
                ob[3] = rs.getString("importe");
                ob[4] = rs.getString("subtotal");
                ob[5] = rs.getDouble("descuento");
                ob[6] = rs.getDouble("total");
                ob[7] = rs.getDouble("cambio");
                ob[8] = rs.getInt("merma");
                ob[9] = rs.getTimestamp("fecha");
                ob[2] = rs.getString("CLA");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla ventas dia sin tarjeta" + e);
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
      public boolean eliminar(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL bajaVenta(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al cancelar una venta: " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
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
            pojo.setCLA(rs.getString("CLA"));
            pojo.setTarjeta(rs.getBoolean("tarjeta"));
            pojo.setMerma(rs.getInt("merma"));
            pojo.setEstado(rs.getBoolean("estado"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Ventas .." + ex);
        }
        return pojo;
    }
}
