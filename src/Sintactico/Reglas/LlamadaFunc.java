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
import java.util.Objects;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class LlamadaFunc extends Nodo implements Constantes {

    private int id = R40;
    private String parentesisIzq, parentesisDer, identificador;
    private Nodo argumento;

    public LlamadaFunc(Pila<ElementoPila> p) {
        p.desapila();
        parentesisDer = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        argumento = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        parentesisIzq = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        identificador = ((Terminal) p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_LLAMADA_FUNC + NL;
        info += TAB + "< " + identificador + " >";
        info += " < " + parentesisIzq + " >";
        if (argumento != null) {
            info += argumento.getArbol();
        }
        info += " < " + parentesisDer + " >" + NL;
        info += FIN_LLAMADA_FUNC + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        int cantParametros = 0;
        if (s.existeVar(identificador, ambito)) {
            s.insertarError(ERROR_FUNCION_REDEFINIDA, identificador);
            return NOT_OK;
        } else {
            if (argumento != null) {
                argumento.validarSemanticamente(ambito, s);
                cantParametros = tokenGlobalAux.getCantParametros();
            }
            if (cantParametros != tokenGlobalAux.getCantParametros()) {
                s.insertarError(ERROR_CANT_PARAMETROS_NO_COINCIDE, identificador);

            } else {
                for (int i = 0; i < cantParametros; i++) {
                    if (!Objects.equals(
                            s.getToken(identificador, ambito).getParametroEn(i),
                            tokenGlobalAux.getParametroEn(i))) {
                        s.insertarError(ERROR_ARGUMENTOS_NO_COINCIDE, identificador);
                    }
                }
            }
            return s.existeTipo(identificador, ambito);
        }
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
