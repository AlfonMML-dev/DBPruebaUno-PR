/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class DBUno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, IOSQLException {
        // TODO code application logic here

        IOSQL.abrirConexionBD("jdbc:mysql://localhost/DEPEMP",
                "pepe", "pepa");
        String query = "SELECT * FROM empleados";
        ResultSet rs = IOSQL.getResultSet(query);
        int numFilas = IOSQL.getNumFilas(rs);
        String resultado = IOSQL.printRs(rs);
        System.out.println(resultado);
        System.out.println(numFilas);
        IOSQL.cerrarConexionBD();

    }
}
