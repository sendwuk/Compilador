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
import Contenedores.Terminal;
import Contenedores.Token;
import Semantico.Semantico;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class DefinicionVariable extends Nodo implements Constantes {

    private int id = R6;
    private Nodo listaVariables;
    private String idVar, tipoVar;

    public DefinicionVariable(Pila<ElementoPila> p) {
        p.desapila();
        p.desapila();
        p.desapila();
        listaVariables = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        idVar = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        tipoVar = ((Terminal) p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_DEF_VAR + NL;
        info += TAB + "< " + tipoVar + " >" + NL + " < " + idVar + " >" + NL;
        if (listaVariables != null) {
            info += listaVariables.getArbol();
        }
        info += FIN_DEF_VAR + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        if (s.existeVar(idVar, ambito)) {
            imprimeln("Si existe la variable " + idVar);
            s.insertarError(ERROR_VARIABLE_REDEFINIDA, idVar);
        } else {
            tokenGlobalAux = new Token(idVar, tipoVar, ambito);
            s.insertarToken(tokenGlobalAux);
        }
        if (listaVariables != null) {
            listaVariables.validarSemanticamente(tipoVar, ambito, s);
        }
        return OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getIdVar() {
        return idVar;
    }

    public String getTipoVar() {
        return tipoVar;
    }

    @Override
    public String getCodigoASM() {
        String info="";
        /*No retornamos el id de la variable ya que dicha variable
        sera guardada en la tabla de simbolos y segun nuestr logica
        dicha variable seria declarada cuando el programa leyera la tabla
        de simbolos es por eso que no retornamos nada*/
        if(listaVariables!=null)info+=listaVariables.getCodigoASM();
        return info;
    }

}
