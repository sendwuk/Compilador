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
public class DefinicionFuncion extends Nodo implements Constantes {
    private int id=R9;
    private String idFunc,tipoFunc,parentesisIzq,parentesisDer;
    Nodo parametro,bloqueFunc;
    public DefinicionFuncion(Pila<ElementoPila>p){
        p.desapila();
        bloqueFunc=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        parentesisDer=((Terminal)p.desapila()).getLexema();
        p.desapila();
        parametro=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        parentesisIzq=((Terminal)p.desapila()).getLexema();
        p.desapila();
        idFunc=((Terminal)p.desapila()).getLexema();
        p.desapila();
        tipoFunc=((Terminal)p.desapila()).getLexema();
        
        
    }

    @Override
    public String getArbol() {
        String info=INICIO_DEF_FUNC+NL;
        info+=TAB+"< "+tipoFunc+" >"+"< "+parentesisIzq
                +" >";
        if(parametro!=null)info+=parametro.getArbol();
        info+=" <"+parentesisDer+" >"+NL;
        if(bloqueFunc!=null)info+=bloqueFunc.getArbol();
        info+=FIN_DEF_FUNC+NL;
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
