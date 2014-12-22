package lecturaempleadoxml;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LecturaEmpleadoXml {
    public static void main(String argv[]) {
        // Construccion de la factoria	 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
              // Creacion del parser
              DocumentBuilder builder = factory.newDocumentBuilder();
              // Cargamos el documento mediante el metodo parser
              Document document = builder.parse(new File("Empleados.xml"));
              // Normalizar el árbol. Esto significa combinar nodos textuales 
              // que estaban en varias líneas y eliminar nodos vacíos textuales.
              // Nos posicionamos en el nodo principal del árbol con getDocumentElement()
              document.getDocumentElement().normalize();

              // Nos posicionamos en el nodo principal del árbol con getDocumentElement()
              // y del mismo el nombre del nodo, que será el nodo raiz.
              System.out.println("Elemento raíz: " + 
                              document.getDocumentElement().getNodeName());
              // Obtenemos la lista de nodos con nombre empleado de todo el documento.
              NodeList empleados = document.getElementsByTagName("empleado");

              // Recorremos la lista de nodos
              for (int i = 0; i < empleados.getLength(); i ++) {
                      // Obtenemos un nodo
                      Node emple = empleados.item(i);
                      // Comprobamos si es un elemento
                      if (emple.getNodeType() == Node.ELEMENT_NODE) {
                              // Como sabes que el nodo es elemento se convierte a elemento
                              Element elemento = (Element) emple;
                              System.out.println("ID: " + getValorHijo("id", elemento));
                              System.out.println("Apellido: " + getValorHijo("apellido", elemento));
                              System.out.println("Departamento: " + getValorHijo("dep", elemento));
                              System.out.println("Salario: " + getValorHijo("salario", elemento));
                      }
              }
        } catch (Exception e) { 
              e.printStackTrace();
        }
    }
 
     // obtener la información de un nodo. Se supone que solo tiene un valor
     private static String getValorHijo(String etiqueta, Element nodoPadre) {
         // Se obtiene la lista de nodos que hay dentro de ese elemento.
         // Por ejemplo: de un elemento empleado seleccionado (viene en nodoPadre)
         // si pide el id solo obtendrá un nodo id (solo le cuelga un nodo id).
         NodeList nodoHijo = nodoPadre.getElementsByTagName(etiqueta);
         // Seguimos con el ejemplo: Queremos obtener de ese nodo id que tenemos
         // la lista de nodos que cuelgan dentro (que seran los valores). En este 
         // caso el id solo puede tener un valor que puede ser (por ejemplo) 1.
         NodeList nodoValorHijo = nodoHijo.item(0).getChildNodes();
         // Nodo a tratar que es el valor final
         Node valorNodo = (Node) nodoValorHijo.item(0);
         // Devuelve el valor del nodo
         return valorNodo.getNodeValue();
    }
}