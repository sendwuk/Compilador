/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sintactico.Reglas;
import Contenedores.Terminal;
import Contenedores.NoTerminal;
import Interfaces.Constantes;
import Interfaces.ElementoPila;
import Interfaces.Nodo;
import Util.Pila;
/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class LlamadaFunc extends Nodo implements Constantes {
    private int id=R40;
    private String parentesisIzq,parentesisDer,identificador;
    private Nodo argumento;
    
    public LlamadaFunc(Pila<ElementoPila>p){
        p.desapila();
        parentesisDer=((Terminal)p.desapila()).getLexema();
        p.desapila();
        argumento=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        parentesisIzq=((Terminal)p.desapila()).getLexema();
        p.desapila();
        identificador=((Terminal)p.desapila()).getLexema();
    }
    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_LLAMADA_FUNC+NL;
        info+=TAB+"< "+identificador+" >";
        info+=" < "+parentesisIzq+" >";
        if(argumento!=null)info+=argumento.getArbol();
        info+=" < "+parentesisDer+" >"+NL;
        info+=FIN_LLAMADA_FUNC+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}