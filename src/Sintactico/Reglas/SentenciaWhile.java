/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico.Reglas;

import Contenedores.NoTerminal;
import Contenedores.Terminal;
import Interfaces.Constantes;
import Interfaces.ElementoPila;
import Interfaces.Nodo;
import Util.Pila;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class SentenciaWhile extends Nodo implements Constantes {

    private int id = R23;
    private Nodo bloque, expresion;
    private String parentesisIzq, parentesisDer, miWhile;

    public SentenciaWhile(Pila<ElementoPila> p) {
        p.desapila();
        bloque=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        parentesisDer=((Terminal)p.desapila()).getLexema();
        p.desapila();
        expresion=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        parentesisIzq=((Terminal)p.desapila()).getLexema();
        p.desapila();
        miWhile=((Terminal)p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_SENTENCIA;
        info+=TAB+"< "+miWhile+" >"+" < "+parentesisIzq+" >";
        if(expresion!=null)info+=expresion.getArbol();
        info+=" < "+parentesisDer+" >";
        if(bloque!=null)info+=bloque.getArbol();
        info+=FIN_SENTENCIA+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
