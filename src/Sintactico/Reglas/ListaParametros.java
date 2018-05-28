/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico.Reglas;

import Contenedores.NoTerminal;
import Contenedores.Terminal;
import Contenedores.Token;
import Interfaces.Constantes;
import Interfaces.ElementoPila;
import Interfaces.Nodo;
import Semantico.Semantico;
import Util.Pila;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class ListaParametros extends Nodo implements Constantes {

    private int id = R13;
    private String tipoParam, idParam;
    private Nodo listaParam;

    public ListaParametros(Pila<ElementoPila> p) {
        p.desapila();
        listaParam = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        idParam = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        tipoParam = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        p.desapila();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_LISTA_PARAM + NL;
        info += "< " + tipoParam + " >" + " < " + idParam + " >";
        if (listaParam != null) {
            info += listaParam.getArbol();
        }
        info += FIN_LISTA_PARAM + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        if (s.existeVar(idParam, ambito)) {
            imprimeln("Si existe " + idParam);
            s.insertarError(ERROR_PARAMETRO_REDEFINIDO, idParam);
        } else {
            parametrosGlobales.add(tipo.charAt(0));
            tokenGlobalAux = new Token(idParam, tipo, ambito);
            s.insertarToken(tokenGlobalAux);
        }
        if (listaParam != null) {
            listaParam.validarSemanticamente(tipo, ambito, s);
        }
    }

    @Override
    public String getCodigoASM() {
        return "Pendiente Lista Parametros id " + id;
    }

}
