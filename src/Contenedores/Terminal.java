/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Contenedores;

import Interfaces.ElementoPila;
import Util.Util;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Terminal extends ElementoPila{
    private String lexema;
    
    public Terminal(String lexema){
    this.lexema=lexema;
    }
    @Override
    public void mostrar() {
        Util.imprimeln(lexema);
    }
    public String getLexema() {
    	return lexema;
    }
    @Override
    public String toString(){
        return lexema;
    }

    
}
