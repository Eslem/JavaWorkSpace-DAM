/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eslem
 */
public class ListaAlumnos {
    private ArrayList<Alumno> lista = new ArrayList<Alumno>();
    
    public ListaAlumnos(){    	
    }

    public void add(Alumno per) {
        lista.add(per);
    }
   
   public List<Alumno> getListaAlumnos() {
        return lista;
   }
}
