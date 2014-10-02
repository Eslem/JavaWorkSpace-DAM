/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.security.Principal;
import pojo.Persona;
import View.VentanaPrincipal;

/**
 *
 * @author loren
 */
public class Main {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
    Persona persona1= new Persona();
    persona1.setNombre("Pedro");
    persona1.setDocumento(1);
 
    Persona persona2= new Persona();
    persona2.setNombre("Pablo");
    persona2.setDocumento(2);
 
    Persona persona3= new Persona();
    persona3.setNombre("Paco");
    persona3.setDocumento(3);
    
    Persona personas[] = {persona1,persona2,persona3};

    VentanaPrincipal miVentanaPrincipal=new VentanaPrincipal(personas);
    miVentanaPrincipal.setVisible(true);
  }
 
}
