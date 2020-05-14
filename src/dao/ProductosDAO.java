/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mxrck.autocompleter.TextAutoCompleter;
import interfaces.XH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import pojo.Productos;
import pojo.Proveedores;

/**
 *
 * @author Meraki .-. ._.
 */
public class ProductosDAO {

    public boolean modificarStock(int Stock, int idp) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update productos set stock=? where idProductos=?");

            st.setInt(2, idp);
            st.setInt(1, Stock);
            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Stock" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }

    public Productos listarID(int id) {
        Productos p = new Productos();
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
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insertarProducto(?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, producto.getNombre());
            st.setString(2, producto.getDescripcion());
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
        } catch (Exception e) {
            System.out.println("Eror al insertar producto" + e);
        } finally {
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
            st = con.prepareStatement("CALL select_all_productos()");
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

    public Productos seleccionar_productos() {
        Connection con = null;
        PreparedStatement st = null;
        Productos pojo = new Productos();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_productos()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[9];
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar productos" + e);
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
     
    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Nombre", "Descripción", "Tipo de venta", "Precio", "Precio mayoreo", "Cantidad de mayoreo", "Stock", "Mínimo"};
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
                ob[1] = rs.getString("nombre").toUpperCase();
                ob[2] = rs.getString("descripcion").toUpperCase();
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
    
    public void cargarModeloAutocompleter(TextAutoCompleter nombre) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_productos()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Productos pojo = inflaPOJO(rs);
                nombre.addItem(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla productos " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
    }
    
    public DefaultTableModel cargarModeloInactivos() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Nombre", "Descripción", "Tipo de venta", "Precio", "Precio mayoreo", "Cantidad de mayoreo", "Stock", "Mínimo"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_productos2()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[9];
                Productos pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idProductos");
                ob[1] = rs.getString("nombre").toUpperCase();
                ob[2] = rs.getString("descripcion").toUpperCase();
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

    public DefaultTableModel cargarModelo2() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Nombre", "Descripción", "Precio", "Tipo de venta", "Cantidad"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_productos()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[6];
                Productos pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idProductos");
                ob[1] = rs.getString("nombre").toUpperCase();
                ob[2] = rs.getString("descripcion").toUpperCase();
                ob[3] = rs.getDouble("precio");
                ob[4] = rs.getString("tipoDeVenta");
                ob[5] = rs.getDouble("stock");
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

    public DefaultTableModel cargarModeloProductosBajos() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Nombre", "Descripcion", "Precio", "Stock", "Mínimo"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_productos3()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[6];
                Productos pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idProductos");
                ob[1] = rs.getString("nombre").toUpperCase();
                ob[2] = rs.getString("descripcion").toUpperCase();
                ob[3] = rs.getDouble("precio");
                ob[4] = rs.getInt("stock");
                ob[5] = rs.getInt("minimo");
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
    
    public boolean modificar(Productos pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Productos productos = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL modificarProducto(?,?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, productos.getIdProducto());
            st.setString(2, productos.getNombre());
            st.setString(3, productos.getDescripcion());
            st.setString(4, productos.getTipoDeVenta());
            st.setDouble(5, productos.getPrecio());
            st.setDouble(6, productos.getPrecioMayoreo());
            st.setDouble(7, productos.getCantidadMayoreo());
            st.setBoolean(8, productos.isEstado());
            st.setInt(9, productos.getProveedor_idProveedor());
            st.setInt(10, productos.getStock());
            st.setInt(11, productos.getMinimo());
            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar producto" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }

    private static Productos inflaPOJO(ResultSet rs) {
        Productos pojo = new Productos();
        try {
            pojo.setIdProducto(rs.getInt("idProductos"));
            pojo.setNombre(rs.getString("nombre"));
            pojo.setDescripcion(rs.getString("descripcion"));
            pojo.setTipoDeVenta(rs.getString("tipoDeVenta"));
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
