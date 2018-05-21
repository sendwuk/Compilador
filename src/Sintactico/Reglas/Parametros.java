/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sintactico.Reglas;

import Contenedores.NoTerminal;
import Contenedores.Terminal;
import Contenedores.Token;
import Interfaces.Constantes;
import Interfaces.ElementoPila;
import Interfaces.Nodo;
import Semantico.Semantico;
import Util.Pila;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Parametros extends Nodo implements Constantes {
    private int id=R11;
    private String tipoParam,idParam;
    Nodo listaParam;
    public Parametros(Pila<ElementoPila>p){
        p.desapila();
        listaParam=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        idParam=((Terminal)p.desapila()).getLexema();
        p.desapila();
        tipoParam=((Terminal)p.desapila()).getLexema();
    }
    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_PARAMETROS+NL;
        info+="< "+tipoParam+" >"+" < "+idParam+" >";
        if(listaParam!=null)info+=listaParam.getArbol();
        info+=FIN_PARAMETROS+NL;
        return info;
    }
    @Override
    public char validarSemanticamente(String ambito, Semantico s) {
        if(s.existeVar(idParam, ambito)){
            s.insertarError(ERROR_PARAMETRO_REDEFINIDO, idParam);
        }else{
            tokenGlobalAux= new Token(idParam,tipoParam,ambito);
            tokenGlobalAux.agregarParametro(tipoParam.charAt(0));
            s.insertarToken(tokenGlobalAux);
        }
        if(listaParam!=null){
            listaParam.validarSemanticamente(tipoParam,ambito, s);
        }
        return OK;
    }
    @Override
    public void validarSemanticamente(String tipo, String ambito, Semantico s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
