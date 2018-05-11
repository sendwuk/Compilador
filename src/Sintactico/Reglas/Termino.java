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
public class Termino extends Nodo implements Constantes {
    private int id=R35;
    private Nodo llamadaFunc;
    
    public Termino(Pila<ElementoPila>p){
        p.desapila();
        llamadaFunc=((NoTerminal)p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_TERMINO+NL;
        if(llamadaFunc!=null)info+=llamadaFunc.getArbol();
        info+=FIN_TERMINO+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
