/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class Ventana extends JFrame{
    Panel panel;

    public Ventana(Empleados empleados) {
        this.setTitle("Listado empleados");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 100, 500, 500);
        this.setLayout(new FlowLayout());
        panel = new Panel(empleados);
        this.getContentPane().add(panel);
//        this.addWindowListener(new FuncionalidadVentana());
        this.setVisible(true);
    }

}
