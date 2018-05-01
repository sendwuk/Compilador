/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import static compilador.Util.imprime;
import static compilador.Util.imprimeLn;
import java.util.ArrayList;

/**
 *
 * @author Gonzalez Luna Bryan Josue Analizador Lexico Centro Universitario de
 * Ciencias Exactas e Ingenierías Universidad de Guadalajara Profesor: Michele
 * Emanuel Lopez Franco Seminario de Solución de Problemas de Traductores de
 * Lenguajes II Definición del lenguaje: 
 * Símbolo      Tipo     ejemplo 
 * Id           0       var 
 * entero       1       5 
 * real         2       5.5 
 * cadena       3       "hola" 
 * tipoDato     4       int,void,float 
 * opSuma       5       +,- 
 * opMul        6       *,/ 
 * opRelac      7         <,<=,>,>= 
 * opOr         8       || 
 * opAnd        9       && 
 * opNot        10      ! 
 * opIgualdad   11    ==,!= 
 * ;            12 
 * ,            13 
 * (            14 
 * )            15 
 * {            16 
 * }            17 
 * if           19 
 * while        20 
 * return       21 
 * else         22 
 * $            23 <--representa fin de la cadena
 */
public class Lexico {
    private int estado, posicion;
    private StringBuilder simbolo;
    private ArrayList<Character> entrada;
    public boolean valido;

   public Lexico(String entrada) {
		int largo=entrada.length();
		this.entrada= new ArrayList<>();
		simbolo= new StringBuilder();
		for(int i=0;i<largo;i++)
			this.entrada.add(entrada.charAt(i));
		valido=true;

	}
	boolean esValido(){
		return valido;
	}

	void error(char c) {
		valido=false;

		System.err.println("Estado actual "+estado+" Error lexico por el caracter: "+c);
	}
	void inicializa(Simbolo s,String lexema,int tipo) {
		s.lexema=lexema;
		s.tipo=tipo;
	}


