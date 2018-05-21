/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import Contenedores.Token;
import Contenedores.Error;
import Interfaces.Constantes;
import Interfaces.Nodo;
import Sintactico.Sintactico;
import static Util.Util.imprimeln;
import java.util.ArrayList;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Semantico implements Constantes {

    private Sintactico sintactico;
    private ArrayList<Token> tablaSimbolos;
    private ArrayList<Error> tablaErrores;
    private Nodo arbol;

    public Semantico(String entrada) {
        sintactico = new Sintactico(entrada);
        tablaSimbolos = new ArrayList<>();
        tablaErrores = new ArrayList<>();
        arbol = sintactico.analizaArbol();
    }

    public boolean analiza() {
        if (arbol != null) {
            arbol.validarSemanticamente(AMBITO_GLOBAL, this);
            return cantErrores() == 0;
        }
        imprimeln("Arbol null");
        return false;
    }

    public boolean existeVar(String id, String ambito) {
        int cantSimbolos = cantSimbolos();
        if (cantSimbolos > 0) {
            for (int i = 0; i < cantSimbolos; i++) {
                if (tablaSimbolos.get(i).ambito.equals(ambito)
                        && tablaSimbolos.get(i).id.equals(id)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public char existeTipo(String id, String ambito) {
        imprimeln("Exite tipo buscando id "+id+" Ambito "+ambito);
        int cantSimbolos=cantSimbolos();
       if(cantSimbolos()>0){
           for(int i=0,j=cantSimbolos;i<j;i++){
               if(tablaSimbolos.get(i).id.equals(id)&&
                       tablaSimbolos.get(i).ambito.equals(ambito)){
                   imprimeln("Encontrado");
                   return tablaSimbolos.get(i).tipo.charAt(0);
               }
           }
       }
       return NOT_OK;
    }

    public boolean insertarToken(Token tk) {
        return tablaSimbolos.add(tk);
    }

    public boolean insertarError(int id, String token) {
        Error error;
        String info;
        switch (id) {
            case ERROR_VARIABLE_REDEFINIDA:
                info = "Variable " + token + " redefinida";
                break;
            case ERROR_FUNCION_REDEFINIDA:
                info = "Funcion " + token + " redefinida";
                break;
            case ERROR_PARAMETRO_REDEFINIDO:
                info = "Parametros " + token + " redefinido";
                break;
            case ERROR_VARIABLE_NO_DEFINIDA:
                info = "Variable " + token + " no definida";
                break;
            case ERROR_TIPO_NO_COINCIDE:
                info = "Tipo " + token + " no coincide con la declaración";
                break;
            case ERROR_CANT_PARAMETROS_NO_COINCIDE:
                info = "Cantidad de parametros diferente a la esperada id " + token;
                break;
            case ERROR_ARGUMENTOS_NO_COINCIDE:
                info = "Tipo de arguementos no coincide con la declaracion id " + token;
                break;
            case ERROR_OPERANDOS_DISTINTOS:
                info = "Operandos no compatibles " + token;
                break;
            default:
                info = "Error desconocido";
                break;
        }
        error = new Error(id, info);
        return tablaErrores.add(error);
    }

    public Token getToken(String id, String ambito) {
        int cantSimbolos = cantSimbolos();
        if (cantSimbolos > 0) {
            for (int i = 0; i < cantSimbolos; i++) {
                if (tablaSimbolos.get(i).id.equals(id)
                        && tablaSimbolos.get(i).ambito.equals(ambito)) {
                    return tablaSimbolos.get(i);
                }
            }
        }
        return null;
    }

    public Nodo getArbol() {
        return arbol;
    }

    public String imprimeErrores() {
        String info =TABLA_ERRORES+NL;
        for (int i = 0, j = cantErrores(); i < j; i++) {
            info += tablaErrores.get(i).toString() + NL;
        }
        return info;
    }
    public String imprimeSimbolos(){
        String info=TABLA_SIMBOLOS+NL;
        for(int i=0,j=cantSimbolos();i<j;i++){
            info+=tablaSimbolos.get(i).toString()+NL;
        }
        return info;
    }

    public ArrayList<Token> getTablaSimbolos() {
        return tablaSimbolos;
    }

    public int cantSimbolos() {
        return tablaSimbolos.size();
    }

    public ArrayList<Error> getTablaErrores() {
        return tablaErrores;
    }

    public int cantErrores() {
        return tablaErrores.size();
    }

}
