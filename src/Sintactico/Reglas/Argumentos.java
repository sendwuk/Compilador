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
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
