/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistabd;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelodatos.Configura;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import util.Constantes;

/**
 * Fichero: Xml.java
 *
 * @date 30-abr-2014
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
public class Xml {

  void grabar(Configura configura)
          throws ParserConfigurationException, TransformerException {

    Element nodoconfigura;
    Document doc = creaDocument("Configuracion");
    doc.setXmlVersion("1.0");
    Element raiz = doc.getDocumentElement();

    nodoconfigura = creaNodo(doc, configura);
    raiz.appendChild(nodoconfigura); //pegamos el elemento a la raiz "Documento"

    // Graba fichero xml
    writeXmlFile(doc, Constantes.CONFIGURAXML);
  }

  public static Document creaDocument(String tagRaiz)
          throws ParserConfigurationException {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    DOMImplementation implementation = builder.getDOMImplementation();
    // Creamos el nuevo documento XML
    Document document;
    document = (Document) implementation.createDocument(null, tagRaiz, null);
    return document;

  }

  private Element creaNodo(Document doc, Configura configura) {

    Element nodoconfigura = doc.createElement("configura"); //creamos un nuevo elemento

    Element fuente = doc.createElement("fuente");
    Text valorfuente = doc.createTextNode(configura.getFuentedatos());
    nodoconfigura.appendChild(fuente);
    fuente.appendChild(valorfuente);

    return nodoconfigura;
  }

  private void writeXmlFile(Document doc, String filename) throws TransformerException {
    try {
      // Prepare the DOM doc for writing
      Source source = new DOMSource(doc);
      // Prepare the output file
      File file = new File(filename);
      Result result = new StreamResult(filename);
      javax.xml.transform.Transformer transformer = TransformerFactory.newInstance().newTransformer();
      //Generar el tranformador para obtener el documento XML en un fichero
      //Insertar saltos de línea al final de cada línea
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      //Añadir 3 espacios delante, en función del nivel de cada nodo
      transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
      transformer.transform(source, result);
    } catch (TransformerConfigurationException e) {
    } catch (TransformerException e) {
    }
  }
}
