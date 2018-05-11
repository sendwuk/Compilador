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
import Util.Pila;
/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Expresion extends Nodo implements Constantes{
    private int id=R43;
    private Nodo expresion;
    private String parentesisIzq,parentesisDer;
    public Expresion(Pila<ElementoPila>p){
        p.desapila();
        parentesisDer=((Terminal)p.desapila()).getLexema();
        p.desapila();
        expresion=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        parentesisIzq=((Terminal)p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_EXPRESION+NL;
        info+=TAB+"< "+parentesisIzq+" >";
        if(expresion!=null)info+=expresion.getArbol();
        info+="< "+parentesisDer+" >"+NL;
        info+=FIN_EXPRESION+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
