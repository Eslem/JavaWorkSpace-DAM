package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistabd.Bd;
import vistabd.Modelo;
import vistapantalla.VistaError;
import vistapantalla.VistaPrincipal;

/**
 * Fichero: Controlador.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 18-feb-2014
 */
public class Controlador implements ActionListener {

  private Modelo modelo;
  private Bd bd;
  private VistaPrincipal vistaprincipal;
  private VistaError vError;
  private ControladorPrincipal controladorprincipal;
  private ContraladorCliente controladorcliente;
  private ControladorLogin controladorlogin;
  private ContraladorProducto controladorproducto;
  private ContraladorPedido controladorpedido;
  private ContraladorConfigurar controladorconfigurar;

  //En el constructor inicializamos nuestros objetos
  public Controlador(VistaPrincipal vista_, Modelo modelo_) throws Exception {
    vistaprincipal = vista_;
    this.modelo = modelo_;
    iniciar();
    vError = new VistaError();
    controladorprincipal.ver();
  }

  private void iniciar() throws Exception {

    Controlador controlador = this;
    controladorprincipal = new ControladorPrincipal(modelo, vistaprincipal, controlador);
    controladorlogin = new ControladorLogin(modelo, vistaprincipal, controlador);
    controladorcliente = new ContraladorCliente(modelo, vistaprincipal, controlador);
    controladorproducto = new ContraladorProducto(modelo, vistaprincipal, controlador);
    controladorpedido = new ContraladorPedido(modelo, vistaprincipal, controlador);
    controladorconfigurar = new ContraladorConfigurar(modelo, vistaprincipal, controlador);

  }

  /* ATENTO A LAS ACCIONES DEL USUARIO */
  @Override
  public void actionPerformed(ActionEvent e) {

    //Captura en String el comando accionado por el usuario
    String comando = e.getActionCommand();
    /* Acciones del formulario padre */
    switch (comando) {
      case "VistaLogin":
      case "VistaLoginEntrar":
      case "VistaLoginSalir":
        controladorlogin.menu(comando);
        break;

      case "VistaCliente":
      case "VistaClientePrimero":
      case "VistaClienteUltimo":
      case "VistaClienteAntes":
      case "VistaClienteDespues":
      case "VistaClienteAnadir":
      case "VistaClienteModificar":
      case "VistaClienteBorrar":
      case "VistaClienteSalir":
        controladorcliente.menu(comando);
        break;

      case "VistaProducto":
      case "VistaProductoAnadir":
      case "VistaProductoBorrar":
      case "VistaProductoSalir":
        controladorproducto.menu(comando);
        break;

      case "VistaPedido":
      case "VistaPedidoAnadir":
      case "VistaPedidoBorrar":
      case "VistaLineaAnadir":
      case "VistaLineaBorrar":
      case "VistaPedidoSalir":
        controladorpedido.menu(comando);
        break;

      case "Documentacion":
      case "Javadoc":
      case "Salir":
        controladorprincipal.menu(comando);
        break;

      case "Configurar":
      case "VistaConfModificar":
      case "VistaConfSalir":
        controladorconfigurar.menu(comando);
        break;

    } // swith
    comando = "";
  }
}
