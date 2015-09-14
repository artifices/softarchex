/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.persistencia.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author santi
 */
@Stateless
@LocalBean
public class Log {
    
    public void loguear(String tipo, String concepto) {
        try {

            String registro = Calendar.getInstance().getTime() + " Tipo: " + tipo + " Concepto: " + concepto + "\n";

            String currentDir = new File("").getAbsolutePath();
            System.out.println("" + currentDir);
            File file = new File("C:/logachilles.txt");

            FileWriter fw;
            
            // Si no existe el archivo que se cree, sino se agrega al final
            if (!file.exists()) {
                file.createNewFile();
                fw = new FileWriter(file.getAbsoluteFile());
            } else {
                fw = new FileWriter(file.getAbsoluteFile(),true);
            }

            
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(registro);
            bw.newLine();
            bw.close();

            System.out.println("Se logueo la transacción");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
