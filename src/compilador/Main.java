/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

//import static compilador.Util.imprimeLn;
import static compilador.Util.imprime;
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
import static compilador.Util.imprimeln;
import static compilador.Util.imprimeln;
import static compilador.Util.imprimeln;

/**
 *
 * @author Bryan Josue Gonzalez Luna
 */
public class Main implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        simbolos = new ArrayList<>();
        salida = new StringBuilder();

    }

  

    private String testSintactico() {
        imprimeln("*************TEST SINTACTICO*****************");
        salidaTxt.setText("Analizando entrada...");
        Sintactico s = new Sintactico(entradaTxt.getText());        
        return (s.analiza()) ? "Aceptado" : "No Aceptado";
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
        return (l.valido)?"Aceptado":"No aceptado";
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
    private void cerrarVentana(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void mostrarMiInfo(ActionEvent event) {
    }
}
