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

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Programa extends Nodo implements Constantes {

    private int id = R1;
    private Nodo definicion;

    public Programa(Pila<ElementoPila> p) {
        p.desapila();
        definicion = ((NoTerminal) p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_PROGRAMA +NL;
        if (definicion != null) info += definicion.getArbol();
        info += FIN_PROGRAMA +NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
