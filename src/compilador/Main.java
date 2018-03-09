/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

//import static compilador.Util.imprimeLn;
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
    @FXML
    private Button btnCompilar;
    private ArrayList<Simbolo>simbolos;
    private StringBuilder salida;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        simbolos=new ArrayList<>();
        salida= new StringBuilder();
       
    }
    @FXML
    private void analiza(ActionEvent event) {
     salidaTxt.setText(null);
                  Sintactico s= new Sintactico(entradaTxt.getText());
                  if(s.analiza())
                  salidaTxt.setText("Análisis Sintáctico sin errores\n");
                  else salidaTxt.setText(" Error sintáctico\n");
         
    }
    }
    

