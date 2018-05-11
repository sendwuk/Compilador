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
public class DefinicionesLocales extends Nodo implements Constantes {
    private int id=R16;
    private Nodo defLocal,defLocales;
    
    public DefinicionesLocales(Pila<ElementoPila>p){
        p.desapila();
        defLocales=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        defLocal=((NoTerminal)p.desapila()).getNodo();
    }

    @Override
    public String getArbol() {
        String info=INICIO_DEF_LOCALES+NL;
        if(defLocal!=null)info+=defLocal.getArbol();
        if(defLocales!=null)info+=defLocales.getArbol();
        info+=FIN_DEF_LOCALES+NL;
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
