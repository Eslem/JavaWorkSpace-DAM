/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.tema3.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eslem
 */
public class ListaPersonas {

    private List<Persona> lista = new ArrayList<Persona>();

    public void ListaPersonas() {

    }

    public void add(Persona per) {
        lista.add(per);
    }

    public List<Persona> getListaPersona() {
        return lista;
    }
}
