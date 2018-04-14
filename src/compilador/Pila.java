/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador;

import java.util.Stack;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 * @param <Objeto>
 */
public class Pila<Objeto> {
    Stack<Objeto> pila;
    Pila(){
        pila= new Stack<>();
    }
    public Objeto tope(){
        Objeto extraido=pila.peek();
        return extraido;
    }
    public void apila(Objeto o){
    pila.push(o);
    }
    public Objeto desapila(){
        Objeto extraido=pila.peek();
        pila.pop();
        return extraido;
    }

}
