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
public class ListaParametros extends Nodo implements Constantes {
private int id=R13;
private String tipoParam,idParam;
private Nodo listaParam;

public ListaParametros(Pila<ElementoPila>p){
    p.desapila();
    listaParam=((NoTerminal)p.desapila()).getNodo();
    p.desapila();
    idParam=((Terminal)p.desapila()).getLexema();
    p.desapila();
    tipoParam=((Terminal)p.desapila()).getLexema();
    p.desapila();
    p.desapila();
}
    @Override
    public String getArbol() {
        String info=INICIO_LISTA_PARAM+NL;
        info+="< "+tipoParam+" >"+" < "+idParam+" >";
        if(listaParam!=null)info+=listaParam.getArbol();
        info+=FIN_LISTA_PARAM+NL;
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
