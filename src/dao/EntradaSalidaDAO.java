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
import javax.swing.table.DefaultTableModel;
import pojo.EntradaSalida;


/**
 *
 * @author Meraki
 */
public class EntradaSalidaDAO {
    
    public int insertar(EntradaSalida v) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insertarES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, v.getCantidad());
            st.setString(2, v.getComentario());
            st.setBoolean(3, v.isEntrada());
           

            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Eror al insertar Entrada o salida ->" + e);
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
        String encabezados[] = {"Cantidad", "Descripción", "Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_entradas()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                EntradaSalida pojo = inflaPOJO(rs);
                ob[0] = rs.getDouble("cantidad");
                ob[1] = rs.getString("comentario").toUpperCase();
                ob[2] = rs.getTimestamp("fecha");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla entradas " + e);
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
        String encabezados[] = {"Cantidad", "Descripción", "Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_salidas()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                EntradaSalida pojo = inflaPOJO(rs);
                ob[0] = rs.getDouble("cantidad");
                ob[1] = rs.getString("comentario").toUpperCase();
                ob[2] = rs.getTimestamp("fecha");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla salidas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
      public DefaultTableModel cargarModeloE()  {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Cantidad", "Descripción", "Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL cargar_entradas()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                EntradaSalida pojo = inflaPOJO(rs);
                ob[0] = rs.getDouble("cantidad");
                ob[1] = rs.getString("comentario").toUpperCase();
                ob[2] = rs.getTimestamp("fecha");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla entradas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
      
      public DefaultTableModel cargarModeloS()  {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Cantidad", "Descripción", "Fecha"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL cargar_salidas()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                EntradaSalida pojo = inflaPOJO(rs);
                ob[0] = rs.getDouble("cantidad");
                ob[1] = rs.getString("comentario").toUpperCase();
                ob[2] = rs.getTimestamp("fecha");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla entradas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
       public EntradaSalida seleccionar_es(int id) {
        Connection con = null;
        PreparedStatement st = null;
           EntradaSalida entradaSalida = new EntradaSalida();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL seleccionar_es(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                entradaSalida = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar entrada salida" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return entradaSalida;
    }
    private static EntradaSalida inflaPOJO(ResultSet rs){
        EntradaSalida pojo = new EntradaSalida();
        try {
            pojo.setIdSalida(rs.getInt("idSalida"));
            pojo.setCantidad(rs.getDouble("cantidad"));
            pojo.setEntrada(rs.getBoolean("entrada"));
            pojo.setComentario(rs.getString("comentario"));
           
           
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo EntradaSalida .." + ex);
        }
        return pojo;
    }
}
