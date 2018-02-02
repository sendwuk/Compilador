/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.List;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 *     Analizador Lexico
     * Gonzalez Luna Bryan Josue 
     * Centro Universitario de Ciencias Exactas e Ingenierías 
     * Universidad de Guadalajara
     * Profesor: Michele Emanuel Lopez Franco
     * Seminario de Solución de Problemas de Traductores de Lenguajes II
     * Definición del lenguaje:
     * Símbolo          Tipo    ejemplo
     * Id               0       var
     * entero           1       5
     * real             2       5.5
     * cadena           3       "hola"
     * tipoDato         4       int,void,float
     * opSuma           5       +,-
     * opMul            6       *,/
     * opRelac          7       <,<=,>,>=
     * opOr             8       ||
     * opAnd            9       &&
     * opNot            10      !
     * opIgualdad       11      ==,!=
     * ;                12
     * ,                13
     * (                14
     * )                15      
     * {                16
     * }                17
     * if               19
     * while            20
     * return           21
         
 */
public class Lexico {
    
     private int estado, posicion;
        private String simbolo;
        private List<Character> entrada;
        public List<Simbolo> simbolos;
        public boolean valido;

        public Lexico(){
           

        }
       
        void error(char c) {
            
        }
            

        Simbolo sigSimbolo(){
            Simbolo s=null;
            char c;
            boolean continuar = true;
            estado = 0;
            simbolo= "";
            while (continuar && eof()){
                c = nextChar();
               switch (estado){
                case 0:
                    //ID
                    if (esLetra(c) || c == '_'){
                        sigEstado(1, simbolo, c);
                    }//Entero
                    else if (esDigito(c)){
                        sigEstado(2, simbolo, c);
                    }
                    else if (c == '"'){
                        sigEstado(5, simbolo, c);

                    }
                    else if (esOperadorAdicion(c)){
                        sigEstado(7, simbolo, c);
                        continuar=false;
                    }
                    else if (esOperadorMul(c)){
                        sigEstado(8, simbolo, c);
                        continuar=false;
                    }
                    else if (esOperadorRelac(c)){
                        sigEstado(9, simbolo, c);
                    }
                    else if (esOperadorOr(c)){
                        sigEstado(11, simbolo, c);
                    }
                    else if (esOperadorAnd(c)){
                        sigEstado(13, simbolo, c);
                    }
                    else if (c == ';'){
                        sigEstado(15, simbolo, c);
                    }
                    else if (c == ','){
                        sigEstado(16, simbolo, c);

                    }
                    else if (c == '('){
                        sigEstado(17, simbolo, c);
                    }
                    else if (c == ')'){
                        sigEstado(18, simbolo, c);

                    }
                    else if (c == '{'){
                        sigEstado(19, simbolo, c);
                    }
                    else if (c == '}'){
                        sigEstado(20, simbolo, c);
                    }
                  else if(c!=' ' && c!='\n' && c!='\t'){
                    sigEstado(-1, simbolo, c);
                    continuar=false;
                    valido=false;
                }
                    break;
                case 1:
                    if(esLetra(c)||c==' '|| esDigito(c)){
                        sigEstado(1,simbolo,c);
                    }
                    else if (validChar(c)){
                        retroceso();
                        continuar=false;
                    }else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                case 2:
                    if(esDigito(c)){
                        sigEstado(2,simbolo,c);
                    }else if(c=='.'){
                        sigEstado(3,simbolo,c);
                    }else if(validChar(c)){
                        retroceso();
                        continuar=false;
                    }else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                case 3:
                    if(esDigito(c)){
                        sigEstado(4,simbolo,c);
                    }else if(validChar(c)){
                        retroceso();
                        continuar=false;
                    }else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                case 4: 
                    if(esDigito(c)){
                        sigEstado(4,simbolo,c);
                    }else if(validChar(c)){
                        retroceso();
                        continuar=false;
                    }
                    else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                        break;
                case 5:
                    if(validChar(c)||c=='.'){
                        sigEstado(5,simbolo,c);
                    }else if(c=='"'){
                        sigEstado(6,simbolo,c);
                        continuar=false;
                    }
                    else{
                    sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                case 7:
                    if(esOperadorAdicion(c)){
                        sigEstado(7,simbolo,c);
                        continuar=false;
                    }else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                case 8:
                    if(esOperadorMul(c)){
                        sigEstado(8,simbolo,c);
                        continuar=false;
                    }else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                case 9:
                    if(esOperadorRelac(c)){
                        sigEstado(10,simbolo,c);
                    }else if(validChar(c)){
                        retroceso();
                        continuar=false;
                    }
                    else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                case 10: 
                    if(c=='='){
                        sigEstado(10,simbolo,c);
                        continuar=false;
                    }else if(validChar(c)){
                        retroceso();
                        continuar=false;
                    }else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                case 11:
                    if(esOperadorOr(c)){
                        sigEstado(12,simbolo,c);
                    }else if(validChar(c)){
                        retroceso();
                        continuar=false;
                    }else{
                        sigEstado(-1,simbolo,c);
                        continuar=false;
                        valido=false;
                    }
                    break;
                default:
                    break;
            }
               
            }
            return s;
        }

        void sigEstado(int estado, String simbolo, char c){
            this.estado = estado;
            simbolo += c;
        }

        char nextChar(){
            return entrada.get(posicion++);
        }
        void retroceso(){
            posicion--;
        }

        boolean eof(){
            return entrada.get(posicion) == -1;
        }

        boolean esTipoDato(String c){
            return "int".equals(c) || "float".equals(c) || "void".equals(c);
        }
        boolean esPalRes(String c){
            return "if".equals(c) || "while".equals(c) || "return".equals(c);
        }

        boolean validChar(char c){
            return esDigito(c) || esLetra(c) || c == '_' || c == '(' || c == ')' || c == ';' || c == '+' || c == '*' ||
                    c == '-' || c == '/' || c == '=' || c == ' ' || c == '\t' || c == '\n';
        }
        boolean esDigito(char c){
            return '0' <= c && c <= '9';
        }
        boolean esLetra(char c){
            return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
        }
        boolean esOperadorAdicion(char c){
            return c == '+' || c == '-';
        }
        boolean esOperadorMul(char c){
            return c == '*' || c == '/';
        }
        boolean esOperadorRelac(char c){
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
