/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Contenedores;

import Interfaces.ElementoPila;
import Interfaces.Nodo;
import Util.Util;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class NoTerminal extends ElementoPila{
    String lexema;
    Nodo nodo;
    
    public NoTerminal(String lexema,Nodo nodo){
    this.lexema=lexema;
    this.nodo=nodo;
    }
    @Override
    public void mostrar() {
        Util.imprimeln(lexema);
    }
    public String getLexema(){
        return lexema;
    }
    public Nodo getNodo(){
        return nodo;
    }
    @Override
    public String toString(){
        return lexema;
    }
    

  

}
