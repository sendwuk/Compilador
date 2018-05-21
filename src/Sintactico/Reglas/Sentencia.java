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
public class Sentencia extends Nodo implements Constantes {

    private int id = R21;
    private Nodo expresion;
    private String idSentencia, asig;

    public Sentencia(Pila<ElementoPila> p) {
        p.desapila();
        p.desapila();
        p.desapila();
        expresion = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        asig = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        idSentencia = ((Terminal) p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_SENTENCIA + NL;
        info += "< " + idSentencia + " >" + " < " + asig + " >" + NL;
        if (expresion != null) {
            info += expresion.getArbol();
        }
        info += FIN_SENTENCIA + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R"+id);
        char tipoExpresion;
        if (!s.existeVar(idSentencia, ambito) && !s.existeVar(idSentencia, AMBITO_GLOBAL)) {
            imprimeln("Si hay error "+idSentencia+" no declara");
            s.insertarError(ERROR_VARIABLE_NO_DEFINIDA, idSentencia);
        }
        tipoExpresion = expresion.validarSemanticamente(ambito, s);
        if (tipoExpresion == s.existeTipo(idSentencia, ambito)
                || tipoExpresion == s.existeTipo(idSentencia, AMBITO_GLOBAL)) {
            return tipoExpresion;
        } else {
            imprimeln("Si hay error "+idSentencia+" no coincide");
            s.insertarError(ERROR_TIPO_NO_COINCIDE, idSentencia);
            return NOT_OK;
        }

    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
