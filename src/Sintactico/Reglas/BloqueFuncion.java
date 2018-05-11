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
public class BloqueFuncion extends Nodo implements Constantes {
    private int id=R14;
    private String llaveIzq,llaveDer;
    Nodo defLocales;
    public BloqueFuncion(Pila<ElementoPila>p){
        p.desapila();
        llaveDer=((Terminal)p.desapila()).getLexema();
        p.desapila();
        defLocales=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        llaveIzq=((Terminal)p.desapila()).getLexema();
        
    }
    @Override
    public String getArbol() {
        String info=INICIO_BLOQ_FUNC;
        info+=TAB+"< "+llaveIzq+" >"+NL;
        if(defLocales!=null)info+=defLocales.getArbol();
        info+=TAB+"< "+llaveDer+" >"+NL;
        info+=FIN_BLOQ_FUNC+NL;
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
