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
public class VentanaMensaje {
    
    private Controlador miControlador;

    public void setControlador(Controlador miControlador) {
            this.miControlador=miControlador;
    }    

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje,"Información",JOptionPane.INFORMATION_MESSAGE);      
    }
}
