/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 52229
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
}
