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

import javax.swing.table.DefaultTableModel;
import pojo.Productos;
import pojo.Proveedores;

/**
 *
 * @author Meraki .-. ._.
 */
public class ProductosDAO {
    
    
     
     
      public boolean modificarStock( int Stock,int idp){
        Connection con = null;
        PreparedStatement st = null;
        
        try{
            con = Conexion.getConnection();
            st = con.prepareStatement("update productos set stock=? where idProductos=?");
            
            st.setInt(2,idp);           
            st.setInt(1,Stock);
            int x = st.executeUpdate();
            if (x==0) {
                return false;
            }
        }catch(Exception e){
            System.out.println("Error al actualizar Stock" + e);
        }finally{
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
     
     
     public Productos listarID(int id){
        Productos p=new Productos();
         Connection con = null;
        PreparedStatement st = null;
       
        try {
            con = Conexion.getConnection();
           st = con.prepareStatement("select * from productos where IdProductos=?");
            st.setInt(1, id);
             ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setIdProducto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
               
            }
        } catch (Exception e) {
        }
        return p;
    }
//    
//     public boolean eliminar(int id) {
//        Connection con = null;
//        PreparedStatement st = null;
//        try {
//            con = Conexion.getConnection();
//            st = con.prepareStatement("CALL bajaNino(?)");
//            st.setInt(1, id);
//            int num = st.executeUpdate();
//            if (num == 0) {
//                return false;
//            }
//        } catch (Exception e) {
//            System.out.println("Error al dar de baja Niño: " + e);
//            return false;
//        } finally {
//            Conexion.close(con);
//            Conexion.close(st);
//        }
//        return true;
//    }
     public int insertar(Productos producto) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try{
            con = Conexion.getConnection(); 
            st = con.prepareStatement("call insertarProducto(?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, producto.getNombre().toUpperCase());
            st.setString(2, producto.getDescripcion().toUpperCase());
            st.setString(3, producto.getTipoDeVenta());
            st.setDouble(4, producto.getPrecio());
            st.setDouble(5, producto.getPrecioMayoreo());
            st.setInt(6, producto.getCantidadMayoreo());
            st.setBoolean(7, producto.isEstado());
            st.setInt(8, producto.getProveedor_idProveedor());
            st.setInt(9, producto.getStock());
            st.setInt(10, producto.getMinimo());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println("Eror al insertar producto" + e);
        }finally{
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
     
     
      public Productos consultar(int id) {
        Connection con = null;
        PreparedStatement st = null;
        Productos pojo = new Productos();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_producto()");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar productos" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
      
      public Productos seleccionar_producto(int id) {
        Connection con = null;
        PreparedStatement st = null;
          Productos productos = new Productos();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL seleccionar_producto(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                productos = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar producto" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return productos;
    }
   
     public DefaultTableModel cargarModelo()  {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Nombre", "Descripción", "TipoVenta", "Precio", "PrecioMayoreo", "CantidadMayoreo","Stock","Minimo"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_productos()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[9];
                Productos pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idProductos");
                ob[1] = rs.getString("nombre");
                ob[2] = rs.getString("descripcion");
                ob[3] = rs.getString("tipoDeVenta");
                ob[4] = rs.getDouble("precio");
                ob[5] = rs.getDouble("precioMayoreo");
                ob[6] = rs.getDouble("cantMayoreo");
                ob[7] = rs.getDouble("stock");
                ob[8] = rs.getDouble("minimo");
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla productos " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public DefaultTableModel cargarModelo2 ()  {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Nombre", "Descripción",  "Precio", "Cantidad"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_productos()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Productos pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idProductos");
                ob[1] = rs.getString("nombre");
                ob[2] = rs.getString("descripcion");
                ob[3] = rs.getDouble("precio");
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla productos2 " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     
     
//      public boolean modificar(Productos Nino){
//        Connection con = null;
//        PreparedStatement st = null;
//        
//        try{
//            con = Conexion.getConnection();
//            st = con.prepareStatement("CALL modificarProducto(?,?,?,?,?,?,?,?)");
//            st.setInt(1, Nino.getIdnino());
//            st.setString(2, Nino.getNombre().toUpperCase());
//            st.setString(3, Nino.getApellido().toUpperCase());
//            st.setString(4, Nino.getSexo());
//            st.setDate(5, Nino.getFecha_nac());
//            st.setString(6, Nino.getEnfermedad().toUpperCase());
//            st.setInt(7, Nino.getTutor_idtutor());
//            st.setInt(8, Nino.getMaestra_idmaestra());
//            int x = st.executeUpdate();
//            if (x==0) {
//                return false;
//            }
//        }catch(Exception e){
//            System.out.println("Error al actualizar Nino" + e);
//        }finally{
//            Conexion.close(con);
//            Conexion.close(st);
//        }
//        return true;
//    }
     
      private static Productos inflaPOJO(ResultSet rs){
        Productos pojo = new Productos();
        try {
            pojo.setIdProducto(rs.getInt("idProductos"));
            pojo.setNombre(rs.getString("nombre"));
            pojo.setDescripcion(rs.getString("descripcion"));
            pojo.setTipoDeVenta("tipoDeVenta");
            pojo.setPrecio(rs.getDouble("precio"));
            pojo.setPrecioMayoreo(rs.getDouble("precioMayoreo"));
            pojo.setCantidadMayoreo(rs.getInt("cantMayoreo"));
            pojo.setEstado(rs.getBoolean("estado"));
            pojo.setProveedor_idProveedor(rs.getInt("Proveedor_idProveedor"));
            pojo.setStock(rs.getInt("stock"));
            pojo.setMinimo(rs.getInt("minimo"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo productos .." + ex);
        }
        return pojo;
    }
      
    
    
}
