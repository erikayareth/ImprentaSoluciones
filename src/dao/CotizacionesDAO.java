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
import pojo.Cotizaciones;
import pojo.Productos;
import pojo.Ventas;

/**
 *
 * @author erika
 */
public class CotizacionesDAO {

    public int insertar(Cotizaciones v) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insertarcotizacion(?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, v.getNombreCliente());
            st.setString(2, v.getTelefono());
            st.setDouble(3, v.getDescuento());
            st.setDouble(4, v.getTotal());
            st.setDouble(5, v.getSubtotal());
            st.setString(6, v.getServicios());
            st.setString(7, v.getFolio());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Eror al insertar cotización ->" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }

    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID","Folio", "Nombre del cliente", "Teléfono", "Descuento", "Subtotal", "Total"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_cotizacion()");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[7];
                Cotizaciones pojo = inflaPOJO(rs);
                ob[0] = rs.getInt("idCotizacion");
                ob[1] = rs.getString("folio");
                ob[2] = rs.getString("nombreCliente");
                ob[3] = rs.getString("telefono");
                ob[4] = rs.getDouble("descuento");
                ob[6] = rs.getDouble("total");
                ob[5] = rs.getDouble("subtotal");

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla cotización " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
public Cotizaciones seleccionar_coti(int i) {
        Connection con = null;
        PreparedStatement st = null;
       
        Cotizaciones cotizaciones = new Cotizaciones();
        Productos productos = new Productos();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL seleccionar_coti(?)");
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cotizaciones  = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar la cotización " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return cotizaciones;
    }  
    private static Cotizaciones inflaPOJO(ResultSet rs) {
        Cotizaciones pojo = new Cotizaciones();
        try {
            pojo.setIdCotizacion(rs.getInt("idCotizacion"));
            pojo.setNombreCliente(rs.getString("nombreCliente"));
            pojo.setTelefono(rs.getString("telefono"));
            pojo.setDescuento(rs.getDouble("descuento"));
            pojo.setTotal(rs.getDouble("total"));
            pojo.setSubtotal(rs.getDouble("subtotal"));
            pojo.setServicios(rs.getString("servicios"));
            pojo.setFolio(rs.getString("folio"));

        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo  cotizaciones .." + ex);
        }
        return pojo;
    }
}
