/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public interface Constantes {

    public final int ERROR_FATAL = -2;
    public final int ERROR = -1;
    public final int IDENTIFICADOR = 0;
    public final int ENTERO = 1;
    public final int REAL = 2;
    public final int CADENA = 3;
    public final int TIPO_DATO = 4;
    public final int OP_SUMA = 5;
    public final int OP_MUL = 6;
    public final int OP_RELAC = 7;
    public final int OP_OR = 8;
    public final int OP_AND = 9;
    public final int OP_NOT = 10;
    public final int OP_IGUALDAD = 11;
    public final int PUNTO_Y_COMA = 12;
    public final int COMA = 13;
    public final int PARENTESIS_IZQ = 14;
    public final int PARENTESIS_DER = 15;
    public final int LLAVE_IZQ = 16;
    public final int LLAVE_DER = 17;
    public final int IGUAL = 18;
    public final int IF = 19;
    public final int WHILE = 20;
    public final int RETURN = 21;
    public final int ELSE = 22;
    public final int FIN_ENTRADA = 23;
    public final int R1 = 1;
    public final int R2 = 2;
    public final int R3 = 3;
    public final int R4 = 4;
    public final int R5 = 5;
    public final int R6 = 6;
    public final int R7 = 7;
    public final int R8 = 8;
    public final int R9 = 9;
    public final int R10 = 10;
    public final int R11 = 11;
    public final int R12 = 12;
    public final int R13 = 13;
    public final int R14 = 14;
    public final int R15 = 15;
    public final int R16 = 16;
    public final int R17 = 17;
    public final int R18 = 18;
    public final int R19 = 19;
    public final int R20 = 20;
    public final int R21 = 21;
    public final int R22 = 22;
    public final int R23 = 23;
    public final int R24 = 24;
    public final int R25 = 25;
    public final int R26 = 26;
    public final int R27 = 27;
    public final int R28 = 28;
    public final int R29 = 29;
    public final int R30 = 30;
    public final int R31 = 31;
    public final int R32 = 32;
    public final int R33 = 33;
    public final int R34 = 34;
    public final int R35 = 35;
    public final int R36 = 36;
    public final int R37 = 37;
    public final int R38 = 38;
    public final int R39 = 39;
    public final int R40 = 40;
    public final int R41 = 41;
    public final int R42 = 42;
    public final int R43 = 43;
    public final int R44 = 44;
    public final int R45 = 45;
    public final int R46 = 46;
    public final int R47 = 47;
    public final int R48 = 48;
    public final int R49 = 49;
    public final int R50 = 50;
    public final int R51 = 51;
    public final int R52 = 52;
    public final int PROGRAMA = 0;
    public final int DEFINICIONES = 2;
    public final int DEFINICION = 3;
    public final int DEFINICION_R5 = 4;
    public final int DEFINICION_VAR = 5;
    public final int LISTA_VAR = 7;
    public final int DEFINICION_FUNCION = 8;
    public final int PARAMETROS = 10;
    public final int LISTA_PARAMETROS = 12;
    public final int BLOQUE_FUNCION = 13;
    public final int DEF_LOCALES = 15;
    public final int DEF_LOCAL = 16;
    public final int DEF_LOCAL_R18 = 17;
    public final int SENTENCIAS = 19;
    public final int SENTENCIA_R21 = 20;
    public final int SENTENCIA_R22 = 21;
    public final int SENTENCIA_R23 = 22;
    public final int SENTENCIA_R24 = 23;
    public final int SENTENCIA_R25 = 24;
    public final int OTRO = 26;
    public final int BLOQUE = 27;
    public final int VALOR_REGRESA = 29;
    public final int ARGS = 31;
    public final int LISTA_ARGS = 33;
    public final int TERMINO = 34;
    public final int TERMINO_ID = 35;
    public final int TERMINO_ENTERO = 36;
    public final int TERMINO_REAL = 37;
    public final int TERMINO_CADENA = 38;
    public final int LLAMADA_FUNCION = 39;
    public final int BLOQUE_SENTENCIA = 40;
    public final int BLOQUE_SENTENCIA_BLOQUE = 41;
    public final int EXPRESION = 42;
    public final int EXPRESION_SUMA = 43;
    public final int EXPRESION_NOT = 44;
    public final int EXPRESION_MUL = 45;
    public final int EXPRESION_OP_SUMA = 46;
    public final int EXPRESION_RELAC = 47;
    public final int EXPRESION_IGUAL = 48;
    public final int EXPRESION_AND = 49;
    public final int EXPRESION_OR = 50;
    public final int EXPRESION_TERMINAL = 51;
    public final String NOMBRE_ARCHIVO = "compilador.lr";
    public final String FIN_PILA = "$";
    public final String TAB = "\t";
    public final String NL = "\n";
    public final String TITULO_APLICACION = "Compilador BJGL";
    public final String INICIO_PROGRAMA = "<Programa>";
    public final String FIN_PROGRAMA = "</Programa>";
    public final String INICIO_DEFINICIONES = "<Definiciones>";
    public final String FIN_DEFINICIONES = "</Definiciones>";
    public final String INICIO_DEFINICION = "<Definicion>";
    public final String FIN_DEFINICION = "</Definicion>";
    public final String INICIO_DEF_VAR = "<DefVar>";
    public final String FIN_DEF_VAR = "</DefVar>";
    public final String INICIO_LIST_VAR = "<ListaVar>";
    public final String FIN_LIST_VAR = "</ListaVar>";
    public final String INICIO_DEF_FUNC = "<DefFunc>";
    public final String FIN_DEF_FUNC = "</DefFunc>";
    public final String INICIO_PARAMETROS = "<Parametros>";
    public final String FIN_PARAMETROS = "</Parametros>";
    public final String INICIO_LISTA_PARAM = "<ListaParam>";
    public final String FIN_LISTA_PARAM = "</ListaParam>";
    public final String INICIO_BLOQ_FUNC = "<BloqFunc>";
    public final String FIN_BLOQ_FUNC = "</BloqFunc>";
    public final String INICIO_DEF_LOCALES = "<DefLocles>";
    public final String FIN_DEF_LOCALES = "</DefLocales>";
    public final String INICIO_DEF_LOCAL = "<DefLocal>";
    public final String FIN_DEF_LOCAL = "</DefLocal>";
    public final String INICIO_SENTENCIAS = "<Sentencias>";
    public final String FIN_SENTENCIAS = "</Sentencias>";
    public final String INICIO_SENTENCIA = "<Sentencia>";
    public final String FIN_SENTENCIA = "</Sentencia>";
    public final String INICIO_OTRO = "<Otro>";
    public final String FIN_OTRO = "</Otro>";
    public final String INICIO_BLOQUE = "<Bloque>";
    public final String FIN_BLOQUE = "</Bloque>";
    public final String INICIO_VALOR_REGRESA = "<ValorRegresa>";
    public final String FIN_VALOR_REGRESA = "</ValorRegresa>";
    public final String INICIO_ARGUMENTOS = "<Argumentos>";
    public final String FIN_ARGUMENTOS = "</Argumentos>";
    public final String INICIO_LISTA_ARGUMENTOS = "<ListaArgumentos>";
    public final String FIN_LISTA_ARGUMENTOS = "</ListaArgumentos>";
    public final String INICIO_TERMINO = "<Termino>";
    public final String FIN_TERMINO = "</Termino>";
    public final String INICIO_LLAMADA_FUNC = "<LlamadaFunc>";
    public final String FIN_LLAMADA_FUNC = "</LlamadaFunc>";
    public final String INICIO_SENTENCIA_BLOQUE = "<SentenciaBloque>";
    public final String FIN_SENTENCIA_BLOQUE = "</SentenciaBloque>";
    public final String INICIO_EXPRESION = "<Expresion>";
    public final String FIN_EXPRESION = "</Expresion>";
}
