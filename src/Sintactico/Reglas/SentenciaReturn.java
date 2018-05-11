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
public class SentenciaReturn extends Nodo implements Constantes {
    private int id=R24;
    private Nodo valorRetorno;
    private String miRetorno;
    public SentenciaReturn(Pila<ElementoPila>p){
        p.desapila();
        p.desapila();
        p.desapila();
        valorRetorno=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        miRetorno=((Terminal)p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_SENTENCIA;
        info+=TAB+"< "+miRetorno+" >";
        if(valorRetorno!=null)info+=valorRetorno.getArbol();
        info+=FIN_SENTENCIA+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
