/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 *
 * @author samuk
 */
public class EstadoCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String estado = (String) table.getValueAt(row, 3); // columna del estado
        if ("Pendiente".equals(estado)) {
            c.setBackground(Color.YELLOW);
        } else if ("En Progreso".equals(estado)) {
            c.setBackground(Color.CYAN);
        } else if ("Completada".equals(estado)) {
            c.setBackground(Color.GREEN);
        } else {
            c.setBackground(Color.WHITE);
        }

        if (isSelected) {
            c.setBackground(c.getBackground().darker());
        }

        return c;
    }
}
