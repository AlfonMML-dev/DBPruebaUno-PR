/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbuno;

import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TratamientoDatos {

    private Panel panel;

    public TratamientoDatos(Panel panel) {
        this.panel = panel;
    }

    private boolean validarNombre(String nombre) {
        char arrayNombre[] = nombre.toCharArray();
        if (arrayNombre.length <= 0) {
            return false;
        }
        if (arrayNombre.length > 20) {
            return false;
        }

        String cadMay = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";//Mayúsculas
        String cadMin = cadMay.toLowerCase();//Minúsculas        
        if (!cadMay.contains(String.valueOf(arrayNombre[0]))) {
            return false;
        }
        for (int i = 1; i < arrayNombre.length; i++) {
            if (!cadMin.contains(String.valueOf(arrayNombre[i]))) {
                return false;
            }
        }
        return true;
    }

    private boolean validarSueldo(String sueldo) {
        char[] arraySueldo = sueldo.toCharArray();
        for (int i = 0; i < arraySueldo.length; i++) {
            if (esLetra(arraySueldo[i])) {
                return false;
            }
        }
        double salario = Double.parseDouble(sueldo);
        if (cifrasEnterasNumero(salario) <= 0
                || cifrasEnterasNumero(salario) > 4) {
            return false;
        }
        return !(cifrasDecimalesNumero(salario) <= 0
                || cifrasDecimalesNumero(salario) > 2);
    }

    //Método que devuelve el número de cifras enteras que tiene un número
    public int cifrasEnterasNumero(double a) {
        int iPart = (int) a;
        String iPartString = String.valueOf(iPart);
        int cifrasEnteras = iPartString.length();
        System.out.println("\n" + "Salario: " + a + ", cifras enteras: "
                + cifrasEnteras);
        return cifrasEnteras;
    }

    //Método que devuelve el número de cifras decimales que tiene un número
    public int cifrasDecimalesNumero(double a) {
        int iPart = (int) a;
        float fPart = (float) (a - iPart);
        String fPartString = String.valueOf(fPart);
        int cifrasDecimales = fPartString.substring(2).length();
        System.out.println("\n" + "Salario: " + a + ", cifras decimales:"
                + cifrasDecimales);
        return cifrasDecimales;
    }

    private boolean esLetra(char c) {
        String letrasMayus = "ABCDEFGHIJKLMNÑOPQRSTUVWYZ";
        String letrasMinus = letrasMayus.toLowerCase();
        String caracter = String.valueOf(c);
        return (letrasMayus.contains(caracter)
                || letrasMinus.contains(caracter));
    }

    private boolean esNumero(char c) {
        String numeros = "0123456789";
        String caracter = String.valueOf(c);
        return numeros.contains(caracter);
    }

    private String errorNombre() {
        JFrame mensaje = new JFrame("El nombre introducido no es válido");
        String cadena = JOptionPane.showInputDialog(mensaje, "Escriba el "
                + "nombre correctamente, sólo con carácteres alfabéticos."
                + "\n" + "El máximo permitido son 20");
        return cadena;
    }

    private String errorSueldo() {
        JFrame mensaje = new JFrame("El sueldo introducido no es válido");
        String cadena = JOptionPane.showInputDialog(mensaje, "Asigne un "
                + "sueldo, con un máximo de 4 dígitos enteros y 2 decimales,"
                + " separados por un punto");
        return cadena;
    }

    public void tratarDatosEmpleado() {
        String cadena;
        if (!validarNombre(panel.getTfNombre().getText())) {
            do {
                cadena = errorNombre();
            } while (!validarNombre(cadena));
            panel.getTfNombre().setText(cadena);
        }
        if (!validarSueldo(panel.getTfSueldo().getText())) {
            do {
                cadena = errorSueldo();
            } while (!validarSueldo(cadena));
            panel.getTfSueldo().setText(cadena);
        }
    }

}
