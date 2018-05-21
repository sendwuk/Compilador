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
public class Bloque extends Nodo implements Constantes {
    private int id=R28;
    private Nodo sentencia;
    private String parentesisIzq,parentesisDer;
    public Bloque(Pila<ElementoPila>p){
        p.desapila();
        parentesisDer=((Terminal)p.desapila()).getLexema();
        p.desapila();
        sentencia=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        parentesisIzq=((Terminal)p.desapila()).getLexema();
    }
    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_BLOQUE+NL;
        info+=TAB+"< "+parentesisIzq+" >"+NL;
        if(sentencia!=null)info+=sentencia.getArbol();
        info+="< "+parentesisDer+" >"+NL;
        info+="</Bloque>"+NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        if(sentencia!=null)sentencia.validarSemanticamente(ambito, s);
        return OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
