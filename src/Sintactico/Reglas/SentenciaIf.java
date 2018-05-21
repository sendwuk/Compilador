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
import Semantico.Semantico;
import Util.Pila;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class SentenciaIf extends Nodo implements Constantes {

    private int id = R22;
    private Nodo otroIf, sentencia, expresion;
    String miIf, parentesisIzq, parentesisDer;

    public SentenciaIf(Pila<ElementoPila> p) {
        p.desapila();
        otroIf = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        sentencia = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        parentesisDer = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        parentesisIzq = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        miIf = ((Terminal) p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_SENTENCIA + NL;
        info += TAB + "< " + miIf + " >" + " < " + parentesisIzq + " >";
        if (expresion != null) {
            info += expresion.getArbol();
        }
        info += " < " + parentesisDer + " >";
        if (sentencia != null) {
            info += sentencia.getArbol();
        }
        if (otroIf != null) {
            info += otroIf.getArbol();
        }
        info += FIN_SENTENCIA + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        if (expresion != null) {
            expresion.validarSemanticamente(ambito, s);
        }
        if (sentencia != null) {
            sentencia.validarSemanticamente(ambito, s);
        }
        if (otroIf != null) {
            otroIf.validarSemanticamente(ambito, s);
        }

        return OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
