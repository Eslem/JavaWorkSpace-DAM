/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import View.AlumnoVista;
import View.OrigenDatosVista;
import controlador.Controlador;
import java.io.IOException;
import modelo.BLL.AlumnoBLL;

/**
 *
 * @author loren
 */
public class Main {
    
        public static void main(String[] args) throws Exception{
            try{
                Controlador miControlador = new Controlador();
                
                //Asignamos todas las vistas
                AlumnoVista vista = new AlumnoVista();            
                miControlador.setMiAlumnoVista(vista);
                
                OrigenDatosVista vista2 = new OrigenDatosVista();
                miControlador.setMiOrigenDatosVista(vista2);

                // Asignamos todos los modelos
                AlumnoBLL logica = new AlumnoBLL();
                miControlador.setMiAlumnoBLL(logica);

                // Asignamos todos los controladores
                vista.setControlador(miControlador);
                logica.setControlador(miControlador);
                vista2.setControlador(miControlador);

                miControlador.startAplicattion();
            }catch(Exception e){
                e.printStackTrace();
            } 
        }
    
}
