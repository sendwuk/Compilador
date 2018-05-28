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
import Util.AdminArchivo;
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
import generacionCodigo.GeneradoraCodigo;
import java.io.IOException;

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
    private AdminArchivo adminArchivo;
    @FXML
    private Button btnGeneracionCodigo;
    @FXML
    private MenuItem itemMenuSave;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        simbolos = new ArrayList<>();
        salida = new StringBuilder();
        adminArchivo = new AdminArchivo("entrada.lr");
        entradaTxt.setText(adminArchivo.leer());

    }
    private String testGeneracionCodigo()throws IOException{
        imprimeln("*************TEST GENERACION DE CODIGO*****************");
        String entrada= entradaTxt.getText();
        salidaTxt.setText("Analizando entrada...");
        adminArchivo.crear(entrada);
        GeneradoraCodigo g= new GeneradoraCodigo(entrada);
        return (g.analiza())?
                "Código Generado en el archivo salida.asm ":"No aceptado";
    }

    private String testSemantico() throws IOException {
        imprimeln("*************TEST SEMANTICO*****************");
        salidaTxt.setText("Analizando entrada...");
        String entrada = entradaTxt.getText();
        adminArchivo.crear(entrada);
        Semantico s = new Semantico(entrada);
        return (s.analiza()) ? "Aceptado" + NL + s.imprimeSimbolos() : s.imprimeErrores();
    }

    private String testSintactico() throws IOException {
        imprimeln("*************TEST SINTACTICO*****************");
        salidaTxt.setText("Analizando entrada...");
        String entrada = entradaTxt.getText();
        adminArchivo.crear(entrada);
        Sintactico s = new Sintactico(entrada);
        return (s.analiza()) ? "Aceptado" : "No Aceptado";
    }

    private String testSintacticoArbol() throws IOException {
        imprimeln("*************TEST SINTACTICO ÁRBOL*****************");
        salidaTxt.setText("Analizando entrada...");
        Nodo grover;
        String entrada = entradaTxt.getText();
        adminArchivo.crear(entrada);
        Sintactico s = new Sintactico(entrada);
        grover = s.analizaArbol();
        if (grover != null) {
            return grover.getArbol();
        }
        return "No Aceptado";

    }

    private String testLexico() throws IOException {
        String entrada = entradaTxt.getText();
        adminArchivo.crear(entrada);
        Lexico l = new Lexico(entrada);
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
    private void analizaLexico(ActionEvent event) throws IOException {
        salidaTxt.setText(" ");
        String info = testLexico();
        salidaTxt.setText(info);
    }

    @FXML
    private void analizaSintactico(ActionEvent event) throws IOException {
        salidaTxt.setText(null);
        String info = testSintactico();
        salidaTxt.setText(info);
    }

    @FXML
    private void analizaArbolSintactico(ActionEvent event) throws IOException {
        salidaTxt.setText(null);
        String info = testSintacticoArbol();
        salidaTxt.setText(info);
    }

    @FXML
    private void analizaSemantico(ActionEvent event) throws IOException {
        salidaTxt.setText(null);
        String info = testSemantico();
        salidaTxt.setText(info);
    }
      @FXML
    private void analizaGeneracionCodigo(ActionEvent event) throws IOException {
        salidaTxt.setAccessibleHelp(null);
        String info=testGeneracionCodigo();
        salidaTxt.setText(info);
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void mostrarMiInfo(ActionEvent event) {
        String info="Elaborado por. "+NL+"Bryan Josue Gonzalez Luna";
        entradaTxt.setText(info);
    }

    @FXML
    private void guardarEntrada(ActionEvent event) throws IOException {
        adminArchivo.crear(entradaTxt.getText());
    }

    @FXML
    private void eliminarCodigo(ActionEvent event) {
        entradaTxt.setText("");
    }

    @FXML
    private void cargarCodigo(ActionEvent event) {
        entradaTxt.setText(adminArchivo.leer());
    }

  

}
