/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Util.Util;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public abstract class Nodo extends Util implements Constantes {
    //   public String tipoVar;
    // public char tipo;
    //public Simbolo simbolo;
    public abstract int getID();

    public abstract String getArbol();

    public abstract void validarSemanticamente(String tipoVar);

}
