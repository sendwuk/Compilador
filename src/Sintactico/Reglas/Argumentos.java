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
public class Argumentos extends Nodo implements Constantes {
    private int id=R32;
    private Nodo expresion,listaArgumentos;
    public Argumentos(Pila<ElementoPila>p){
        p.desapila();
        expresion=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        listaArgumentos=((NoTerminal)p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_ARGUMENTOS+NL;
        if(expresion!=null)info+=expresion.getArbol();
        if(listaArgumentos!=null)info+=listaArgumentos.getArbol();
        info+=FIN_ARGUMENTOS+NL;
       return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
     char tipoExp=' ';
     if(expresion!=null)tipoExp=expresion.validarSemanticamente(ambito, s);
     tokenGlobalAux.agregarParametro(tipoExp);
     if(listaArgumentos!=null)listaArgumentos.validarSemanticamente(ambito, s);
     return (tipoExp!=' ')?tipoExp:NOT_OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
