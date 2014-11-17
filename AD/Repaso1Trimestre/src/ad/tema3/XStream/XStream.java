/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.tema3.XStream;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eslem
 */
public class XStream {

    public static void main(String[] args) {
        try {
            toXML();
        } catch (IOException ex) {
            Logger.getLogger(XStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(XStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void toXML() throws IOException, ClassNotFoundException {

        String folder = System.getProperty("user.dir");

        //System.out.println(folder + "\\src\\ad\\tema3\\XStream\\empleado.xml");

        File fichero = new File(folder + "\\\\src\\\\ad\\\\tema3\\\\XStream\\\\FichPersona.dat");

        FileInputStream filein = new FileInputStream(fichero);

        ObjectInputStream dataIS = new ObjectInputStream(filein);
        System.out.println("Creando fichero XML...");

        ListaPersonas listaper = new ListaPersonas();

        while (true) {
            Persona persona = null;
            persona = (Persona) dataIS.readObject();
            listaper.add(persona);
        }

        dataIS.close();
        System.out.println(listaper.toString());

    }

}
