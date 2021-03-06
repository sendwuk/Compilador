/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico.Reglas;

import Contenedores.NoTerminal;
import Interfaces.Constantes;
import Interfaces.ElementoPila;
import Interfaces.Nodo;
import Semantico.Semantico;
import Util.Pila;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class ExpresionTermino extends Nodo implements Constantes {

    private int id = R52;
    private Nodo termino;

    public ExpresionTermino(Pila<ElementoPila> p) {
        p.desapila();
        termino = ((NoTerminal) p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_EXPRESION + NL;
        if (termino != null) {
            info += termino.getArbol();
        }
        info += FIN_EXPRESION + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        return termino.validarSemanticamente(ambito, s);
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoASM() {
        String info="";
        if(termino!=null)info+=termino.getCodigoASM();
        return info;
    }

}
