/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import javax.swing.JOptionPane;

/**
 * Fichero: VentanaError.java
 */
public class VentanaError {
    
    private Controlador miControlador;

    public void setControlador(Controlador miControlador) {
            this.miControlador=miControlador;
    }    

  public String tipo(int e) {
    String t = "";
    switch (e) {
      case 1:
        t = "El identificador de la persona es erroneo";
        break;
      case 2:
        t = "Debe ingresar un dato numerico";
        break; 
      case 3:
        t = "Se ha presentado un Error";
        break;
      case 4:
        t = "El nombre de la persona debe ser mayor a 5 digitos";
        break;  
      case 5:
        t = "Error al modificar";
        break;  
      case 6:
        t = "Error al eliminar";
        break;    
      case 7:
        t = "Error al ingreso de los datos";
        break;  
      case 8:
        t = "La persona no existe";
        break;  
      case 9:
        t = "Ingrese un numero de Documento";
        break;          
    }
    return t;
  }
  
  public void mostrarAdvertencia(int codigo){
    JOptionPane.showMessageDialog(null, tipo(codigo),"Advertencia",JOptionPane.WARNING_MESSAGE);      
  }
  public void mostrarError(int codigo) {
    JOptionPane.showMessageDialog(null, tipo(codigo),"Error",JOptionPane.ERROR_MESSAGE);  
  }

  public Object mostrarError(String name) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
