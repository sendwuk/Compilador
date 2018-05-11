/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sintactico.Reglas;
import Contenedores.Terminal;
import Interfaces.Constantes;
import Interfaces.ElementoPila;
import Interfaces.Nodo;
import Util.Pila;
/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class TerminoInt extends Nodo implements Constantes {
    private int id=R37;
    private String entero;
    
    public TerminoInt(Pila<ElementoPila>p){
        p.desapila();
        entero=((Terminal)p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_TERMINO+NL;
        info+=TAB+"< "+entero+" >"+NL;
        info+=FIN_TERMINO;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
