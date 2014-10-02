/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import modelodatos.Configura;
import vistabd.Modelo;
import vistapantalla.VistaConfigurar;
import vistapantalla.VistaPrincipal;

/**
 * Fichero: ContraladorConfigurar.java
 *
 * @date 28-abr-2014
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
class ContraladorConfigurar {

  private Modelo modelo;
  private VistaPrincipal vPrincipal;
  private VistaConfigurar vConfigurar;
  private Controlador controlador;
  private Configura configura = null;

  public ContraladorConfigurar(Modelo modelo_, VistaPrincipal vistaprincipal_, Controlador controlador_) {
    modelo = modelo_;
    vPrincipal = vistaprincipal_;
    controlador = controlador_;
    vConfigurar = new VistaConfigurar();
  }

  public void menu(String comando) {
    try {
      switch (comando) {
        case "Configurar":
          iniciaBotones();
          activaVentana();
          rellenacampos();
          break;
        case "VistaConfModificar":
          modificar();
          break;
        case "VistaConfSalir":
          desactivaVentana();
          break;
      } // swith
      comando = "";
    } catch (Exception ex) {
      Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void iniciaBotones() {
    vConfigurar.getVistaConfModificar().setActionCommand("VistaConfModificar");
    vConfigurar.getVistaConfSalir().setActionCommand("VistaConfSalir");

    vConfigurar.getVistaConfModificar().addActionListener(controlador);
    vConfigurar.getVistaConfSalir().addActionListener(controlador);

  }

  public void activaVentana() throws Exception {
    vConfigurar.setTitle("CONFIGURAR APLICACIÓN");
    vConfigurar.setLocation(630, 132);
    vConfigurar.setVisible(true);
  }

  public void desactivaVentana() {
    vConfigurar.setVisible(false);
  }

  private void modificar() throws ParserConfigurationException, TransformerException {

    configura.setId("1");
    configura.setNombreaplicacion(vConfigurar.getNombretxt().getText());
    configura.setAutor(vConfigurar.getAutortxt().getText());
    configura.setBasedatos(vConfigurar.getBdtxt().getText());
    configura.setFecha(vConfigurar.getFechatxt().getText());
    configura.setFuentedatos(vConfigurar.getFuentetxt().getText());
    modelo.modifyConfigura(configura);
    modificarFormulario(configura);

  }

  private void rellenacampos() {
    configura = modelo.getConfigura();
    if (configura != null) {
      modificarFormulario(configura);
    }
  }

  private void modificarFormulario(Configura configura) {
    vConfigurar.getNombretxt().setText(configura.getNombreaplicacion());
    vConfigurar.getAutortxt().setText(configura.getAutor());
    vConfigurar.getBdtxt().setText(configura.getBasedatos());
    vConfigurar.getFechatxt().setText(configura.getFecha());
    vConfigurar.getFuentetxt().setText(configura.getFuentedatos());
  }
}
