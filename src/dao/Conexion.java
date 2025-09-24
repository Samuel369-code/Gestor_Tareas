/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author samuk
 */
public class Conexion {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/gestor_tareas";
    private static final String USER = "ioc";   // tu usuario de pgAdmin
    private static final String PASS = "ioc"; // tu contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
}
