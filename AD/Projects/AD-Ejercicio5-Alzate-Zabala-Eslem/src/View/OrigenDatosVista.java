/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import controlador.Controlador;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author loren
 */
public class OrigenDatosVista {
    
    private BufferedReader stdin = null;
    private Controlador miControlador;    
    
    public OrigenDatosVista(){
        super();
        stdin = new BufferedReader(new InputStreamReader(System.in));        
    }   
    
    
    // Vista que muestra el menú en la pantalla principal
    public void showSourceDataView(){
        try{
            System.out.println();
            System.out.println("Selecciona el origen de los datos");
            System.out.println("1.- FileText.");
            System.out.println("2.- MySQL.");
            System.out.println("3.- FileBuffer.");
            System.out.println("4.- XML.");
            int opcion = Integer.parseInt(stdin.readLine());
            this.miControlador.setTypeSource(opcion);
        }catch(Exception e){
            this.miControlador.showErrorView(e.getMessage());
        }
        
    }

    public void setControlador(Controlador miControlador) {
        this.miControlador = miControlador;
    }
    
}
