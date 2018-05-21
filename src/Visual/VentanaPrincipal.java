package Visual;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import static compilador.Util.imprimeLn;
import Lexico.Lexico;
import Contenedores.Simbolo;
import Interfaces.Constantes;
import Interfaces.Nodo;
import Semantico.Semantico;
import Sintactico.Sintactico;
import static Util.Util.imprime;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import static Util.Util.imprimeln;
import static Util.Util.imprimeln;
import static Util.Util.imprimeln;

/**
 *
 * @author Bryan Josue Gonzalez Luna
 */
public class VentanaPrincipal implements Initializable, Constantes {

    @FXML
    private AnchorPane lienzoPrincipal;
    @FXML
    private TextArea entradaTxt;
    @FXML
    private TextArea salidaTxt;
    private ArrayList<Simbolo> simbolos;
    private StringBuilder salida;
    @FXML
    private Button btnAnalizaLexico;
    @FXML
    private Button btnAnalizaSintactico;
    @FXML
    private MenuItem btnClose;
    @FXML
    private MenuItem btnAbout;
    @FXML
    private Button btnArbol;
    @FXML
    private Button btnSemantico;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        simbolos = new ArrayList<>();
        salida = new StringBuilder();

    }

    private String testSemantico() {
        imprimeln("*************TEST SEMANTICO*****************");
        salidaTxt.setText("Analizando entrada...");
        Semantico s = new Semantico(entradaTxt.getText());
        return (s.analiza()) ? "Aceptado"+NL+s.imprimeSimbolos() : s.imprimeErrores();
    }

    private String testSintactico() {
        imprimeln("*************TEST SINTACTICO*****************");
        salidaTxt.setText("Analizando entrada...");
        Sintactico s = new Sintactico(entradaTxt.getText());
        return (s.analiza()) ? "Aceptado" : "No Aceptado";
    }

    private String testSintacticoArbol() {
        imprimeln("*************TEST SINTACTICO ÁRBOL*****************");
        salidaTxt.setText("Analizando entrada...");
        Nodo grover;
        Sintactico s = new Sintactico(entradaTxt.getText());
        grover = s.analizaArbol();
        if (grover != null) {
            return grover.getArbol();
        }
        return "No Aceptado";

    }

    private String testLexico() {
        Lexico l = new Lexico(entradaTxt.getText());
        Simbolo actual;
        do {
            imprimeln("*************TEST LEXICO*****************");
            actual = l.sigSimbolo();
            imprime("Simbolo leido: ");
            imprimeln(actual.lexema);
            imprime("Tipo con numero: ");
            imprimeln(actual.tipo);
            imprime("Tipo con letra: ");
            imprimeln(l.getTipo(actual.tipo));
        } while (!"$".equals(actual.lexema));
        return (l.valido) ? "Aceptado" : "No aceptado";
    }

    @FXML
    private void analizaLexico(ActionEvent event) {
        salidaTxt.setText(" ");
        String info = testLexico();
        salidaTxt.setText(info);
    }

    @FXML
    private void analizaSintactico(ActionEvent event) {
        salidaTxt.setText(null);
        String info = testSintactico();
        salidaTxt.setText(info);
    }

    @FXML
    private void analizaArbolSintactico(ActionEvent event) {
        salidaTxt.setText(null);
        String info = testSintacticoArbol();
        salidaTxt.setText(info);
    }

    @FXML
    private void analizaSemantico(ActionEvent event) {
        salidaTxt.setText(null);
        String info = testSemantico();
        salidaTxt.setText(info);
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void mostrarMiInfo(ActionEvent event) {

    }

}
