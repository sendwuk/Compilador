/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contenedores;

import Interfaces.Constantes;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Error implements Constantes {

    public int id;
    public String mensaje;

    public Error(int id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "ID error: " + id + " " + mensaje;
    }

}
