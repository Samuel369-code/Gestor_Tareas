/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.TareaDAO;
import model.Tarea;
import ui.HeaderRenderer;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author samuk
 */
public class GestorTareasUI extends JFrame {
    
    private final JTable tabla;
    private final DefaultTableModel modelo;

    public GestorTareasUI() {
        setTitle("Gestor de Tareas");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/portapapeles.png"));
        setIconImage(icon.getImage());

        modelo = new DefaultTableModel(new String[]{"ID", "Título", "Descripción", "Estado", "Fecha"}, 0);
        tabla = new JTable(modelo);

        tabla.getColumnModel().getColumn(3).setCellRenderer(new EstadoCellRenderer());

        
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        
        // Colorear encabezado
        tabla.getTableHeader().setDefaultRenderer(new HeaderRenderer(new Color(70, 130, 180), Color.WHITE));
        
        // Color de fondo de la ventana
        getContentPane().setBackground(new Color(245, 245, 245)); // gris claro

        // Color de fondo del panel de botones
        panelBotones.setBackground(new Color(220, 220, 220)); // gris más oscuro

        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10)); // top, left, bottom, right

        
        // Eventos
        btnAgregar.addActionListener(e -> agregarTarea());
        btnEditar.addActionListener(e -> editarTarea());
        btnEliminar.addActionListener(e -> eliminarTarea());

        cargarTareas();
    }

    private void cargarTareas() {
        modelo.setRowCount(0);
        try {
            List<Tarea> lista = TareaDAO.listar();
            for (Tarea t : lista) {
                modelo.addRow(new Object[]{t.getId(), t.getTitulo(), t.getDescripcion(), t.getEstado(),t.getFecha()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tareas: " + e.getMessage());
        }
    }

    private void agregarTarea() {
    String titulo = JOptionPane.showInputDialog(this, "Título:");
    String descripcion = JOptionPane.showInputDialog(this, "Descripción:");
    String[] estados = {"Pendiente", "En Progreso", "Completada"};
    String estado = (String) JOptionPane.showInputDialog(this, "Estado:", "Seleccionar",
            JOptionPane.QUESTION_MESSAGE, null, estados, estados[0]);

    if (titulo != null && estado != null) {
        // --- AQUÍ VA EL BLOQUE DEL SPINNER DE FECHA ---
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(model);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        int option = JOptionPane.showConfirmDialog(this, dateSpinner, "Selecciona la fecha", JOptionPane.OK_CANCEL_OPTION);
        if (option != JOptionPane.OK_OPTION) return;
        java.util.Date selectedDate = model.getDate();
        LocalDate fecha = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // --- FIN BLOQUE DEL SPINNER DE FECHA ---

        try {
            TareaDAO.insertar(new Tarea(titulo, descripcion, estado, fecha));
            cargarTareas();
            JOptionPane.showMessageDialog(this, "Tarea agregada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar: " + e.getMessage());
        }
    }
}


    private void editarTarea() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para editar.");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        String titulo = JOptionPane.showInputDialog(this, "Título:", modelo.getValueAt(fila, 1));
        String descripcion = JOptionPane.showInputDialog(this, "Descripción:", modelo.getValueAt(fila, 2));
        String[] estados = {"Pendiente", "En Progreso", "Completada"};
        String estado = (String) JOptionPane.showInputDialog(this, "Estado:", "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, estados, modelo.getValueAt(fila, 3));

        if (titulo != null && estado != null) {
            SpinnerDateModel model = new SpinnerDateModel();
            JSpinner dateSpinner = new JSpinner(model);
            dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
            int option = JOptionPane.showConfirmDialog(this, dateSpinner, "Selecciona la fecha", JOptionPane.OK_CANCEL_OPTION);
            if (option != JOptionPane.OK_OPTION) return;
            java.util.Date selectedDate = model.getDate();
            LocalDate fecha = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            try {
                TareaDAO.actualizar(new Tarea(id, titulo, descripcion, estado, fecha));
                cargarTareas();
                JOptionPane.showMessageDialog(this, "Tarea actualizada correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
            }
        }
    }

    private void eliminarTarea() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para eliminar.");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar tarea?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                TareaDAO.eliminar(id);
                cargarTareas();
                JOptionPane.showMessageDialog(this, "Tarea eliminada correctamente ");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestorTareasUI().setVisible(true));
    }
    
}
