/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generacionCodigo;

import Contenedores.Token;
import Interfaces.Constantes;
import Interfaces.Nodo;
import Semantico.Semantico;
import Util.AdminArchivo;
import java.util.ArrayList;
/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class GeneradoraCodigo implements Constantes {
    private Semantico semantico;
    private ArrayList<Token>simbolos;
    Nodo arbol;
    
    public GeneradoraCodigo(String entrada){
        semantico = new Semantico(entrada);
        semantico.analiza();
        arbol=semantico.getArbol();
        simbolos=semantico.getTablaSimbolos();
    }
    public String generaCodigo(){
        AdminArchivo file= new AdminArchivo("salida.asm");
        String ss;
        file.crear("");
         ss=".386" + NL;
        ss+=".model flat, stdcall"+NL;
        ss+="option casemap:none"+NL;
        ss+="include C:\\masm32\\macros\\macros.asm"+NL;
        ss+="include C:\\masm32\\include\\masm32.inc"+NL;
        ss+="include C:\\masm32\\include\\kernel32.inc"+NL;
        ss+="include C:\\masm32\\macros\\macros.asm"+NL;
        ss+="includelib C:\\masm32\\lib\\masm32.lib"+NL;
        ss+="includelib C:\\masm32\\lib\\kernel32.lib"+NL;
        ss+=".data"+NL;
        ss+=generaCodigo(simbolos);
        ss+=".code"+NL;
        ss+="inicio:"+NL;
        /*ss+=arbol.generaCodigo();*/
        ss+="exit"+NL;
        ss+="end inicio"+NL;
        file.crear(ss);
        return "Pendiente";
    }
    public String generaCodigo(ArrayList<Token>simbolos){
        String info="";
        for(int i=0,j=simbolos.size();i<j;i++){
            info+=simbolos.get(i).id+" dword 0"+NL;
        }
        return info;
    }
}
