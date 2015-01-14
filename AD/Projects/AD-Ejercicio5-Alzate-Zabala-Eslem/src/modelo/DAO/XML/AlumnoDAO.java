/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO.XML;

import POJO.Alumno;
import POJO.ListaAlumnos;
import com.thoughtworks.xstream.XStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import modelo.DAO.POJODAO;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author loren
 */
public class AlumnoDAO implements POJODAO {
    ListaAlumnos listadeAlumnos = new ListaAlumnos();

    private ArrayList<Alumno> listaAlumnos = listadeAlumnos.getListaAlumnos();

    // *****************
    // Implementacion de las operacions propias del OperationsDAO
    // *****************
    @Override
    public void add(Object object) {
        listaAlumnos.add((Alumno) object);
    }

    @Override
    public Object find(Object object) {
        Alumno a = (Alumno) object;
        for (int i = 0; i < this.listaAlumnos.size(); i++) {
            if (listaAlumnos.get(i).getId() == a.getId()) {
                return listaAlumnos.get(i);
            }
        }
        return null;
    }

    @Override
    public void update(Object object) {
        int i = 0;
        Alumno a = (Alumno) object;
        boolean enc = false;
        while ((i < this.listaAlumnos.size()) && (enc == false)) {
            if (this.listaAlumnos.get(i).getId() == a.getId()) {
                this.listaAlumnos.set(i, a);
                enc = true;
            } else {
                i++;
            }
        }
    }

    @Override
    public void delete(Object object) {
        Alumno a = (Alumno) object;
        int i = 0;
        while (i < this.listaAlumnos.size()) {
            if (this.listaAlumnos.get(i).getId() == a.getId()) {
                this.listaAlumnos.remove(i);
            } else {
                i++;
            }
        }
    }

    @Override
    public ArrayList list() {
        return listaAlumnos;
    }

    @Override
    public void open() throws Exception {
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();

        // Otra posibilidad hubiera sido la siguiente:
        // XMLReader procesadorXML = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        // Instanciamos la clase que realizará el tratamiento al fichero XML
        GestionContenido gestor = new GestionContenido();
        // Asignamos el objeto creado para tratar los eventos que ocurren dentro del documento
        procesadorXML.setContentHandler(gestor);

        // Creamos una fuente de entrada a partir del fichero XML
        InputSource fileXML = new InputSource("./datos/alumnos.xml");
        // Lanzamos el parseador
        procesadorXML.parse(fileXML);
        listaAlumnos = gestor.getListaAlumnos();
    }

    @Override
    public void close() throws Exception {
        try {
            // instancia un objeto de XStream
            XStream xstream = new XStream();
            //cambiar de nombre a las etiquetas XML
            xstream.alias("listadealumnos", ListaAlumnos.class);		
            xstream.alias("alumno", Alumno.class);
            //quitar etiqueta lista (atributo de la clase ListaPersonas)
            //xstream.addImplicitCollection(ListaAlumnos.class, "lista");
            //Insertar los objetos en el XML
            xstream.toXML(listaAlumnos, new FileOutputStream("./datos/alumnos.xml"));		
            System.out.println("Creado fichero XML....");	
        }catch (Exception e) {
            System.out.println("Error creado fichero XML: "+e.toString());
        }
    }
}
