/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class IOSQL {    

    public static Connection abrirConexionBD(String url, String user, String pass) throws IOSQLException{
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new IOSQLException("Error al abrir la conexión");
        }
        return conn;
    }

    public static ResultSet getResultSet(Connection conn, String query) throws IOSQLException {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new IOSQLException("Error al obtener el ResultSet");
        }
        return rs;
    }

    public static ResultSetMetaData getResultSetMetadData(ResultSet rs) throws IOSQLException {
        ResultSetMetaData rm = null;
        try {
            rm = rs.getMetaData();
        } catch (SQLException e) {
            throw new IOSQLException("Error al obtener los metadatos");
        }
        return rm;
    }

    public static String printRs(ResultSet rs) throws IOSQLException {
        String salida = "";
        ResultSetMetaData meta;
        meta = IOSQL.getResultSetMetadData(rs);

        int nCol;
        try {
            nCol = meta.getColumnCount();
        } catch (SQLException ex) {
            throw new IOSQLException("Error al obtener el número");
        }
        String[] nombreColumnas = new String[nCol];
        String[] tipoColumnas = new String[nCol];
        Object[] valColumnas = new Object[nCol];
        for (int i = 0; i < nCol; i++) {
            try {
                nombreColumnas[i] = meta.getColumnName(i + 1);
                tipoColumnas[i] = meta.getColumnTypeName(i + 1);
            } catch (SQLException e) {
                throw new IOSQLException("Error al rellenar la cabecera");
            }
        }
        for (int i = 0; i < nCol; i++) {
            salida += nombreColumnas[i] + "[" + tipoColumnas[i] + "]\t";
        }
        salida += "\n";

        try {
            rs.beforeFirst();
            while (rs.next()) {
                for (int i = 0; i < nCol; i++) {
                    valColumnas[i] = rs.getObject(i + 1);
                }
                for (int i = 0; i < nCol; i++) {
                    salida += valColumnas[i].toString();
                    salida += "\t\t";                                       
                }
                salida += "\n";
            }
        } catch (SQLException ex) {
            throw new IOSQLException("Error al recorrer el ResultSet");
        }        
        return salida;
    }//end printRs()
    
    public static int getNumFilas(ResultSet rs) throws IOSQLException{
        int contador = 0;
        try {
            rs.beforeFirst();
            while(rs.next()){
                contador++;
            }
        } catch (SQLException e) {
            throw new IOSQLException("Error al obtener el número de filas");
        }
        return contador;
    }
        
    public static void cerrarConexionBD(Connection conn) throws IOSQLException {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new IOSQLException("Error al cerrar la conexión" + ex.getMessage());
        }
    }      
}

class IOSQLException extends Exception {

    public IOSQLException(String msg) {
        super(msg);
    }
}
