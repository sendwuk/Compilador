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
public class DefinicionLocal extends Nodo implements Constantes{
    private int id=R17;
    private Nodo defVar;
    public DefinicionLocal(Pila<ElementoPila>p){
        p.desapila();
        defVar=((NoTerminal)p.desapila()).getNodo();
    }
    
    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_DEF_LOCAL+NL;
        if(defVar!=null)info+=defVar.getArbol();
        info+=FIN_DEF_LOCAL+NL;
        return info;
    }

    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
       if(defVar!=null)defVar.validarSemanticamente(ambito, s);
       return OK;
    }

    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
