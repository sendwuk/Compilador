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
    private Pila<String> pila;
    private Pila<Simbolo> pilaSimbolos;
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

     public final boolean cargaArchivo() {
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
        aceptacion = false;
        Simbolo actual;
        if (reglas.isEmpty()) {
            imprimeLn("Reglas vacias");
            return aceptacion;
        }
        pila = new Pila();
        pilaSimbolos = new Pila<>();
        pila.apila(FIN_PILA);
        pila.apila("0");
        actual = lex.sigSimbolo();
        imprime("Simbolo leido: ");imprimeLn(actual.lexema);
        imprime("Tipo: ");imprimeLn(actual.tipo);
        while (!aceptacion) {
            imprimeLn("**********Estado de la pila*******************");
            imprime(pila.muestra());
            imprimeLn("**********************************************");
            try {
                fila = Integer.parseInt(pila.tope());
                imprime("Fila: ");imprimeLn(fila);
                columna = actual.tipo;
                imprime("Columna: ");imprimeLn(columna);
                accion = tablaLr[fila][columna];
                imprime("Accion: ");imprimeLn(accion);
                if (accion > 0) {
                    pila.apila(actual.lexema);
                    pila.apila(String.valueOf(accion));
                    actual = lex.sigSimbolo();
                } else if (accion < 0) {
                    if (accion == -1) {
                        imprimeLn("Accion -1");
                        aceptacion = true;
                        break;
                    }
                    regla = (-1) * (accion + 2);
                    //imprime regla
                    imprime("Regla: ");imprimeLn(regla+1);
                    imprime("Longitud regla: ");imprimeLn(longReglas.get(regla));
                    //imprime longitud de regla longReglas.get(regla);
                    imprimeLn("**************POP'S********************");
                    for (i = 0, j = longReglas.get(regla); i < j; i++) {
                        imprimeLn("PoP:"+pila.desapila());
                        imprimeLn("PoP:"+pila.desapila());
                    }
                    imprimeLn("*************************");
                    //imprime pop*(i*2)
                    imprime("cant de pops: ");imprimeLn(i*2);
                    fila = Integer.parseInt(pila.tope());
                    columna = idReglas.get(regla);
                    accion = tablaLr[fila][columna];
                    pila.apila(reglas.get(regla));
                    pila.apila(String.valueOf(accion));
                } else {
                    imprimeLn("Else");
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
