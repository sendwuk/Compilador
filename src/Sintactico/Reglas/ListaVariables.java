/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sintactico.Reglas;

import Util.Pila;
import Interfaces.Nodo;
import Interfaces.Constantes;
import Interfaces.ElementoPila;
import Contenedores.NoTerminal;
import Contenedores.Terminal;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class ListaVariables extends Nodo implements Constantes {
	private int id=R8;
	private Nodo listaVariables;
	private String idVar;
	public ListaVariables(Pila<ElementoPila>p){
		p.desapila();
		listaVariables =((NoTerminal)p.desapila()).getNodo();
		p.desapila();
		idVar=((Terminal)p.desapila()).getLexema();
		p.desapila();
		p.desapila();
	}

	@Override
	public String getArbol() {
		String info=INICIO_LIST_VAR+NL;
		info+=TAB+" < "+id+" >"+NL;
		if(listaVariables!=null)info+=listaVariables.getArbol();
		info+=FIN_DEF_VAR+NL;
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
	public String getIdVar() {
		return idVar;
	}
	
}