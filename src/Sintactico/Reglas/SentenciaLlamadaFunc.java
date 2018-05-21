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
public class SentenciaLlamadaFunc extends Nodo implements Constantes {
    private int id=R25;
    private Nodo llamadaFunc;
    public SentenciaLlamadaFunc(Pila<ElementoPila>p){
        p.desapila();
        p.desapila();
        p.desapila();
        llamadaFunc=((NoTerminal)p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_SENTENCIA+NL;
        if(llamadaFunc!=null)info+=llamadaFunc.getArbol();
        info+=FIN_SENTENCIA+NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        if(llamadaFunc!=null)llamadaFunc.validarSemanticamente(ambito, s);
        return OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
