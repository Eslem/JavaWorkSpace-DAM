/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import util.Constantes;
import vistabd.Modelo;
import vistapantalla.VistaError;
import vistapantalla.VistaLogin;
import vistapantalla.VistaPrincipal;

/**
 * Fichero: ControladorLogin.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 15-abr-2014
 */
class ControladorLogin {

  Modelo modelo;
  VistaPrincipal vPrincipal;
  VistaLogin vLogin;
  Controlador controlador;

  ControladorLogin(Modelo modelo_, VistaPrincipal vPrincipal_, Controlador controlador_) {
    vPrincipal = vPrincipal_;
    modelo = modelo_;
    controlador = controlador_;
    vLogin = new VistaLogin(vPrincipal, true);
  }

  void menu(String comando) {

    try {

      switch (comando) {

        case "VistaLogin":
          iniciaBotones();
          activaVentana();
          break;

        case "VistaLoginEntrar":
          validaAcceso(vPrincipal);
          break;

        case "VistaLoginSalir":
          desactivaVentana();
          break;


      } // swith
    } catch (Exception ex) {
      Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
    }


  }

  void iniciaBotones() {
    vLogin.getVistaLoginEntrar().setActionCommand("VistaLoginEntrar");
    vLogin.getVistaLoginSalir().setActionCommand("VistaLoginSalir");
    vLogin.getVistaLoginEntrar().addActionListener(controlador);
    vLogin.getVistaLoginSalir().addActionListener(controlador);
  }

  void activaVentana() {
    vLogin.setVisible(true);

  }

  void desactivaVentana() {
    vLogin.setVisible(false);
  }

  void validaAcceso(VistaPrincipal vPrincipal) {
    VistaError er = new VistaError();

    String user = this.vLogin.getUser();
    String pass = this.vLogin.getPassword();
    Boolean acceso = usuariovalido(user, pass);

    //si los datos de acceso son correctos en login
    if (acceso) {
      vLogin.dispose();//cierra VistaLogin
      //habilita/deshabilita controles segun corresponda
      vPrincipal.getBotonidentificacion().setEnabled(false);
      vPrincipal.getBotonsalir().setEnabled(true);
      vPrincipal.getBotondocumentacion().setEnabled(true);
      vPrincipal.getBotonpedidos().setEnabled(true);
      vPrincipal.getBotonclientes().setEnabled(true);
      vPrincipal.getBotonproductos().setEnabled(true);
      vPrincipal.getBotonJavadoc().setEnabled(true);
      vPrincipal.getBotonConfigurar().setEnabled(true);
    } else {
      er.error(vPrincipal, 1);
    }
  }

  public boolean usuariovalido(String user, String pass) {

    boolean valido = false;
    if (user.equals(Constantes.BDUSUARIO) && pass.equals(Constantes.BDPASSWORD)) {
      valido = true;
    }
    return valido;
  }
}