	public Simbolo sigSimbolo() {
		Simbolo s= new Simbolo();
		char c;
		boolean continuar = true;
		estado = 0;
		simbolo.delete(0,simbolo.length());
		while (continuar && !eof()) {
			c = nextChar();
			switch (estado) {
			case 0:
				//ID
				if (esLetra(c) || c == '_') {
					sigEstado(1, simbolo, c);
				}//Entero
				else if (esDigito(c)) {
					sigEstado(2, simbolo, c);
				} else if (c == '"') {
					sigEstado(5, simbolo, c);
				} else if (esOperadorAdicion(c)) {
					sigEstado(7, simbolo, c);
					continuar = false;
				} else if (esOperadorMul(c)) {
					sigEstado(8, simbolo, c);
					continuar = false;
				} else if (esOperadorRelac(c)) {
					sigEstado(9, simbolo, c);
				} else if (esOperadorOr(c)) {
					sigEstado(11, simbolo, c);
				} else if (esOperadorAnd(c)) {
					sigEstado(13, simbolo, c);
				}else if (c == ';') {
					sigEstado(15, simbolo, c);
				} else if (c == ',') {
					sigEstado(16, simbolo, c);
				} else if (c == '(') {
					sigEstado(17, simbolo, c);
				} else if (c == ')') {
					sigEstado(18, simbolo, c);
				} else if (c == '{') {
					sigEstado(19, simbolo, c);
				} else if (c == '}') {
					sigEstado(20, simbolo, c);					
				}else if (c=='='){
					sigEstado(22,simbolo,c);
				}else  if(esOperadorNot(c)){
					sigEstado(21,simbolo,c);
				}else if (c != ' ' && c != '\n' && c != '\t') {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 1:
				if (esLetra(c) || esDigito(c)) {
					sigEstado(1, simbolo, c);
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 2:
				if (esDigito(c)) {
					sigEstado(2, simbolo, c);
				} else if (c == '.') {
					sigEstado(3, simbolo, c);
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 3:// punto :v
				if (esDigito(c)) {
					sigEstado(4, simbolo, c);
				}else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 4: //real
				if (esDigito(c)) {
					sigEstado(4, simbolo, c);
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 5://cadena
				if (c == '"') {
					sigEstado(6, simbolo, c);
					continuar = false;
				}else if (validChar(c) || c == '.')  {
					sigEstado(5, simbolo, c);

				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 7://suma resta
				if (esOperadorAdicion(c)) {
					sigEstado(7, simbolo, c);
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 8://mul  div
				if (esOperadorMul(c)) {
					sigEstado(8, simbolo, c);
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 9:// < >
				if (esOperadorRelac(c)) {
					retroceso();
					continuar=false;
				}else if(c=='=') {
					sigEstado(9,simbolo,c);
					continuar=false;
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 10://OP asig
				if (c == '=') {
					sigEstado(10, simbolo, c);
					continuar = false;
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 11://Op or
				if (esOperadorOr(c)) {
					sigEstado(12, simbolo, c);
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 12:
				if (esOperadorOr(c)) {
					sigEstado(12, simbolo, c);
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 13://op and
				if (esOperadorAnd(c)) {
					sigEstado(14, simbolo, c);
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 14:
				if (esOperadorAnd(c)) {
					sigEstado(14, simbolo, c);
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 15://Delimitador punto y coma
				if (c == ';') {
					sigEstado(15, simbolo, c);
					continuar = false;
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 16://coma
				if (c == ',') {
					sigEstado(16, simbolo, c);
					continuar = false;
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 17://Parentesis izq
				if (c == '(') {
					sigEstado(17, simbolo, c);
					continuar = false;
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 18://Parentesis der
				if (c == ')') {
					sigEstado(18, simbolo, c);
					continuar = false;
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 19://llave izq
				if (c == '{') {
					sigEstado(19, simbolo, c);
					continuar = false;
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 20: //llave der 
				if (c == '}') {
					sigEstado(20, simbolo, c);
					continuar = false;
				} else if (validChar(c)) {
					retroceso();
					continuar = false;
				} else {
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 21: //operador !
				if(c=='=') {
					sigEstado(22,simbolo,c);
					continuar=false;
				}else if(validChar(c)){
					retroceso();
					continuar=false;
				}else{
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			case 22: //op asig
				if(c=='='){
					sigEstado(22,simbolo,c);
					continuar=false;
				}else if(validChar(c)){
					System.out.println("Valid char");
					sigEstado(21,simbolo,c);
					continuar=false;
				}else{
					sigEstado(-1, simbolo, c);
					continuar = false;
					error(c);
				}
				break;
			}//Fin del automata
		}//while
		if (estado == 0 && eof()) {
			inicializa(s,"$",23);
		} else if (estado > 0) {
			//Inicio del automata de tipos
			switch (estado) {
			case 1://id
				switch (simbolo.toString()) {
				case "void":
				case "int":
				case "float":
					inicializa(s,simbolo.toString(),4);
					break;
				case "if":
					inicializa(s,simbolo.toString(),19);
					break;
				case "while":
					inicializa(s,simbolo.toString(),20);
					break;
				case "return":
					inicializa(s,simbolo.toString(),21);
					break;
                                case "else":
                                    inicializa(s,simbolo.toString(),22);
                                    break;
				default:
					inicializa(s,simbolo.toString(),0);
					break;
				}
				break;
			case 2://entero
				inicializa(s,simbolo.toString(),1);
				break;
			case 4://real
				inicializa(s,simbolo.toString(),2);
				break;
			case 6://cadena
				inicializa(s,simbolo.toString(),3);
				break;
			case 7://suma resta
				inicializa(s,simbolo.toString(),5);
				break;
			case 8://mul div
				inicializa(s,simbolo.toString(),6);
				break;
			case 9:// < >
			case 10:
				inicializa(s,simbolo.toString(),7);
				break;
			case 11:// OP OR
			case 12:
				inicializa(s,simbolo.toString(),8);
				break;
			case 13: //OP AND
			case 14:
				inicializa(s,simbolo.toString(),9);
				break;
			case 15://;
				inicializa(s,simbolo.toString(),12);
				break;
			case 16://,
				inicializa(s,simbolo.toString(),13);
				break;
			case 17://(
				inicializa(s,simbolo.toString(),14);
				break;
			case 18://)
				inicializa(s,simbolo.toString(),15);
				break;
			case 19://{
				inicializa(s,simbolo.toString(),16);
				break;
			case 20:
				inicializa(s,simbolo.toString(),17);
				break;
			case 21://OpNot
				inicializa(s,simbolo.toString(),10);
				break;
			case 22://OpIgualdad
				inicializa(s,simbolo.toString(),11);
				break;
			default:
				inicializa(s,simbolo.toString(),-1);
				break;
			}
		} else {
			inicializa(s,simbolo.toString(),-2);
		}
		return s;
	}

	void sigEstado(int estado, StringBuilder simbolo, char c) {
		this.estado = estado;
		simbolo.append(c);
	}

	char nextChar() {
		return entrada.get(posicion++);
	}

	void retroceso() {
		posicion--;
	}

	boolean eof() {
		return posicion==entrada.size();
	}

	boolean esTipoDato(String c) {
		return "int".equals(c) || "float".equals(c) || "void".equals(c);
	}

	boolean esPalRes(String c) {
		return "if".equals(c) || "while".equals(c) || "return".equals(c);
	}

	boolean validChar(char c) {
		return esDigito(c) || esLetra(c) || c == '_' || c == '(' || c == ')' || c == ';' || c == '+' || c == '*'
				|| c == '-' || c == '/' || c == '=' || c == ' ' || c == '\t' || c == '\n'||
				c=='{'||c=='}'||c=='"'||c=='|'||c=='&'||c=='!';
	}

	boolean esDigito(char c) {
		return '0' <= c && c <= '9';
	}

	boolean esLetra(char c) {
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
	}

	boolean esOperadorAdicion(char c) {
		return c == '+' || c == '-';
	}

	boolean esOperadorMul(char c) {
		return c == '*' || c == '/';
	}

	boolean esOperadorRelac(char c) {
		return c == '<' || c == '>';
	}

	boolean esOperadorOr(char c) {
		return c == '|';
	}

	boolean esOperadorAnd(char c) {
		return c == '&';
	}

	boolean esOperadorNot(char c) {
		return c == '!';
	}

	boolean estaVacio() {
		return entrada.isEmpty();
	}

    boolean esEspacio(char c) {
        return c == 10 || c == 32 || c == 9;
    }
    public String getTipo(int tipo){
        String info;
        switch(tipo){
            case -3: info="Fin de entrada";break;
            case 0: info="Identificador";break;
            case 1: info="Entero";break;
            case 2: info="Real";break;
            case 3: info="Cadena";break;
            case 4: info="TipoDato";break;
            case 5: info="OpSuma";break;
            case 6: info="OpMul";break;
            case 7: info="OpRelac";break;
            case 8: info="OpOr";break;
            case 9: info="OpAnd"; break;
            case 10: info="OpNot";break;
            case 11: info="OpIgualdad";break;
            case 12: info="Delimitador";break;
            case 13: info="Coma";break;
            case 14: info="ParentesisIzq";break;
            case 15: info="ParentesisDer";break;
            case 16: info="LlaveIzq";break;
            case 17: info="LlaveDer";break;
            case 19: info="IF";break;
            case 20: info="While";break;
            case 21: info="Return";break;
            case 22: info="Else";break;
            default: info="Desconocido";break;
        }
    return info;
    }

}
