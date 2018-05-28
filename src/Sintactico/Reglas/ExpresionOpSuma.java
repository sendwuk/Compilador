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

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class ExpresionOpSuma extends Nodo implements Constantes {

    private int id = R44;
    private Nodo expresion;
    private String opSuma;

    public ExpresionOpSuma(Pila<ElementoPila> p) {
        p.desapila();
        expresion = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        opSuma = ((Terminal) p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info = INICIO_EXPRESION + NL;
        info += TAB + " < " + opSuma + " >" + NL;
        if (expresion != null) {
            info += expresion.getArbol();
        }
        info += FIN_EXPRESION + NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        imprimeln("Validando R" + id);
        return expresion.validarSemanticamente(ambito, s);
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoASM() {
        String info="";
        if(expresion!=null)info+=expresion+NL;
        
        return "Pendiente Expresion Op Suma id " + id+NL+info;
    }

}
