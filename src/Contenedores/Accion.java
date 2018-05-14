/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contenedores;

import Interfaces.ElementoPila;
import Util.Util;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Accion extends ElementoPila {

    private int accion;

    public Accion(int accion) {
        this.accion = accion;
    }

    @Override
    public void mostrar() {
        Util.imprimeln(accion);
    }

    public int getAccion() {
        return accion;
    }
    @Override
    public String toString(){
        return String.valueOf(accion);
    }
}
