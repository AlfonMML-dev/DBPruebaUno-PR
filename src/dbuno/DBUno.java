/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class DBUno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Connection conn = IOSQL.abrirConexionBD("jdbc:mysql://localhost/DEPEMP", 
                    "pepe", "pepa");
            String query = "SELECT * FROM empleados";
            ResultSet  rs = IOSQL.getResultSet(conn, query);
            int numFilas = IOSQL.getNumFilas(rs);
            String resultado = IOSQL.printRs(rs);
            System.out.println(resultado);
            System.out.println(numFilas);
            IOSQL.cerrarConexionBD(conn);
        } catch (IOSQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
