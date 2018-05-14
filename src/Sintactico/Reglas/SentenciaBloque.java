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
public class SentenciaBloque extends Nodo implements Constantes {
    private int id=R41;
    private Nodo sentencia;
    public SentenciaBloque(Pila<ElementoPila>p){
        p.desapila();
        sentencia=((NoTerminal)p.desapila()).getNodo();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_SENTENCIA_BLOQUE+NL;
        if(sentencia!=null)info+=sentencia.getArbol();
        info+=FIN_SENTENCIA_BLOQUE+NL;
     return info;   
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}