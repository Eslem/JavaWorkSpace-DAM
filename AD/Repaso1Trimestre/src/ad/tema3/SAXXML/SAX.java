/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.tema3.SAXXML;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author eslem
 */
public class SAX {

    public static void main(String[] args) {
        readXML();
    }

    public static void readXML() {
        try {
            XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
            //XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            GestionContenido gestor = new GestionContenido();

            procesadorXML.setContentHandler(gestor);

            String folder = System.getProperty("user.dir");
            
            System.out.println(folder + "\\src\\ad\\tema3\\empleado.xml");

            InputSource fileXML = new InputSource(folder + "\\src\\ad\\tema3\\empleado.xml");
            procesadorXML.parse(fileXML);

            List<Empleado> listaEmpleado = gestor.getList();
            System.out.println(listaEmpleado);

        } catch (SAXException | IOException ex) {
            Logger.getLogger(SAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
