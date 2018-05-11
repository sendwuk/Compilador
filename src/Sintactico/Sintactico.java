/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico;

import Contenedores.Accion;
import Interfaces.Nodo;
import Interfaces.Constantes;
import Contenedores.Simbolo;
import Interfaces.ElementoPila;
import Contenedores.NoTerminal;
import Contenedores.Terminal;
import Lexico.Lexico;
import Sintactico.Reglas.*;
import Util.Pila;
import static Util.Util.imprime;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import static Util.Util.imprimeln;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Sintactico implements Constantes {

   
    private Pila<ElementoPila> pila;
    private Pila<Simbolo> pilaSimbolos;
    private Lexico lex;
    private int filas, columnas;
    private boolean aceptacion;
    private ArrayList<String> reglas;
    private ArrayList<Integer> idReglas;
    private ArrayList<Integer> longReglas;
    private int[][] tablaLr;
    private String arbol;

    public Sintactico(String entrada) {
        lex = new Lexico(entrada);
        reglas = new ArrayList<>();
        idReglas = new ArrayList<>();
        longReglas = new ArrayList<>();
        arbol="";
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

    public boolean analiza(boolean generarArbol) {
        int fila, columna, accion, regla, i, j;
        Accion aux;
        Nodo raiz;
        raiz = null;
        aceptacion = false;
        Simbolo actual;
        if (reglas.isEmpty()) {
            imprimeln("Reglas vacias");
            return aceptacion;
        }
        pila = new Pila<>();
        pilaSimbolos = new Pila<>();
        pila.apila(new Terminal(FIN_PILA));
        pila.apila(new Accion(0));
        actual = lex.sigSimbolo();
        imprime("Simbolo leido: ");
        imprimeln(actual.lexema);
        imprime("Tipo: ");
        imprimeln(lex.getTipo(actual.tipo));
        while (!aceptacion) {
            try {
                aux = (Accion) pila.tope();
                fila = aux.getAccion();
                columna = actual.tipo;
                accion = tablaLr[fila][columna];
                if (accion > 0) {
                    pila.apila(new Terminal(actual.lexema));
                    pila.apila(new Accion(accion));
                    actual = lex.sigSimbolo();
                    imprime("Simbolo leido: ");
                    imprimeln(actual.lexema);
                    imprime("Tipo: ");
                    imprimeln(lex.getTipo(actual.tipo));
                } else if (accion < 0) {
                    if (accion == -1) {
                        aceptacion = true;
                        return aceptacion;
                    }
                    regla = (-1) * (accion + 2);
                    if(generarArbol){
                     generarArbol(regla,raiz);
                    }else{
                        for (i = 0, j = longReglas.get(regla); i < j; i++) {
                        imprimeln("1. "+pila.desapila());
                        imprimeln("2."+pila.desapila());
                    }
                    }
                    aux = (Accion) pila.tope();
                    fila = aux.getAccion();
                    columna = idReglas.get(regla);
                    accion = tablaLr[fila][columna];
                    pila.apila(new NoTerminal(reglas.get(regla), raiz));
                    pila.apila(new Accion(accion));
                } else {
                    imprimeln("Error no aceptado");
                    aceptacion = false;
                    return aceptacion;
                }
            } catch (IndexOutOfBoundsException e) {
                imprime(e.getMessage());
            }
        }
        /* imprimeln("*******************Pila simbolos************");
        imprime(pilaSimbolos.muestra());
        imprimeln("***************************");*/
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

    private void generarArbol(int regla, Nodo raiz) {
        switch (regla) {
            case PROGRAMA:
                raiz = new Programa(pila);
                arbol+=raiz.getArbol();
                break;
            case DEFINICIONES:
                raiz=new Definiciones(pila);
                arbol+=raiz.getArbol();
                break;
            case DEFINICION:
                raiz=new Definicion(pila);
                arbol+=raiz.getArbol();
                break;
            case DEFINICION_R5:
                raiz= new DefinicionR5(pila);
                arbol+=raiz.getArbol();
                break;
            case DEFINICION_VAR:
                raiz= new DefinicionVariable(pila);
                arbol+=raiz.getArbol();
                break;
            case LISTA_VAR:
                raiz= new ListaVariables(pila);
                arbol+=raiz.getArbol();
                break;
            case DEFINICION_FUNCION:
                raiz= new DefinicionFuncion(pila);
                arbol+=raiz.getArbol();
                break;
            case PARAMETROS:
                raiz= new Parametros(pila);
                arbol+=raiz.getArbol();
                break;
            case LISTA_PARAMETROS:
                raiz= new ListaParametros(pila);
                arbol+=raiz.getArbol();
                break;
            case BLOQUE_FUNCION:
                raiz= new BloqueFuncion(pila);
                arbol+=raiz.getArbol();
                break;
            case DEF_LOCALES:
                raiz= new DefinicionesLocales(pila);
                arbol+=raiz.getArbol();
                break;
            case DEF_LOCAL:
                raiz= new DefinicionLocal(pila);
                arbol+=raiz.getArbol();
               break;
            case DEF_LOCAL_R18:
                raiz= new DefinicionLocalR18(pila);
                arbol+=raiz.getArbol();
                break;
            case SENTENCIAS:
                raiz= new Sentencias(pila);
                arbol+=raiz.getArbol();
                break;
            case SENTENCIA_R21:
                raiz= new Sentencia(pila);
                arbol+=raiz.getArbol();
                break;
            case SENTENCIA_R22:
                break;
               
                

        }
        
    }


}
