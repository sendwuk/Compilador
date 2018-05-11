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
import Util.Pila;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Sentencias extends Nodo implements Constantes {
    private int id=R20;
    private Nodo sentencia,sentencias;
    public Sentencias(Pila<ElementoPila>p){
        p.desapila();
        sentencias=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        sentencia=((NoTerminal)p.desapila()).getNodo();
    }

    @Override
    public String getArbol() {
        String info=INICIO_SENTENCIAS+NL;
        if(sentencias!=null)info+=sentencias.getArbol();
        if(sentencia!=null)info+=sentencia.getArbol();
        info+=FIN_SENTENCIAS+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getID() {
        return id;
    }
    
    

}
