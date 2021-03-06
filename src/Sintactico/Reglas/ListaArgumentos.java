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
public class ListaArgumentos extends Nodo implements Constantes {

    private int id = R34;
    private Nodo listaArgumentos, expresion;

    public ListaArgumentos(Pila<ElementoPila> p) {
        p.desapila();
        listaArgumentos = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        expresion = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        p.desapila();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_LISTA_ARGUMENTOS + NL;
        if (expresion != null) {
            info += expresion.getArbol();
        }
        if (listaArgumentos != null) {
            info += listaArgumentos.getArbol();
        }
        info += FIN_LISTA_ARGUMENTOS + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        char tipoExp = ' ';
        if (expresion != null) {
            tipoExp = expresion.validarSemanticamente(ambito, s);
        }
        argumentosGlobales.add(tipoExp);
        if (listaArgumentos != null) {
            listaArgumentos.validarSemanticamente(ambito, s);
        }
        return tipoExp;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoASM() {
        return "Pendiente listaArgumentos id " + id;

    }

}
