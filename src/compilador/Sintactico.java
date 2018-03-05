/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador;

import static compilador.Util.imprime;
import static compilador.Util.imprimeLn;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class Sintactico {
    final String NOMBRE_ARCHIVO="compilador.lr";
    final int FIN_PILA =-50;
    private Stack<Integer> pila;
    private Lexico lex;
    private int fila,columna,accion,cantReglas;
    private boolean aceptacion;
    private ArrayList<String> reglas;
    private ArrayList<Integer> idReglas;
    private ArrayList<Integer> longReglas;
    private ArrayList<ArrayList<Integer>> tablaLr;
    
    public Sintactico(String entrada){
        lex = new Lexico(entrada);
        reglas = new ArrayList();
        idReglas = new ArrayList();
        longReglas = new ArrayList();
        tablaLr = new ArrayList();
        
    }
    public boolean cargaArchivo(){
        File archivo;
        Scanner s;
        String linea;
        int maxReglas;
        try{
            archivo = new File(NOMBRE_ARCHIVO);
            s = new Scanner(archivo);
            /*Manejo del archvio compilador.lr*/
            
                linea=s.nextLine();
                imprimeLn("Linea ledia: "+linea);    
                maxReglas=Integer.parseInt(linea);
                imprime("Max reglas tiene: ");imprimeLn(maxReglas);
                for(int i=0;i<maxReglas;i++){
                    linea=s.nextLine();
                   StringTokenizer st = new StringTokenizer(linea);
                   while(st.hasMoreTokens()){
                       idReglas.add(Integer.parseInt(st.nextToken()));
                       longReglas.add(Integer.parseInt(st.nextToken()));
                       reglas.add(st.nextToken());
                       imprimeLn(idReglas.get(i));
                       imprimeLn(longReglas.get(i));
                       imprimeLn(reglas.get(i));
                   }
                  
                }
                        
            return true;
        }catch(FileNotFoundException e){
            imprimeLn(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<String> getReglas(){
    return reglas;
    }
    public ArrayList<Integer>getIdReglas(){
    return idReglas;
    }
    public ArrayList<Integer> getLongReglas(){
    return longReglas;
    }
    public ArrayList<ArrayList<Integer>>getTablaLr(){
    return tablaLr;
    }
    public void analiza(){
        Simbolo actual;
        actual=lex.sigSimbolo();
    }
    

}
