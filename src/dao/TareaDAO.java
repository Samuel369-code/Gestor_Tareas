/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Tarea;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samuk
 */
public class TareaDAO {
    
      public static void insertar(Tarea tarea) throws SQLException {
    String sql = "INSERT INTO tareas (titulo, descripcion, estado, fecha) VALUES (?, ?, ?, ?)";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tarea.getTitulo());
        stmt.setString(2, tarea.getDescripcion());
        stmt.setString(3, tarea.getEstado());
        stmt.setDate(4, java.sql.Date.valueOf(tarea.getFecha())); // LocalDate -> java.sql.Date
        stmt.executeUpdate();
    }
}


    public static List<Tarea> listar() throws SQLException {
    List<Tarea> lista = new ArrayList<>();
    String sql = "SELECT * FROM tareas";
    try (Connection conn = Conexion.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            java.sql.Date sqlDate = rs.getDate("fecha");
            LocalDate fecha = (sqlDate != null) ? sqlDate.toLocalDate() : LocalDate.now();

            lista.add(new Tarea(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("estado"),
                    fecha
            ));
        }
    }
    return lista;
}


    public static void actualizar(Tarea tarea) throws SQLException {
    String sql = "UPDATE tareas SET titulo=?, descripcion=?, estado=?, fecha=? WHERE id=?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tarea.getTitulo());
        stmt.setString(2, tarea.getDescripcion());
        stmt.setString(3, tarea.getEstado());
        stmt.setDate(4, java.sql.Date.valueOf(tarea.getFecha())); // LocalDate -> java.sql.Date
        stmt.setInt(5, tarea.getId());
        stmt.executeUpdate();
    }
}

    public static void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM tareas WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
}
