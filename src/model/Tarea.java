/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

public class Tarea {
    
    private int id;
    private String titulo;
    private String descripcion;
    private String estado;
    private LocalDate fecha;

    // Constructor completo
    public Tarea(int id, String titulo, String descripcion, String estado, LocalDate fecha) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
    }

    // Constructor sin id (para insertar nuevas tareas)
    public Tarea(String titulo, String descripcion, String estado, LocalDate fecha) {
        this(0, titulo, descripcion, estado, fecha);
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
