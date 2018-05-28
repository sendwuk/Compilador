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
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class GeneradoraCodigo implements Constantes {

    private Semantico semantico;
    private ArrayList<Token> simbolos;
    private boolean valido;
    Nodo arbol;

    public GeneradoraCodigo(String entrada) {
        semantico = new Semantico(entrada);
        valido = semantico.analiza();
        arbol = semantico.getArbol();
        simbolos = semantico.getTablaSimbolos();
    }

    public boolean analiza() throws IOException {
        if (valido) {
            AdminArchivo file = new AdminArchivo("salida.asm");
            file.crear(generaCodigo());
            return true;
        }
        return false;
    }

    public String generaCodigo() throws IOException {
        String ss;
        ss = ".386" + NL;
        ss += ".model flat, stdcall" + NL;
        ss += "option casemap:none" + NL;
        ss += "include C:\\masm32\\macros\\macros.asm" + NL;
        ss += "include C:\\masm32\\include\\masm32.inc" + NL;
        ss += "include C:\\masm32\\include\\kernel32.inc" + NL;
        ss += "include C:\\masm32\\macros\\macros.asm" + NL;
        ss += "includelib C:\\masm32\\lib\\masm32.lib" + NL;
        ss += "includelib C:\\masm32\\lib\\kernel32.lib" + NL;
        ss += ".data" + NL;
        ss += generaCodigo(simbolos);
        ss += ".code" + NL;
        ss += "inicio:" + NL;
        ss += arbol.getCodigoASM();
        ss += imprimeResultados();
        ss += "exit" + NL;
        ss += "end inicio" + NL;
        return ss;
    }

    public String imprimeResultados() {
        String ss = "";
        for (int i = 0; i < simbolos.size(); i++) {
            if(!simbolos.get(i).id.equals("_main")){
            ss += "print chr$(\""
                    + simbolos.get(i).id.substring(1, simbolos.get(i).id.length())
                    + "= \")" + NL;
            ss += "print str$(" + simbolos.get(i).id + ")" + NL;
            ss += "print chr$(13)" + NL;
            ss += "print chr$(10)" + NL;
            }
        }
        return ss;
    }

    public String generaCodigo(ArrayList<Token> simbolos) {
        String info = "";
        for (int i = 0, j = simbolos.size(); i < j; i++) {
            if (!simbolos.get(i).id.equals("_main")) {
                info += simbolos.get(i).id + " dword 0" + NL;
            }
        }
        return info;
    }
}
