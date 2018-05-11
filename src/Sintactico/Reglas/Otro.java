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
public class Otro extends Nodo implements Constantes {
    private int id = R27;
    private Nodo sentenciaBloque;
    private String miElse;

    public Otro(Pila<ElementoPila> p) {
        p.desapila();
        sentenciaBloque = ((NoTerminal) p.desapila()).getNodo();
        p.desapila();
        miElse=((Terminal)p.desapila()).getLexema();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getArbol() {
        String info=INICIO_OTRO+NL;
        info+=TAB+"< "+miElse+" >";
        if(sentenciaBloque!=null)info+=sentenciaBloque.getArbol();
        info+=FIN_OTRO+NL;
        return info;
    }

    @Override
    public void validarSemanticamente(String tipoVar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
