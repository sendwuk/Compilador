/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.ArrayList;


/**
 *
 * @author 
 * Gonzalez Luna Bryan Josue 
 * Analizador Lexico 
 * Centro Universitario de Ciencias Exactas e Ingenierías 
 * Universidad de Guadalajara 
 * Profesor: Michele Emanuel Lopez Franco 
 * Seminario de Solución de Problemas de Traductores de Lenguajes II 
 * Definición del lenguaje: 
 * Símbolo  Tipo ejemplo 
 * Id           0       var 
 * entero       1       5 
 * real         2       5.5 
 * cadena       3       "hola" 
 * tipoDato     4       int,void,float 
 * opSuma       5       +,- 
 * opMul        6       *,/ 
 * opRelac      7       <,<=,>,>= 
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
 * $            -3  <-- representa fin de la cadena
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
        System.out.println( "Error lexico por el caracter: "+c);
    }
   

    Simbolo sigSimbolo() {
        Simbolo s = new Simbolo();
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
                    }else if(esOperadorNot(c)){
                        sigEstado(21,simbolo,c);
                    }else if(c=='='||c=='!'){
                        sigEstado(22,simbolo,c);
                    }else if (c != ' ' && c != '\n' && c != '\t') {
                        sigEstado(-1, simbolo, c);
                        continuar = false;
                        error(c);
                    }
                    break;
                case 1:
                    if (esLetra(c) || c == ' ' || esDigito(c)) {
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
                    if (validChar(c) || c == '.') {
                        sigEstado(5, simbolo, c);
                    } else if (c == '"') {
                        sigEstado(6, simbolo, c);
                        continuar = false;
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
                        sigEstado(10, simbolo, c);
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
                    if(validChar(c)){
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
                        retroceso();
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
                s.lexema = "$";
                s.tipo = -3;
            } else if (estado > 0) {
                //Inicio del automata de tipos
            switch (estado) {
                case 1://id
            switch (simbolo.toString()) {
                case "void":
                case "int":
                case "float":
                    s.lexema=simbolo.toString();
                    s.tipo=4;
                    break;
                case "if":
                    s.lexema=simbolo.toString();
                    s.tipo=19;
                    break;
                case "while":
                   s.lexema=simbolo.toString();
                    s.tipo=20;
                    break;
                case "return":
                    s.lexema=simbolo.toString();
                    s.tipo=21;
                    break;
                default:
                    s.lexema=simbolo.toString();
                    s.tipo=0;
                    break;
            }
                    break;
                case 2://entero
                    s.lexema=simbolo.toString();
                    s.tipo=1;
                    break;
                case 4://real
                    s.lexema=simbolo.toString();
                    s.tipo=2;
                    break;
                case 6://cadena
                    s.lexema=simbolo.toString();
                    s.tipo=3;
                    break;
                case 7://suma resta
                    s.lexema=simbolo.toString();
                    s.tipo=5;
                    break;
                case 8://mul div
                    s.lexema=simbolo.toString();
                    s.tipo=6;
                    break;
                case 9:// < >
                case 10:
                    s.lexema=simbolo.toString();
                    s.tipo=7;
                    break;
                case 11:// OP OR
                case 12:
                    s.lexema=simbolo.toString();
                    s.tipo=8;
                    break;
                case 13: //OP AND
                case 14:
                    s.lexema=simbolo.toString();
                    s.tipo=9;
                    break;
                case 15://;
                    s.lexema=simbolo.toString();
                    s.tipo=12;
                    break;
                case 16://,
                    s.lexema=simbolo.toString();
                    s.tipo=13;
                    break;
                case 17://(
                    s.lexema=simbolo.toString();
                    s.tipo=14;
                    break;
                case 18://)
                    s.lexema=simbolo.toString();
                    s.tipo=15;
                    break;
                case 19://{
                    s.lexema=simbolo.toString();
                    s.tipo=16;
                    break;
                case 20:
                    s.lexema=simbolo.toString();
                    s.tipo=17;
                    break;
                default:
                    s.lexema=simbolo.toString();
                    s.tipo=-1;
                    break;
            }
            } else {
                s.lexema="Error Fatal";
                    s.tipo=-2;
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
                || c == '-' || c == '/' || c == '=' || c == ' ' || c == '\t' || c == '\n';
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

}
