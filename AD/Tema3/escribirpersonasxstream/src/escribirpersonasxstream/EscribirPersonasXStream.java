/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package escribirpersonasxstream;

import POJO.Persona;
import java.io.*;
import com.thoughtworks.xstream.XStream;

public class EscribirPersonasXStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Usamos el fichero generado en prácticas anteriores
        File fichero = new File("FichPersona.dat");
        // Creamos el flujo de entrada
        FileInputStream filein = new FileInputStream(fichero);   

        //conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataIS = new ObjectInputStream(filein);      
        System.out.println("Comienza el proceso de creación del fichero a XML ...");

        //Creamos un objeto Lista de Personas
        ListaPersonas listaper = new ListaPersonas();	 

        try {
            while (true) { //lectura del fichero
                Persona persona= (Persona) dataIS.readObject(); //leer una Persona     
                listaper.add(persona); //añadir persona a la lista  
            }	
        }catch (EOFException eo) {
              // No hace nada, ya que ha terminado de recorrer 
        }

        //cerrar stream de entrada
        dataIS.close();       

        try {
            // instancia un objeto de XStream
            XStream xstream = new XStream();
            //cambiar de nombre a las etiquetas XML
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);		
            xstream.alias("DatosPersona", Persona.class);
            //quitar etiqueta lista (atributo de la clase ListaPersonas)
            xstream.addImplicitCollection(ListaPersonas.class, "lista");
            //Insertar los objetos en el XML
            xstream.toXML(listaper, new FileOutputStream("Personas.xml"));		
            System.out.println("Creado fichero XML....");
        }catch (Exception e) {
            e.printStackTrace();
        }	    
    } // fin main
} //fin EscribirPersonas