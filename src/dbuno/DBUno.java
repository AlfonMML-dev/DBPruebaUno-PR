/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class DBUno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOClassNotFoundException, IOSQLException {
        // TODO code application logic here
        Empleados empleados = new Empleados();
        IOSQL.abrirConexionBD("pepe", "pepa");
        String query = "SELECT * FROM empleado";
        ResultSet rs = IOSQL.getResultSet(query);
        int numFilas = IOSQL.getNumFilas(rs);
        String resultado = IOSQL.printRs(rs);
        empleados = rellenarEmpleados(rs);
        Ventana ventana = new Ventana(empleados);
        System.out.println(resultado);
        System.out.println(numFilas);
        IOSQL.cerrarConexionBD();
    }

    public static Empleados rellenarEmpleados(ResultSet rs) throws IOSQLException {
        Empleados empleados = new Empleados();        
        String[] nombreColumnas = IOSQL.getNomColumn(rs);
        String[] tipoColumnas = IOSQL.getTypeColumn(rs);
        Object[][] valFilas = IOSQL.getValFila(rs);
        Empleado emp;
        int id;
        String nombreEmp;
        double sueldo;        
        for (int i = 0; i < valFilas.length; i++) {
            id = (int) valFilas[i][0];
            nombreEmp = String.valueOf(valFilas[i][1]); 
            sueldo = Double.parseDouble(String.valueOf(valFilas[i][2]));
            //Da error de la siguiente forma
//            sueldo = (double) valFilas[i][2]; 
            emp = new Empleado(id, nombreEmp, sueldo);
            empleados.insertaEmpleado(emp);
        }
        return empleados;
    }
}
