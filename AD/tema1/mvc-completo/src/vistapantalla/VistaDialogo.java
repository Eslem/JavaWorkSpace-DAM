/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistapantalla;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import util.Constantes;

/**
 * Fichero: VistaDialogo.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 29-abr-2014
 */
public class VistaDialogo {

  public int mostrarConfirmDialog(JFrame jframe, String string, String aviso, int mensaje) {
    return JOptionPane.showConfirmDialog(jframe, string, aviso, mensaje);
  }

  public int mostrarConfirmDialog(VistaProducto vista, String string, String aviso, int mensaje) {
    return JOptionPane.showConfirmDialog(vista, string, aviso, mensaje);
  }

  public String mostrarInputDialog(VistaProducto vProducto1, String nombre_, String titulo, int mensaje) {
    return JOptionPane.showInputDialog(vProducto1, nombre_, titulo, mensaje);
  }

  public String mostrarInputDialog(VistaPedido vPedido1,
          String id_Producto_, String Titulo, int QUESTION_MESSAGE,
          Object object, Object[] nombresProductos, Object object0) {
    /* Con JCombobox
     http://chuwiki.chuidiang.org/index.php?title=JOptionPane_y_di%C3%A1logos_modales
     * */
    String res;
    res = (String) JOptionPane.showInputDialog(
            vPedido1,
            "Id Producto: ",
            Titulo,
            JOptionPane.QUESTION_MESSAGE,
            null, // null para icono defecto
            nombresProductos,
            nombresProductos[0]);

    return res;
  }

  public int mostrarConfirmDialogBorrarPedido(VistaPedido vPedido1, String string, String aviso, int WARNING_MESSAGE) {
    return JOptionPane.showConfirmDialog(vPedido1, "Desea borrar: " + string + " ", "Aviso", JOptionPane.WARNING_MESSAGE);
  }

  public void ejecutarcomando(String pwd) throws IOException {
    String comando = null;

    Process p = Runtime.getRuntime().exec("pwd");
    BufferedReader stdInput =
            new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader stdError =
            new BufferedReader(new InputStreamReader(p.getErrorStream()));
    // Leemos la salida del comando
    System.out.println("Esta es la salida standard del comando:\n");
    String s;
    while ((s = stdInput.readLine()) != null) {
      System.out.println(s);
    }
  }

  public void abrirfichero(String filename) throws IOException {
    File doc = new File(filename);
    Desktop.getDesktop().open(doc);
  }

  public void mostrarMessageDialgo(JFrame jframe, String texto) {
    JOptionPane.showMessageDialog(jframe, "This dialog box is run from System Tray");
  }
}
