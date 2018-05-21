/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Contenedores.Token;
import Semantico.Semantico;
import Util.Util;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public abstract class Nodo extends Util implements Constantes {
    public String ambito;
    public String tipo;
    public Token tokenGlobalAux;
    public abstract int getID();

    public abstract String getArbol();

    public abstract char validarSemanticamente(String ambito,Semantico s);
    public abstract void validarSemanticamente(String tipo,String ambito,Semantico s);

}
