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
public class SentenciaReturn extends Nodo implements Constantes {

    private int id = R24;
    private Nodo valorRetorno;
    private String miRetorno;

    public SentenciaReturn(Pila<ElementoPila> p) {
        p.desapila();
        p.desapila();
        p.desapila();
        valorRetorno = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        miRetorno = ((Terminal) p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_SENTENCIA;
        info += TAB + "< " + miRetorno + " >";
        if (valorRetorno != null) {
            info += valorRetorno.getArbol();
        }
        info += FIN_SENTENCIA + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R"+id);
        char valor = ' ';
        if (valorRetorno != null) {
            valor = valorRetorno.validarSemanticamente(ambito, s);
        }
        if (valor != s.existeTipo(ambito, AMBITO_GLOBAL)) {
            imprimeln("Si hay error "+valor+"no coincide con el valor de retorno");
            s.insertarError(ERROR_TIPO_NO_COINCIDE, miRetorno);
            return NOT_OK;
        }
        return OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
