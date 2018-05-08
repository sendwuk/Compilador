/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import static compilador.Util.imprime;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import static compilador.Util.imprimeln;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Sintactico implements Constantes {

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
        reglas = new ArrayList<>();
        idReglas = new ArrayList<>();
        longReglas = new ArrayList<>();
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
            imprimeln(e.getMessage());
            return false;
        }
    }

    public boolean analiza() {
        int fila, columna, accion, regla, i, j;
        aceptacion = false;
        Simbolo actual;
        if (reglas.isEmpty()) {
            imprimeln("Reglas vacias");
            return aceptacion;
        }
        pila = new Pila<>();
        pilaSimbolos = new Pila<>();
        pila.apila(FIN_PILA);
        pila.apila("0");
        actual = lex.sigSimbolo();
        imprime("Simbolo leido: ");
        imprimeln(actual.lexema);
        imprime("Tipo: ");
        imprimeln(lex.getTipo(actual.tipo));
        while (!aceptacion) {
            try {
                fila = Integer.parseInt(pila.tope());
                columna = actual.tipo;
                accion = tablaLr[fila][columna];
                if (accion > 0) {
                    pila.apila(actual.lexema);
                    pila.apila(String.valueOf(accion));
                    actual = lex.sigSimbolo();
                    imprime("Simbolo leido: ");
                    imprimeln(actual.lexema);
                    imprime("Tipo: ");
                    imprimeln(lex.getTipo(actual.tipo));
                } else if (accion < 0) {
                    if (accion == -1) {
                        aceptacion = true;
                        break;
                    }
                    regla = (-1) * (accion + 2);
                    for (i = 0, j = longReglas.get(regla); i < j; i++) {
                        Simbolo s= new Simbolo();
                        pila.desapila();
                        s.lexema=pila.desapila();
                        pilaSimbolos.apila(s);
                    }
                    fila = Integer.parseInt(pila.tope());
                    columna = idReglas.get(regla);
                    accion = tablaLr[fila][columna];
                    pila.apila(reglas.get(regla));
                    pila.apila(String.valueOf(accion));
                } else {
                    imprimeln("Error no aceptado");
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                imprime(e.getMessage());
            }
        }
        imprimeln("*******************Pila simbolos************");
        imprime(pilaSimbolos.muestra());
        imprimeln("***************************");
       // generarArbol(pilaSimbolos);
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
    private void generarArbol(Pila<Simbolo> pila){
        String accion=pila.desapila().lexema;
        //imprimeln("Generando Arbol");
        imprimeln("<Programa>");
        imprimeln(accion);
        imprimeln("</Programa>");
        
    }

}
