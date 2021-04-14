/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Empleados {

    private ArrayList<Empleado> empleados;

    public Empleados() {
        empleados = new ArrayList<>();
    }

    public void insertaEmpleado(Empleado emp) {
        if (!empleados.contains(emp)) {
            empleados.add(emp);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Este empleado ya se encuentra en la base de datos",
                    "Mensaje Base de datos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void borrarEmpleado(Empleado emp) {
        if (empleados.contains(emp)) {
            empleados.remove(emp);
        }
    }

    public void actualizarValoresEmpleado(int pos, Empleado emp) {
        if (empleados.contains(emp)) {
            if (empleados.get(pos).getId() == emp.getId()) {
                empleados.get(pos).setNombre(emp.getNombre());
                empleados.get(pos).setSueldo(emp.getSueldo());
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se puede cambiar el id del empleado",
                        "Mensaje Panel", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Este empleado no se encuentra en la base de datos",
                    "Mensaje Base de datos", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public String toString() {
        return "Empleados{" + "empleados=" + empleados + '}';
    }
}
