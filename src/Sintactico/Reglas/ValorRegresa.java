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
public class ValorRegresa extends Nodo implements Constantes {

    private int id = R30;
    private Nodo expresion;

    public ValorRegresa(Pila<ElementoPila> p) {
        p.desapila();
        expresion = ((NoTerminal) p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_VALOR_REGRESA + NL;
        if (expresion != null) {
            info += expresion.getArbol();
        }
        info += FIN_VALOR_REGRESA + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        if (expresion != null) {
            return expresion.validarSemanticamente(ambito, s);
        }
        return NOT_OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoASM() {
        String info = "";
        if (expresion != null) {
            info += expresion.getCodigoASM();
        }
        return info;

    }

}
