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
public class Accion extends ElementoPila{
private int accion;
public Accion(int accion){
    this.accion=accion;
}
    @Override
    public void mostrar() {
        Util.imprimeln(accion);
    }
}
