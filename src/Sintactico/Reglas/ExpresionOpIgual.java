/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico.Reglas;

import Contenedores.Terminal;
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
public class ExpresionOpIgual extends Nodo implements Constantes {

    private int id = R49;
    private Nodo exp1, exp2;
    private String igual;

    public ExpresionOpIgual(Pila<ElementoPila> p) {
        p.desapila();
        exp2 = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        igual = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        exp1 = ((NoTerminal) p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_EXPRESION + NL;
        if (exp1 != null) {
            info += exp1.getArbol();
        }
        info += TAB + "< " + igual + " >" + NL;
        if (exp2 != null) {
            info += exp2.getArbol();
        }
        info += FIN_EXPRESION + NL;
        return info;

    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        char izq, der;
        izq = exp1.validarSemanticamente(ambito, s);
        der = exp2.validarSemanticamente(ambito, s);
        if ((izq == der) && (izq != NOT_OK && der != NOT_OK)) {
            return der;
        } else {
            imprimeln("Error en igual");
            s.insertarError(ERROR_OPERANDOS_DISTINTOS, izq + igual + der);
            return NOT_OK;
        }
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoASM() {
        String izq = "",der = "";
        if (exp1 != null) {
            izq = exp1.getCodigoASM();
        }
        if (exp2 != null) {
            der = exp2.getCodigoASM();
        }
        return izq+"|"+"jne"+"|"+der;
    }

}
