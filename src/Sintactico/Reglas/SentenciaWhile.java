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
import java.util.StringTokenizer;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class SentenciaWhile extends Nodo implements Constantes {

    private int id = R23;
    private Nodo bloque, expresion;
    private String parentesisIzq, parentesisDer, miWhile;

    public SentenciaWhile(Pila<ElementoPila> p) {
        p.desapila();
        bloque = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        parentesisDer = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        expresion = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        parentesisIzq = ((Terminal) p.desapila()).getLexema();
        p.desapila();
        miWhile = ((Terminal) p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_SENTENCIA;
        info += TAB + "< " + miWhile + " >" + " < " + parentesisIzq + " >";
        if (expresion != null) {
            info += expresion.getArbol();
        }
        info += " < " + parentesisDer + " >";
        if (bloque != null) {
            info += bloque.getArbol();
        }
        info += FIN_SENTENCIA + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        if (expresion != null) {
            expresion.validarSemanticamente(ambito, s);
        }
        if (bloque != null) {
            bloque.validarSemanticamente(ambito, s);
        }
        return OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoASM() {
        String etiquetaInicio = "_inicio_while_", exp = "", bloq = "",
                etiquetaFin = "_fin_while", info = "", izq, der, salto;
        StringTokenizer st;
        info += etiquetaInicio + ":" + NL;
        if (expresion != null) {
            exp = expresion.getCodigoASM();
        }
        st= new StringTokenizer(exp,"|");
        izq=st.nextToken();
        salto=st.nextToken();
        der=st.nextToken();
      
        info += "cmp " + izq + ", " + der + NL;
        info += salto + " " + etiquetaFin + NL;
        if (bloque != null) {
            bloq = bloque.getCodigoASM();
        }
        info += bloq + NL;
        info += "jmp " + etiquetaInicio + NL;
        info += etiquetaFin + ":" + NL;
        return info;
    }

}
