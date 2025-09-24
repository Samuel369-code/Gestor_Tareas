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
public class HeaderRenderer extends DefaultTableCellRenderer {

    private final Color backgroundColor;
    private final Color foregroundColor;

    public HeaderRenderer(Color backgroundColor, Color foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        setHorizontalAlignment(JLabel.CENTER); // centrar texto
        setFont(getFont().deriveFont(Font.BOLD)); // negrita
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(backgroundColor);
        c.setForeground(foregroundColor);
        c.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK)); // línea negra derecha y abajo

        return c;
    }
}
