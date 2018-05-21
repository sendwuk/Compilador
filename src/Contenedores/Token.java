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
    public ArrayList<Character> parametros;
    public ArrayList<Character> argumentos;

    public Token() {
        setInfo("sin id", "sin tipo", "sin ambito");
    }

    public Token(String id, String tipo, String ambito) {
        setInfo(id,tipo,ambito);
    }

    public Token(String id, String tipo, String ambito, ArrayList<Character> parametros) {
        setInfo(id,tipo,ambito,parametros);
    }
    public final void setInfo(String id,String tipo,String ambito){
        this.id=id;
        this.tipo=tipo;
        this.ambito=ambito;
    }
    public final void setInfo(String id,String tipo,String ambito,
            ArrayList<Character>parametros){
        setInfo(id,tipo,ambito);
        this.parametros=parametros;
    }

    public ArrayList<Character> getParametros() {
        return parametros;
    }
    public ArrayList<Character> getArgumentos() {
        return argumentos;
    }
    public Character getArgumentoEn(int i) {
        if (i >= 0 && i < getCantParametros()) {
            return parametros.get(i);
        }
        return NOT_OK;
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
    public int getCantArgumentos() {
        return parametros.size();
    }

    public void agregarParametro(char param) {
        parametros.add(param);
    }
    public void agregarArgumentos(char arg){
        argumentos.add(arg);
    }
    @Override 
    public String toString(){
        return "Id "+id+ " |Tipo: "+tipo+" |Ambito: "+ambito;
    }

}
