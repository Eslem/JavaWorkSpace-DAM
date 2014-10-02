/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import modelodatos.Configura;
import util.Constantes;
import vistabd.Modelo;
import vistapantalla.VistaDialogo;
import vistapantalla.VistaError;
import vistapantalla.VistaPrincipal;

/**
 * Fichero: ControladorPrincipal.java Se definen los botones de la vista general
 * y sus acciones
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 15-abr-2014
 */
public class ControladorPrincipal {

  private Modelo modelo = null;
  private VistaPrincipal vPrincipal = null;
  private Controlador controlador;
  private ControladorTrayIcon cIcon;
  private VistaError vError;
  private VistaDialogo vd;
  private static Logger LOGGER;

  public ControladorPrincipal(Modelo modelo_, VistaPrincipal vPrincipal_, Controlador controlador_) throws IOException {
    modelo = modelo_;
    vPrincipal = vPrincipal_;
    controlador = controlador_;
    cIcon = new ControladorTrayIcon(modelo, vPrincipal, controlador);
    vError = new VistaError();
    vd = new VistaDialogo();
    LOGGER = Logger.getLogger(Controlador.class.getName());
    configura(vPrincipal, controlador);
  }

  public void configura(VistaPrincipal vPrincipal, Controlador controlador) throws IOException {

    // Botones de la vista principal
    vPrincipal.getBotonproductos().setActionCommand("VistaProducto");
    vPrincipal.getBotonclientes().setActionCommand("VistaCliente");
    vPrincipal.getBotonpedidos().setActionCommand("VistaPedido");
    vPrincipal.getBotonidentificacion().setActionCommand("VistaLogin");
    vPrincipal.getBotonsalir().setActionCommand("Salir");
    vPrincipal.getBotondocumentacion().setActionCommand("Documentacion");
    vPrincipal.getBotonJavadoc().setActionCommand("Javadoc");
    vPrincipal.getBotonConfigurar().setActionCommand("Configurar");

    vPrincipal.getBotonidentificacion().addActionListener(controlador);
    vPrincipal.getBotonsalir().addActionListener(controlador);
    vPrincipal.getBotondocumentacion().addActionListener(controlador);
    vPrincipal.getBotonproductos().addActionListener(controlador);
    vPrincipal.getBotonclientes().addActionListener(controlador);
    vPrincipal.getBotonpedidos().addActionListener(controlador);
    vPrincipal.getBotonJavadoc().addActionListener(controlador);
    vPrincipal.getBotonConfigurar().addActionListener(controlador);

    //Hacemos funcional sólo los botones true
    vPrincipal.getBotonidentificacion().setEnabled(true);
    vPrincipal.getBotonsalir().setEnabled(true);
    activarbotones(true);
    indicarFuente(vPrincipal, modelo.getFuente());
    definelogger();

  }

  public void activarbotones(boolean opcion) {
    vPrincipal.getBotondocumentacion().setEnabled(opcion);
    vPrincipal.getBotonpedidos().setEnabled(opcion);
    vPrincipal.getBotonclientes().setEnabled(opcion);
    vPrincipal.getBotonproductos().setEnabled(opcion);
    vPrincipal.getBotonJavadoc().setEnabled(opcion);
    vPrincipal.getBotonConfigurar().setEnabled(opcion);
  }

  public void indicarFuente(VistaPrincipal vPrincipal, String fuente) {

    Configura configura = modelo.getConfigura();
    vPrincipal.getjLabel1NombreAplicacion().setText("Aplicación: " + configura.getNombreaplicacion());
    vPrincipal.getjLabel1Autores().setText("Autor/es: " + configura.getAutor());
    vPrincipal.getjLabel2Proyecto().setText("Proyecto: " + configura.getBasedatos());
    vPrincipal.getjLabel3Fecha().setText("Fecha: " + configura.getFecha());
    vPrincipal.getjLabel1Fuente().setText("Fuente de Datos: " + configura.getFuentedatos());
  }

  public void salir() {
    modelo.getBd().desconectar();
    vPrincipal.setVisible(false);
    System.exit(0);
  }

  public void ver() {
    vPrincipal.setVisible(true);
  }

  public void mostrarDocumentacion() throws IOException {

    vd.abrirfichero(Constantes.DOCUMENTACION);
    //vd.ejecutarcomando("pwd");
  }

  public void mostrarJavadoc() throws IOException {

    vd.abrirfichero(Constantes.JAVADOC);

  }

  void menu(String comando) {
    try {
      switch (comando) {
        case "Documentacion":
          mostrarDocumentacion();
          break;
        case "Javadoc":
          mostrarJavadoc();
          break;
        case "Salir":
          salir();
          break;

      } // swith
      comando = "";
    } catch (IOException ex) {
      vError.error(vPrincipal, -6);
      //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private void definelogger() throws IOException {
    FileHandler fileTxt = new FileHandler("Logging.txt");
    SimpleFormatter formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    LOGGER.addHandler(fileTxt);
  }
}
