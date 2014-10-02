/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistabd.Modelo;
import vistapantalla.VistaDialogo;
import vistapantalla.VistaPrincipal;
import vistapantalla.VistaTrayIcon;

/**
 * Fichero: ControladorTrayIcon.java
 *
 * @date 05-may-2014
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * Fuente:
 * http://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html
 */
class ControladorTrayIcon {

  private VistaTrayIcon vIcon = null;
  private VistaPrincipal vPrincipal;
  private VistaDialogo vDialogo;
  private Modelo modelo;
  private Controlador controlador;

  public ControladorTrayIcon(Modelo modelo_, VistaPrincipal vPrincipal, Controlador controlador_) {
    vIcon = new VistaTrayIcon();
    modelo = modelo_;
    controlador = controlador_;
    initComponents();

  }

  private void initComponents() {
    vDialogo = new VistaDialogo();
    final TrayIcon trayIcon = vIcon.gettrayIcon();
    final SystemTray tray = vIcon.gettray();
    MenuItem aboutItem = vIcon.getaboutItem();
    MenuItem exitItem = vIcon.getexitItem();
    MenuItem clienteItem = vIcon.getclienteItem();
    MenuItem productoItem = vIcon.getproductoItem();
    MenuItem pedidoItem = vIcon.getpedidoItem();


    if (!SystemTray.isSupported()) {
      vDialogo.mostrarMessageDialgo(vPrincipal, "SystemTray no está soportado");
      //System.out.println("SystemTray is not supported");
    }

    aboutItem.addActionListener(
            new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        vDialogo.mostrarMessageDialgo(vPrincipal, "Este dialogo está ejecutandose con System Tray");
        //JOptionPane.showMessageDialog(vPrincipal,"This dialog box is run from System Tray");
      }
    });

    exitItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tray.remove(trayIcon);
        modelo.getBd().desconectar();
        System.exit(0);
      }
    });


    vIcon.getclienteItem().setActionCommand("VistaCliente");
    vIcon.getproductoItem().setActionCommand("VistaProducto");
    vIcon.getpedidoItem().setActionCommand("VistaPedido");

    vIcon.getclienteItem().addActionListener(controlador);
    vIcon.getproductoItem().addActionListener(controlador);
    vIcon.getpedidoItem().addActionListener(controlador);

  }
}
