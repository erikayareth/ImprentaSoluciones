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
import pojo.Productos;
import pojo.Usuario;
import pojo.Ventas;

/**
 *
 * @author Meraki
 */
public class usuarioDAO {

    private static final String TABLE = "Usuario";
    private static final String SQL_LOGIN = "select * from " + TABLE + " " + "where usuario=? and contrasena=?";

    public static int login(String usuario, String contrasena) {

        Connection con = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            PreparedStatement st = con.prepareStatement(SQL_LOGIN);
            st.setString(1, usuario);
            st.setString(2, contrasena);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idUsuario");
            }
        } catch (Exception e) {
            System.out.println("Error en login " + e);
        }
        return id;
    }
    
    
    public Usuario seleccionar_usuario() {
        Connection con = null;
        PreparedStatement st = null;
        Usuario usuario = new Usuario();
        
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL seleccionar_usuario()");
           
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                usuario  = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar usuario " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return usuario;
    } 
     public Usuario seleccionar_usuario2() {
        Connection con = null;
        PreparedStatement st = null;
        Usuario usuario = new Usuario();
        
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL seleccionar_usuario2()");
           
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                usuario  = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar usuario " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return usuario;
    } 
     private static Usuario inflaPOJO(ResultSet rs){
         Usuario pojo = new Usuario();
        try {
            pojo.setIdUsuario(rs.getInt("idUsuario"));
            pojo.setUsuario(rs.getString("usuario"));
            pojo.setContrasena(rs.getString("contrasena"));
            
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Usuario .." + ex);
        }
        return pojo;
    }
}
