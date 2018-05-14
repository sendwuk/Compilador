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
public class ExpresionOpMul extends Nodo implements Constantes {
    private int id=R46;
    private Nodo exp1,exp2;
    private String opMul;
    public ExpresionOpMul(Pila<ElementoPila>p){
        p.desapila();
        exp2=((NoTerminal)p.desapila()).getNodo();
        p.desapila();
        opMul=((Terminal)p.desapila()).getLexema();
        p.desapila();
        exp1=((NoTerminal)p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_EXPRESION+NL;
        if(exp1!=null)info+=exp1.getArbol();
        info+=TAB+"< "+opMul+" >"+NL;
        if(exp2!=null)info+=exp2.getArbol();
        info+=FIN_EXPRESION+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}