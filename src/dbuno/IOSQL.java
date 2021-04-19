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

        int nCol = getNumColumn(rs);
        String[] nombreColumnas = getNomColumn(rs);
        String[] tipoColumnas = getTypeColumn(rs);
        Object[] valColumnas = new Object[nCol];        
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
        int numFilas = 0;
        try {
            //Da error de la siguiente manera que aparece comentada
            // numFilas = rs.getRow();
            rs.beforeFirst();
            while (rs.next()) {
                numFilas++;
            }
        } catch (SQLException e) {
            throw new IOSQLException("Error al obtener el número de filas");
        }
        return numFilas;
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
    
    //Método que devuelve el número de columnas
    public static int getNumColumn(ResultSet rs) throws IOSQLException{
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
    public static String[] getNomColumn(ResultSet rs) throws IOSQLException{
        ResultSetMetaData rm = getResultSetMetadData(rs);
        int numColums = getNumColumn(rs);
        String[] nombreColumnas;        
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
    
    //Método que devuelve el tipo de dato de cada columnas
    public static String[] getTypeColumn(ResultSet rs) throws IOSQLException{
        ResultSetMetaData rm = getResultSetMetadData(rs);
        int numColums = getNumColumn(rs);
        String[] tipoColumnas;        
        tipoColumnas = new String[numColums];
        for (int i = 0; i < tipoColumnas.length; i++) {
            try {
                tipoColumnas[i] = rm.getColumnTypeName(i+1);
            } catch (SQLException ex) {
                throw new IOSQLException("Error al obtner el tipo de dato "
                        + "de las columnas");
            }            
        }
        return tipoColumnas;
    }

    //Método que devuelve los datos de todas las filas
    public static Object[][] getValFila(ResultSet rs) throws IOSQLException{        
        int numFilas = getNumFilas(rs);
        int numColums = getNumColumn(rs);
        Object[][] valColumnas = new Object[numFilas][numColums];
        int posFila = 0; //Indica la posición de la fila
        int posColumn = 0; //Indica la posición de la columna
        try {
            rs.beforeFirst();
            while(rs.next()){
                for (posColumn = 0; posColumn < numColums; posColumn++) {
                    valColumnas[posFila][posColumn] = rs.getObject(posColumn+1);                    
                }
                posFila++;
            }
        } catch (SQLException ex) {
            throw new IOSQLException("Error al obtener los datos de la fila " 
                    + posFila + " en la columna " + posColumn);
        } catch(ArrayIndexOutOfBoundsException a){
            throw new IOSQLException("Error al obtener los datos de la fila " 
                    + posFila + " en la columna " + posColumn);
        }
        return valColumnas;
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