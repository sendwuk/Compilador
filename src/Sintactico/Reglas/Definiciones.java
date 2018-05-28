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
public class Definiciones extends Nodo implements Constantes {

    private int id = R3;
    private Nodo definicion;
    private Nodo definiciones;

    public Definiciones(Pila<ElementoPila> p) {
        p.desapila();
        definiciones = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        definicion = ((NoTerminal) p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_DEFINICIONES + NL;
        if (definicion != null) {
            info += definicion.getArbol();
        }
        if (definiciones != null) {
            info += definiciones.getArbol();
        }
        info += FIN_DEFINICIONES + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        if (definicion != null) {
            definicion.validarSemanticamente(ambito, s);
        }
        if (definiciones != null) {
            definiciones.validarSemanticamente(ambito, s);
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
        if (definicion != null) {
            info += definicion.getCodigoASM();
        }
        if (definiciones != null) {
            info += definiciones.getCodigoASM();
        }
        return info;
    }

}
