/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

//import static compilador.Util.imprimeLn;
import static compilador.Util.imprime;
import static compilador.Util.imprimeLn;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        simbolos = new ArrayList<>();
        salida = new StringBuilder();

    }

  

    private String testSintactico() {
        imprimeLn("*************TEST SINTACTICO*****************");
        Sintactico s = new Sintactico(entradaTxt.getText());        
        return (s.analiza()) ? "Aceptado" : "No Aceptado";
    }

    private String testLexico() {
        Lexico l = new Lexico(entradaTxt.getText());
        Simbolo actual;
        do {
            imprimeLn("*************TEST LEXICO*****************");
            actual = l.sigSimbolo();
            imprime("Simbolo leido: ");
            imprimeLn(actual.lexema);
            imprime("Tipo con numero: ");
            imprimeLn(actual.tipo);
            imprime("Tipo con letra: ");
            imprimeLn(l.getTipo(actual.tipo));
        } while (!"$".equals(actual.lexema));
        return (l.valido)?"Aceptado":"No aceptado";
    }

    @FXML
    private void analizaLexico(ActionEvent event) {
           salidaTxt.setText(null);
     String info = testLexico();
     salidaTxt.setText(info);
    }

    @FXML
    private void analizaSintactico(ActionEvent event) {
           salidaTxt.setText(null);
     String info = testSintactico();
     salidaTxt.setText(info);
    }
}
