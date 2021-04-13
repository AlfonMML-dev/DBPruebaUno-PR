/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class Panel extends JPanel{
 
    static private ResultSet rs;

    private final GridLayout grid = new GridLayout(1, 2, 10, 0);
    private JButton btActualizar, btBorrar, btDer, btInsertar, btIz;
    private JLabel lbID, lbNombre, lbSueldo;
    private JPanel panelDespla, panelID, panelNombre, panelSueldo;
    private JTextField tfID, tfNombre, tfSueldo;
    
    public Panel() {
        this.setLayout(new GridLayout(0, 1, 0, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        ponComponentes();        
    }
    
    public void ponComponentes(){
        lbID = new JLabel("idEmp:");
        tfID = new JTextField();   
        panelID = new JPanel(grid);
        panelID.add(lbID);
        panelID.add(tfID);
                
        lbNombre = new JLabel("Nombre:");
        tfNombre = new JTextField();
        panelNombre = new JPanel(grid);
        panelNombre.add(lbNombre);
        panelNombre.add(tfNombre);
        
        lbSueldo = new JLabel("Sueldo:");
        tfSueldo = new JTextField();
        panelSueldo = new JPanel(grid);
        panelSueldo.add(lbSueldo);
        panelSueldo.add(tfSueldo);
        
        btIz = new JButton("<");
        btDer = new JButton(">");
        btIz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desplazarIz();
            }
        });
        
        btDer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desplazarDer();
            }
        });        
        grid.setHgap(20);
        panelDespla = new JPanel(grid);
        panelDespla.add(btIz);
        panelDespla.add(btDer);
        
        btBorrar = new JButton("Borrar");
        btActualizar = new JButton("Actualizar");
        btInsertar = new JButton("Insertar");
        
        btBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarEmpleado();
            }
        });
        btActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEmpleado();
            }
        });
        btInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarEmpleado();
            }
        });
        
        this.add(panelID);
        this.add(panelNombre);
        this.add(panelSueldo);
        this.add(panelDespla);
        this.add(btBorrar);
        this.add(btActualizar);
        this.add(btInsertar);
        
    }
    
    public void desplazarIz(){
        
    }
    
    public void desplazarDer(){
        
    }
    
    public void borrarEmpleado(){
        
    }
    
    public void actualizarEmpleado(){
        
    }
    
    public void insertarEmpleado(){
        
    }
    
    
}
