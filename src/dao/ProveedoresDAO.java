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
    
    
     public DefaultComboBoxModel loadCombo( ) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call select_all_proveedor()");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Selecciona un proveedor");
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
                ob[1] = rs.getString("nombre");
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
            st.setString(1, p.getNombre().toUpperCase());
            st.setString(2, p.getTelefono().toUpperCase());
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
