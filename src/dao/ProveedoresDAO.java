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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import pojo.Productos;
import pojo.Proveedores;

/**
 *
 * @author erika
 */
public class ProveedoresDAO {
    
    
     public DefaultComboBoxModel loadCombo() throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call select_all_proveedor()");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
             dt.addElement("Seleccione un Proveedor");
            while (rs.next()) {
                Proveedores pojo = inflaPOJO(rs);
                dt.addElement(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el combo Proveedor" + e);
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
        String encabezados[] = {"ID","Nombre","Teléfono"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL  select_all_proveedor()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Proveedores pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idProveedor");
                ob[1] = rs.getString("nombre").toUpperCase();
                ob[2] = rs.getString("telefono");          
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Proveedor " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
      
    public DefaultTableModel cargarModeloInactivos()  {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID","Nombre","Teléfono"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL  select_all_proveedor2()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Proveedores pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idProveedor");
                ob[1] = rs.getString("nombre").toUpperCase();
                ob[2] = rs.getString("telefono");          
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Proveedor " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
      
      public int insertar(Proveedores p) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try{
            con = Conexion.getConnection(); 
            st = con.prepareStatement("call insertarProveedor(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, p.getNombre());
            st.setString(2, p.getTelefono());
            st.setBoolean(3, p.isEstado());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println("Eror al insertar proveedor" + e);
        }finally{
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
      
    public Proveedores seleccionar_proveedor(int id) {
        Connection con = null;
        PreparedStatement st = null;
        Proveedores proveedores = new Proveedores();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL seleccionar_proveedor(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                proveedores = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar proveedor" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return proveedores;
    }
      
    public boolean modificar(Proveedores pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Proveedores proveedores = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL modificarProveedor(?,?,?,?)");
            st.setInt(1, proveedores.getIdProveedor());
            st.setString(2, proveedores.getNombre());
            st.setString(3, proveedores.getTelefono());
            st.setBoolean(4, proveedores.isEstado());
            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar proveedor" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
      
      private static Proveedores inflaPOJO(ResultSet rs){
        Proveedores pojo = new Proveedores();
        try {
            pojo.setIdProveedor(rs.getInt("idProveedor"));
            pojo.setNombre(rs.getString("nombre"));
            pojo.setTelefono(rs.getString("telefono"));
            pojo.setEstado(rs.getBoolean("estado"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo proveedor " + ex);
        }
        return pojo;
    }
      
      
}
