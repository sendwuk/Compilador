/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico.Reglas;

import Util.Pila;
import Interfaces.Nodo;
import Interfaces.Constantes;
import Interfaces.ElementoPila;
import Contenedores.NoTerminal;
import Semantico.Semantico;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class DefinicionR5 extends Nodo implements Constantes {

    private int id = R5;
    private Nodo definicionFuncion;

    public DefinicionR5(Pila<ElementoPila> p) {
        p.desapila();
        definicionFuncion = ((NoTerminal) p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_DEFINICION + NL;
        if (definicionFuncion != null) {
            info += definicionFuncion.getArbol();
        }
        info += FIN_DEFINICION + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        if (definicionFuncion != null) {
            definicionFuncion.validarSemanticamente(ambito, s);
        }
        return OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String tipoVar, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoASM() {
        String info = "";
        if (definicionFuncion != null) {
            info += definicionFuncion.getCodigoASM();
        }
        return info;
    }

}
