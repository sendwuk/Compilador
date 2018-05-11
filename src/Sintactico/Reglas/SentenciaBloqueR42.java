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
public class SentenciaBloqueR42 extends Nodo implements Constantes {
    private int id=R42;
    private Nodo bloque;
    public SentenciaBloqueR42(Pila<ElementoPila>p){
        p.desapila();
        bloque=((NoTerminal)p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_SENTENCIA_BLOQUE+NL;
        if(bloque!=null)info+=bloque.getArbol();
        info+=FIN_SENTENCIA_BLOQUE+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
