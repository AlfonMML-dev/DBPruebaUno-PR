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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class IOSQL {
         
    static final String DB_URL = "jdbc:mysql://localhost/pruebauno";
    static private Connection conn = null;
    static private Statement stmt;

    public static void abrirConexionBD(String user, String pass) 
            throws IOSQLException, IOClassNotFoundException {        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, user, pass);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            throw new IOSQLException("Error al conectarse a la base de datos");
        } catch (ClassNotFoundException e){
            throw new IOClassNotFoundException("NO se ha encontrado el driver " 
                    + e.getMessage());
        }        
    }

    public static ResultSet getResultSet(String query) throws IOSQLException {
        ResultSet rs = null;
        try {            
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new IOSQLException("Error al obtener el ResultSet");
        }
        return rs;
    }

    public static ResultSetMetaData getResultSetMetadData(ResultSet rs) 
            throws IOSQLException {
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
            throw new IOSQLException("Error al obtener el número de columnas");
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

    public static int getNumFilas(ResultSet rs) throws IOSQLException {
        int contador = 0;
        try {
            rs.beforeFirst();
            while (rs.next()) {
                contador++;
            }
        } catch (SQLException e) {
            throw new IOSQLException("Error al obtener el número de filas");
        }
        return contador;
    }
    
    public static int getNumFilasAfectadas(String sql) throws IOSQLException{
        //Número de filas afectadas
        int fA = 0; 
        try {
            fA = stmt.executeUpdate(sql);
        } catch (SQLException e) {            
            String error = "";
            if(e.getErrorCode() == 1062){
                error += "Ya existe un empleado con ese ID";
            }
            throw new IOSQLException("Error al obtener el número de filas afectadas. "
            + error);
        }
        return fA;
    }
    
    public static int getNumColumnas(ResultSet rs) throws IOSQLException{
        ResultSetMetaData rm ;
        int numColums = 0;
        try {
            rm = rs.getMetaData();
            numColums = rm.getColumnCount();            
        } catch (SQLException ex) {
            throw new IOSQLException("Error al obtener el número de columnas");
        }
        return numColums;
    }
    
    //Método que devuelve el nombre de las columnas
    public static String[] getNomColumnas(ResultSet rs) throws IOSQLException{
        ResultSetMetaData rm ;
        int numColums = 0;
        String[] nombreColumnas;
        try {
            rm = rs.getMetaData();
            numColums = rm.getColumnCount();            
        } catch (SQLException ex) {
            throw new IOSQLException("Error al obtener el número de columnas");
        }
        nombreColumnas = new String[numColums];
        for (int i = 0; i < nombreColumnas.length; i++) {
            try {
                nombreColumnas[i] = rm.getColumnName(i+1);
            } catch (SQLException ex) {
                throw new IOSQLException("Error al obtner el nombre de las columnas");
            }            
        }
        return nombreColumnas;
    }

    public static void cerrarConexionBD() throws IOSQLException {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            throw new IOSQLException("Error al cerrar la conexión" 
                    + ex.getMessage());
        }
    }
}

class IOSQLException extends SQLException {

    public IOSQLException(String msg) {
        super(msg);
        JOptionPane.showMessageDialog(null, msg, "DATABASE ERROR", 
                JOptionPane.WARNING_MESSAGE);
    }
}

class IOClassNotFoundException extends ClassNotFoundException {
    public IOClassNotFoundException(String msg){
        super(msg);
        JOptionPane.showMessageDialog(null, msg, "DATABASE ERROR", 
                JOptionPane.WARNING_MESSAGE);
    }
}