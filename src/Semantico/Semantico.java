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
            arbol.validarSemanticamente(AMBITO_GLOBAL,this);
            return cantErrores()==0;
        }
        return false;
    }
    
     public boolean existeVar(String id,String ambito){
         int cantSimbolos=cantSimbolos();
         if(cantSimbolos>0){
             for(int i=0;i<cantSimbolos;i++){
                 if(tablaSimbolos.get(i).ambito.equals(ambito)&&
                         tablaSimbolos.get(i).id.equals(id))
                     return true;
             }
             return false;
         }
        return false;
    }
     public char existeTipo(String id,String ambito){
         int cantSimbolos=cantSimbolos();
         Token tk = getToken(id,ambito);
         if(cantSimbolos>0){
             for(int i=0;i<cantSimbolos;i++){
                 if(tablaSimbolos.get(i).ambito.equals(ambito)&&
                         tablaSimbolos.get(i).id.equals(id)){
                     if(tk!=null)
                     return getToken(id,ambito).tipo.charAt(0);
                     else return NOT_OK;
                 }
             }
             return NOT_OK;
         }
         return NOT_OK;
     }
    public boolean insertarToken(Token tk){
        return tablaSimbolos.add(tk);
    }
    public boolean insertarError(int id,String info){
        Error error= new Error(id,info);
        return tablaErrores.add(error);
    }
    public Token getToken(String id,String ambito){
        int cantSimbolos=cantSimbolos();
        if(cantSimbolos>0){
            for(int i=0;i<cantSimbolos;i++){
                if(tablaSimbolos.get(i).id.equals(id) &&
                        tablaSimbolos.get(i).ambito.equals(ambito)){
                    return tablaSimbolos.get(i);
                }
            }
        }
        return null;
    }

    public Nodo getArbol() {
        return arbol;
    }

    public ArrayList<Token> getTablaSimbolos() {
        return tablaSimbolos;
    }
    public int cantSimbolos(){
    return tablaSimbolos.size();
            }
    public ArrayList<Error> getTablaErrores(){
        return tablaErrores;
    }
    public int cantErrores(){
        return tablaErrores.size();
    }
 
}
