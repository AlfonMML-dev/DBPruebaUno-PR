/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Empleados {
    private ArrayList<Empleado> empleados;
    
    
    public Empleados() {
        empleados = new ArrayList<>();
    }
    
    public void insertaEmpleado(Empleado emp){
        if (!empleados.contains(emp)) {
            empleados.add(emp);
        }
    }
    
    public void borrarEmpleado(Empleado emp){
        if (empleados.contains(emp)) {
            empleados.remove(emp);
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
