/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistapantalla;

import javax.swing.JOptionPane;

/**
 * Fichero: VistaError.java
 *
 * @date 18-feb-2014
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
public class VistaError {

  public String tipo(int e) {
    String t = "";
    switch (e) {
      case 1:
        t = "Los datos de acceso son incorrectos";
        break;
      case -1:
        t = "No se pueden borrar pedidos que esten en lineas de pedido";
        break;
      case -2:
        t = "No se pueden borrar productos que esten en lineas de pedido";
        break;
      case -3:
        t = "No se pueden borrar cliente que este en un pedido";
        break;
      case -4:
        t = "Error en el la clase Contralador";
        break;
      case -5:
        t = "Error en el la clase principal. Falla controlador";
        break;
      case -6:
        t = "Error en controlador principal";
        break;
      case -7:
        t = "Error";
        break;
    }
    return t;

  }

  public void error(VistaPrincipal v, int i) {
    JOptionPane.showMessageDialog(v, tipo(i));
  }

  public Object error(VistaPrincipal vPrincipal, String name) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
