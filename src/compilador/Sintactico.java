/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import static compilador.Util.imprime;
import static compilador.Util.imprimeLn;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Sintactico {

    final String NOMBRE_ARCHIVO = "compilador.lr";
    final String FIN_PILA = "$";
    private Stack<String> pila;
    private Lexico lex;
    private int filas, columnas;
    private boolean aceptacion;
    private ArrayList<String> reglas;
    private ArrayList<Integer> idReglas;
    private ArrayList<Integer> longReglas;
    private int[][] tablaLr;

    public Sintactico(String entrada) {
        lex = new Lexico(entrada);
        reglas = new ArrayList();
        idReglas = new ArrayList();
        longReglas = new ArrayList();
        cargaArchivo();

    }

    public boolean cargaArchivo() {
        File archivo;
        Scanner s;
        StringTokenizer stringTokenizer;
        String linea;
        int maxReglas;
        try {
            archivo = new File(NOMBRE_ARCHIVO);
            s = new Scanner(archivo);
            /*Manejo del archvio compilador.lr*/
            linea = s.nextLine();
            maxReglas = Integer.parseInt(linea);
            for (int i = 0; i < maxReglas; i++) {
                linea = s.nextLine();
                stringTokenizer = new StringTokenizer(linea);
                idReglas.add(Integer.parseInt(stringTokenizer.nextToken()));
                longReglas.add(Integer.parseInt(stringTokenizer.nextToken()));
                reglas.add(stringTokenizer.nextToken());
            }
            linea = s.nextLine();
            stringTokenizer = new StringTokenizer(linea);
            filas = Integer.parseInt(stringTokenizer.nextToken());
            columnas = Integer.parseInt(stringTokenizer.nextToken());
            tablaLr = new int[filas][columnas];
            for (int i = 0; i < filas; i++) {
                linea = s.nextLine();
                stringTokenizer = new StringTokenizer(linea);
                for (int j = 0; j < columnas; j++) {
                    tablaLr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            s.close();
            return true;
        } catch (FileNotFoundException e) {
            imprimeLn(e.getMessage());
            return false;
        }
    }

    public boolean analiza() {
        int fila, columna, accion, regla, i, j;
        Simbolo actual;
        if (reglas.isEmpty()) {
            return false;
        }
        aceptacion = false;
        pila = new Stack();
        pila.push(FIN_PILA);
        pila.push("0");
        actual = lex.sigSimbolo();
        while (!aceptacion) {
            try {
                fila = Integer.parseInt(pila.peek());
                columna = actual.tipo;
                accion = tablaLr[fila][columna];
                if (accion > 0) {
                    pila.push(actual.lexema);
                    pila.push(String.valueOf(accion));
                    actual = lex.sigSimbolo();
                } else if (accion < 0) {
                    if (accion == -1) {
                        imprimeLn("Accion -1");
                        aceptacion = true;
                        break;
                    }
                    regla = (-1) * (accion + 2);
                    //imprime regla
                    //imprime longitu de regla longReglas.get(regla);
                    for (i = 0, j = longReglas.get(regla); i < j; i++) {
                        pila.pop();
                        pila.pop();
                    }
                    //imprime pop*(i*2)
                    fila = Integer.parseInt(pila.peek());
                    columna = idReglas.get(regla);
                    accion = tablaLr[fila][columna];
                    pila.push(reglas.get(regla));
                    pila.push(String.valueOf(accion));
                } else {
                    break;
                }

            } catch (IndexOutOfBoundsException e) {
                imprime(e.getMessage());
            }
        }
        return aceptacion;
    }

    public ArrayList<String> getReglas() {
        return reglas;
    }

    public ArrayList<Integer> getIdReglas() {
        return idReglas;
    }

    public ArrayList<Integer> getLongReglas() {
        return longReglas;
    }

    public int[][] getTablaLr() {
        return tablaLr;
    }

}
