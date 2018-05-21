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

    private Lexico lex;
    private int filas, columnas;
    private boolean aceptacion;
    private ArrayList<String> reglas;
    private ArrayList<Integer> idReglas;
    private ArrayList<Integer> longReglas;
    private int[][] tablaLr;
    private Nodo arbolitoDeNavidad;

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

    public Nodo analizaArbol() {
        int fila, columna, accion, regla, i, j;
        Pila<ElementoPila> pila;
        aceptacion = false;
        Simbolo actual;
        if (reglas.isEmpty()) {
            imprimeln("Reglas vacias");
            return null;
        }
        pila = new Pila<>();
        pila.apila(new Terminal(FIN_PILA));
        pila.apila(new Accion(0));
        actual = lex.sigSimbolo();
        imprime("Simbolo leido: ");
        imprimeln(actual.lexema);
        imprime("Tipo: ");
        imprimeln(lex.getTipo(actual.tipo));
        while (!aceptacion && !pila.vacia()) {
            try {

                fila = ((Accion) pila.tope()).getAccion();
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
                        return arbolitoDeNavidad;
                    }
                    regla = (-1) * (accion + 2);
                    generarArbol(regla, pila);
                    fila = ((Accion) pila.tope()).getAccion();
                    columna = idReglas.get(regla);
                    accion = tablaLr[fila][columna];
                    pila.apila(new NoTerminal(reglas.get(regla), arbolitoDeNavidad));
                    pila.apila(new Accion(accion));
                } else {
                    imprimeln("Error no aceptado");
                }
            } catch (IndexOutOfBoundsException e) {
                imprime(e.getMessage());
            }
        }

        return null;
    }

    public boolean analiza() {
        int fila, columna, accion, regla, i, j;
        Accion aux;
        aceptacion = false;
        Simbolo actual;
        if (reglas.isEmpty()) {
            imprimeln("Reglas vacias");
            return aceptacion;
        }
        Pila<String> pila;
        pila = new Pila<>();
        pila.apila("$");
        pila.apila("0");
        actual = lex.sigSimbolo();
        imprime("Simbolo leido: ");
        imprimeln(actual.lexema);
        imprime("Tipo: ");
        imprimeln(lex.getTipo(actual.tipo));
        while (!aceptacion && !pila.vacia()) {
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
                        return aceptacion;
                    }
                    regla = (-1) * (accion + 2);
                    for (i = 0, j = longReglas.get(regla); i < j; i++) {
                        imprimeln("1. " + pila.desapila());
                        imprimeln("2." + pila.desapila());
                    }
                    fila = Integer.parseInt(pila.tope());
                    columna = idReglas.get(regla);
                    accion = tablaLr[fila][columna];
                    pila.apila(actual.lexema);
                    pila.apila(String.valueOf(accion));
                } else {
                    imprimeln("Error no aceptado");
                    aceptacion = false;
                    return aceptacion;
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

    private void generarArbol(int regla, Pila<ElementoPila> pila) {
        switch (regla) {
            case PROGRAMA:
                arbolitoDeNavidad = new Programa(pila);
                break;
            case DEFINICIONES:
                arbolitoDeNavidad = new Definiciones(pila);
                break;
            case DEFINICION:
                arbolitoDeNavidad = new Definicion(pila);
                break;
            case DEFINICION_R5:
                arbolitoDeNavidad = new DefinicionR5(pila);
                break;
            case DEFINICION_VAR:
                arbolitoDeNavidad = new DefinicionVariable(pila);
                break;
            case LISTA_VAR:
                arbolitoDeNavidad = new ListaVariables(pila);
                break;
            case DEFINICION_FUNCION:
                arbolitoDeNavidad = new DefinicionFuncion(pila);
                break;
            case PARAMETROS:
                arbolitoDeNavidad = new Parametros(pila);
                break;
            case LISTA_PARAMETROS:
                arbolitoDeNavidad = new ListaParametros(pila);
                break;
            case BLOQUE_FUNCION:
                arbolitoDeNavidad = new BloqueFuncion(pila);
                break;
            case DEF_LOCALES:
                arbolitoDeNavidad = new DefinicionesLocales(pila);
                break;
            case DEF_LOCAL:
                arbolitoDeNavidad = new DefinicionLocal(pila);
                break;
            case DEF_LOCAL_R18:
                arbolitoDeNavidad = new DefinicionLocalR18(pila);
                break;
            case SENTENCIAS:
                arbolitoDeNavidad = new Sentencias(pila);
                break;
            case SENTENCIA_R21:
                arbolitoDeNavidad = new Sentencia(pila);
                break;
            case SENTENCIA_R22:
                arbolitoDeNavidad = new SentenciaIf(pila);
                break;
            case SENTENCIA_R23:
                arbolitoDeNavidad = new SentenciaWhile(pila);
                break;
            case SENTENCIA_R24:
                arbolitoDeNavidad = new SentenciaReturn(pila);
                break;
            case SENTENCIA_R25:
                arbolitoDeNavidad = new SentenciaLlamadaFunc(pila);
                break;
            case OTRO:
                arbolitoDeNavidad = new Otro(pila);
                break;
            case BLOQUE:
                arbolitoDeNavidad = new Bloque(pila);
                break;
            case VALOR_REGRESA:
                arbolitoDeNavidad = new ValorRegresa(pila);
                break;
            case ARGS:
                arbolitoDeNavidad = new Argumentos(pila);
                break;
            case LISTA_ARGS:
                arbolitoDeNavidad = new ListaArgumentos(pila);
                break;
            case TERMINO:
                arbolitoDeNavidad = new Termino(pila);
                break;
            case TERMINO_ID:
                arbolitoDeNavidad = new TerminoId(pila);
                break;
            case TERMINO_ENTERO:
                arbolitoDeNavidad = new TerminoInt(pila);
                break;
            case TERMINO_REAL:
                arbolitoDeNavidad = new TerminoFloat(pila);
                break;
            case TERMINO_CADENA:
                arbolitoDeNavidad = new TerminoString(pila);
                break;
            case LLAMADA_FUNCION:
                arbolitoDeNavidad = new LlamadaFunc(pila);
                break;
            case BLOQUE_SENTENCIA:
                arbolitoDeNavidad = new SentenciaBloque(pila);
                break;
            case BLOQUE_SENTENCIA_BLOQUE:
                arbolitoDeNavidad = new SentenciaBloqueR42(pila);
                break;
            case EXPRESION:
                arbolitoDeNavidad = new Expresion(pila);
                break;
            case EXPRESION_SUMA:
                arbolitoDeNavidad = new ExpresionOpSuma(pila);
                break;
            case EXPRESION_NOT:
                arbolitoDeNavidad = new ExpresionOpNot(pila);
                break;
            case EXPRESION_MUL:
                arbolitoDeNavidad = new ExpresionOpMul(pila);
                break;
            case EXPRESION_OP_SUMA:
                arbolitoDeNavidad = new ExpresionSuma(pila);
                break;
            case EXPRESION_RELAC:
                arbolitoDeNavidad = new ExpresionOpRelac(pila);
                break;
            case EXPRESION_IGUAL:
                arbolitoDeNavidad = new ExpresionOpIgual(pila);
                break;
            case EXPRESION_AND:
                arbolitoDeNavidad = new ExpresionAnd(pila);
                break;
            case EXPRESION_OR:
                arbolitoDeNavidad = new ExpresionOr(pila);
                break;
            case EXPRESION_TERMINAL:
                arbolitoDeNavidad = new ExpresionTermino(pila);
                break;
            default:
                arbolitoDeNavidad = null;
                break;

        }

    }

    public Nodo getArbol() {
        return arbolitoDeNavidad;
    }

}
