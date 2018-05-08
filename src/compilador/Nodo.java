/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public abstract class Nodo extends Util {
    public String tipoVar;
    public char tipo;
    public Simbolo simbolo;
    public abstract void mostrar();
    public abstract char validaTipoVar(String tipoVar);
    public abstract int getID();
    
    
    

}
