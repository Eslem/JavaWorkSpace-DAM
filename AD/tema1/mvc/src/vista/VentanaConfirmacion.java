/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import controlador.Controlador;
import javax.swing.JOptionPane;

/**
 *
 * @author loren
 */
public class VentanaConfirmacion {

    private Controlador miControlador;

    public void setControlador(Controlador miControlador) {
            this.miControlador=miControlador;
    }    

    public int mostrarConfirmacion(String mensaje){
        return JOptionPane.showConfirmDialog(null,mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);     
    }    
}
