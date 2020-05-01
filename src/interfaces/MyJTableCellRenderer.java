/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Avril
 */
public class MyJTableCellRenderer extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable jtable,Object o, boolean bln , boolean bln1, int i, int i1){
        
        Component cell  = super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
        Color fondo = new Color(213, 234, 255);
        /*cell.setBackground(fondo);, */
        return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
    }
    
}
