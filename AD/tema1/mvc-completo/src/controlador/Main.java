/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import vistabd.Modelo;
import vistapantalla.VistaError;
import vistapantalla.VistaPrincipal;
import vistapantalla.VistaProducto;

/**
 * Fichero: Main.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 07-feb-2014
 */
public class Main {

  static final String TIPO1 = "cvs";
  static final String TIPO2 = "mysql";
  static final String TIPO3 = "db4o";
  static final String TIPO4 = "hibernate";
  static String tipo = TIPO3;

  public static void main(String[] args) throws Exception {

    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(VistaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(VistaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(VistaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(VistaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          VistaError er = new VistaError();
          Modelo modelo = new Modelo(tipo);
          VistaPrincipal vista = new VistaPrincipal();
          new Controlador(vista, modelo);
        } catch (Exception ex) {
          //er.error(vista, -5);
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
  }
}
