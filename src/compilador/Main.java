/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

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
    /*    Simbolo actual;
     salidaTxt.setText(null);
         Lexico lex= new Lexico(entradaTxt.getText());
           do{
                      actual=lex.sigSimbolo();
              //System.out.println("Lexema: "+actual.lexema);
              //System.out.println("Tipo: "+actual.tipo);
            // simbolos.add(actual);
            if(actual.tipo!=-1)
            salida.append("Simbolo aceptado: ")
                    .append(actual.lexema).append(" tipo: ").append(actual.tipo)
                     .append(System.getProperty("line.separator"));
            
         salidaTxt.setText(salida.toString());
           }while(!"$".equals(actual.lexema));*/
                  Sintactico s= new Sintactico(entradaTxt.getText());
                  s.cargaArchivo();
        
    }
    }
    

