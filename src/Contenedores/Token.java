/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contenedores;

import Interfaces.Constantes;
import java.util.ArrayList;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Token implements Constantes {
    
    public String id;
    public String tipo;
    public String ambito;
    public ArrayList<Character> parametros = new ArrayList<>();

    public Token() {
        this("sin id", "sin tipo", "sin ambito");
    }

    public Token(String id, String tipo, String ambito) {
        this.id = id;
        this.tipo = tipo;
        this.ambito = ambito;
    }

    public Token(String id, String tipo, String ambito, ArrayList<Character> parametros) {
        this(id, tipo, ambito);
        this.parametros = parametros;
    }

    public ArrayList<Character> getParametros() {
        return parametros;
    }

    public Character getParametroEn(int i) {
        if (i >= 0 && i < getCantParametros()) {
            return parametros.get(i);
        }
        return NOT_OK;
    }

    public int getCantParametros() {
        return parametros.size();
    }

    public void agregarParametro(char param) {
        parametros.add(param);
    }
    
}
