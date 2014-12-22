/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package leerpersonasxmlserializado;

import POJO.Persona;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.thoughtworks.xstream.XStream;

public class LeerPersonasXMLSerializado {
    public static void main(String[] args) throws IOException {   	

        // Instanciamos el objeto de xstream
        XStream xstream = new XStream();

        // Necesitamos volver a indicar lo que utilizamos para crear la lista
        xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);		
        xstream.alias("DatosPersona", Persona.class);
        xstream.addImplicitCollection(ListaPersonas.class, "lista");

        // Obtenemos la lista de personas a partir del fichero xml
        ListaPersonas listadoTodas = (ListaPersonas) xstream.fromXML(new FileInputStream("Personas.xml"));			
        System.out.println("Numero de Personas: " + listadoTodas.getListaPersonas().size());

        // Creamos un arraylist y obtenemos la lista de personas en forma de arraylist
        List<Persona> listaPersonas = new ArrayList<Persona>();
        listaPersonas = listadoTodas.getListaPersonas();

        //se solicita a la lista que un iterador con todos los el elementos contenidos en ella
        Iterator iterador = listaPersonas.listIterator();
        while( iterador.hasNext() ) {
            //Se obtiene el siguiente elemento de la lista
            Persona p = (Persona) iterador.next();  
            System.out.println("Nombre: " + p.getNombre() + 
                           ", edad: " + p.getEdad());        
        }    
        System.out.println("Fin de listado .....");
    } //fin main
}//fin LeerPersonas 
