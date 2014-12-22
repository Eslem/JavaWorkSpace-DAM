/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crearficheroempleadoxml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import org.apache.xml.serializer.OutputPropertiesFactory;

public class CrearFicheroEmpleadoXML {

    public static void main(String argv[]) throws IOException{
        File fichero = new File("AleatorioEmple.dat");   
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int  id, dep, posicion=0; //para situarnos al principio del fichero        
        Double salario;
        char apellido[] = new char[10], aux;

        // Creacion de la factoria que nos proporcia el parseador (creador de parseadores)
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
           // Creacion del parseador
           DocumentBuilder builder = factory.newDocumentBuilder();

           //  Contenedor de documentos
           DOMImplementation implementation = builder.getDOMImplementation();
           // Creamos un documento vacio con el nombre document.
           // Asignamos al nodo raiz el nombre "Empleados"
           Document document = implementation.createDocument(null, "Empleados", null);
           // Asignamos la version del XML
           document.setXmlVersion("1.0");

           // Va a realizar un recorrido mientras no llegue al final
           for(;;) {
                   file.seek(posicion); //nos posicionamos 
                   id=file.readInt(); // obtengo id de empleado	  	  
                   for (int i = 0; i < apellido.length; i++) {
                           aux = file.readChar();//recorro uno a uno los caracteres del apellido 
                           apellido[i] = aux;    //los voy guardando en el array
                   }   
                   String apellidoS= new String(apellido);//convierto a String el array
                   dep=file.readInt();//obtengo dep
                   salario=file.readDouble();  //obtengo salario	  

                   //id validos a partir de 1. No vale para nada ya que empieza por 1.
                   if(id>0) {
                           // Crea un nodo empleado. Sustituimos el nombre raiz por empleado 
                           Element empleado = document.createElement("empleado"); //nodo empleado
                           // Añade el nodo empleado creado al documento
                           document.getDocumentElement().appendChild(empleado);
                           // Añadimos los hijos de ese nodo mediante la funcion CrearElemento
                           CrearElemento("id",Integer.toString(id), empleado, document); //añadir ID
                           CrearElemento("apellido",apellidoS.trim(), empleado, document); //Apellido 
                           CrearElemento("dep",Integer.toString(dep), empleado, document); //añadir DEP
                           CrearElemento("salario",Double.toString(salario), empleado, document); //SAL 
                   }
                   // Nos posicionamos de nuevo
                   posicion= posicion + 36; // me posiciono para el sig empleado
                   // Si esta al final sale del bucle. Esta ruptura no me gusta nada.
                   if (file.getFilePointer() == file.length()){
                           break; 
                   }
           }

           // Creamos una fuente XML para almacenar a partir el documento
           Source source = new DOMSource(document);
           // Se crea el resultado en el fichero Empleados.xml
           Result result = new StreamResult(new java.io.File("Empleados.xml"));
           // La clase javax.xml.transform.Transformer implementa un motor para realizar transformaciones a documentos XML.
           // Los ejemplares se obtienen de Factorías. Es lo mismo que la factoria 
           // de arriba, pero lo obtiene directamente.
           Transformer transformer = TransformerFactory.newInstance().newTransformer();
           //Insertar saltos de línea al final de cada línea
           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
           //Añadir 3 espacios delante, en función del nivel de cada nodo
           transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");	   
           // Se dispone del método transform(fuente, destino). 
           // Ambas interfaces (Source y Result) disponen de implementaciones 
           // para representar a un documento XML basado en DOM, SAX o en un flujo de datos (Stream) genérico.
           transformer.transform(source, result);
           // La salida se escribe directamente en un flujo de datos.
           // Se pueden construir a partir de un objeto de tipo File, de un OutputStream (flujo de datos binarios) 
           // o un Writer (escritor de caracteres).
           // Mostramos el resultado por consola.
           Result console= new StreamResult(System.out);
           transformer.transform(source, console);

        }catch(Exception e){
            System.err.println("Error: " + e);
        }
        // cerrar fichero
        file.close();  	
     }
 
    //Inserción de los datos del empleado
    static void  CrearElemento(String datoEmple, String valor,
                            Element raiz, Document document){	 
         // La funcion recibe el nombre del nodo hijo y sus textos o valores , el nodo al que se va a añadir (raiz)
         // y el documento.	 
         // Crea el nuevo hijo en el documento que puede ser id, apellido, dep y salario.
         Element elem = document.createElement(datoEmple); 
         // Asigna el valor al hijo
         Text text = document.createTextNode(valor);
         // pegamos el elemento hijo a la raiz (o sea pegamos id, apellido, dep y salario a empleado.
         raiz.appendChild(elem); 
         // pegamos el valor al elemento	
         elem.appendChild(text); 	
    }
}
