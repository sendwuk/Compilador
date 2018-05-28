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
public class ExpresionOpRelac extends Nodo implements Constantes {

    private int id = R48;
    private Nodo exp1, exp2;
    private String opRelac;

    public ExpresionOpRelac(Pila<ElementoPila> p) {
        p.desapila();
        exp2 = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        opRelac = ((Terminal) p.desapila()).getLexema();
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
        info += "< " + opRelac + " >" + NL;
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
            s.insertarError(ERROR_OPERANDOS_DISTINTOS, izq + opRelac + der);
            return NOT_OK;
        }
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoASM() {
        String izq = "",der = "",salto;
        if (exp1 != null) {
            izq = exp1.getCodigoASM();
        }
        if (exp2 != null) {
            der = exp2.getCodigoASM();
        }
        switch (opRelac) {
            case "<":
                salto = "jnl";
                break;
            case ">":
                salto = "jng";
                break;
            case "<=":
                salto = "jnle";
                break;
            case ">=":
                salto = "jnge";
                break;
            default:
                salto = "jmp";
                break;
        }

        return izq+"|"+salto+"|"+der;
    }
  

}
