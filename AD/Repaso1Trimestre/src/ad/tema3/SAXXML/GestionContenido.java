/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.tema3.SAXXML;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author eslem
 */
public class GestionContenido extends DefaultHandler {

    private List<Empleado> listaEmpleados;
    private Empleado empleado;
    private String whereIm;

    public GestionContenido() {
        super();
        this.listaEmpleados = new ArrayList<Empleado>();
    }

    @Override
    public void startDocument() {
        System.out.println("Starting document");
    }

    @Override
    public void endDocument() {
        System.out.println("End document");
    }

    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
        whereIm = nombre;
        if (nombre.equals("empleado")) {
            empleado = new Empleado();
        }
        System.out.println("Start element");
    }

    @Override
    public void endElement(String uri, String nombre, String nombreC) {
        System.out.println("End element");
        whereIm = "/" + nombre;
        if (whereIm.equals("/empleado")) {
            listaEmpleados.add(empleado);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String car = new String(ch, start, length);
        car = car.replaceAll("[\t\n]", "");
        System.out.println("\tCaracteres:	" + car);
        if (whereIm.equals("id")) {
            empleado.setId(Integer.parseInt(car));
        } else if (whereIm.equals("apellido")) {
            empleado.setApellido(car);
        } else if (whereIm.equals("dep")) {
            empleado.setDep(Integer.parseInt(car));
        } else if (whereIm.equals("salario")) {
            empleado.setSalario(Double.parseDouble(car));
        }
    }

    public List<Empleado> getList() {
        return listaEmpleados;
    }

}
