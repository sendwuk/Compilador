/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Interfaces.Constantes;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalez Luna Bryan Josue
 */
public class AdminArchivo implements Constantes {

    private String nombreArchivo;

    public AdminArchivo() {
        this("Sin nombre.txt");
    }

    public AdminArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void crear(String info) throws IOException {
        try {
            File file = new File(nombreArchivo);
            file.delete();
            file = new File(nombreArchivo);
            try (FileWriter fw = new FileWriter(file, true)) {
                fw.write(info);
                fw.close();
            }

        } catch (IOException ex) {
            Logger.
                    getLogger(AdminArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String leer() {
        String info = "", linea;
        try {
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                info += linea + NL;
            }
            br.close();
        } catch (IOException ex) {
            Logger.
                    getLogger(AdminArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }

}
