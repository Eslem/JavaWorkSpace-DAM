/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import modelodatos.Cliente;
import vistabd.Modelo;
import vistapantalla.VistaCliente;
import vistapantalla.VistaDialogo;
import vistapantalla.VistaError;
import vistapantalla.VistaPrincipal;

/**
 * Fichero: ControladorClientes.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 15-abr-2014
 */
public class ContraladorCliente {

  Modelo modelo;
  VistaPrincipal vPrincipal;
  VistaCliente vCliente;
  VistaError vError;
  VistaDialogo vDialogo;
  Controlador controlador;
  int posicion = 0;
  VerificadorEntrada verficador = new VerificadorEntrada();

  ContraladorCliente(Modelo modelo_, VistaPrincipal vPrincipal_, Controlador controlador_)
          throws Exception {
    modelo = modelo_;
    vPrincipal = vPrincipal_;
    controlador = controlador_;
    vCliente = new VistaCliente();
    VistaError er = new VistaError();
    VistaDialogo vd = new VistaDialogo();
  }

  void iniciaBotones() {
    vCliente.getVistaClienteAnadir().setActionCommand("VistaClienteAnadir");
    vCliente.getVistaClienteModificar().setActionCommand("VistaClienteModificar");
    vCliente.getVistaClientePrimero().setActionCommand("VistaClientePrimero");
    vCliente.getVistaClienteUltimo().setActionCommand("VistaClienteUltimo");
    vCliente.getVistaClienteAntes().setActionCommand("VistaClienteAntes");
    vCliente.getVistaClienteDespues().setActionCommand("VistaClienteDespues");
    vCliente.getVistaClienteBorrar().setActionCommand("VistaClienteBorrar");
    vCliente.getVistaClienteSalir().setActionCommand("VistaClienteSalir");

    vCliente.getVistaClienteAnadir().addActionListener(controlador);
    vCliente.getVistaClienteModificar().addActionListener(controlador);
    vCliente.getVistaClientePrimero().addActionListener(controlador);
    vCliente.getVistaClienteUltimo().addActionListener(controlador);
    vCliente.getVistaClienteAntes().addActionListener(controlador);
    vCliente.getVistaClienteDespues().addActionListener(controlador);
    vCliente.getVistaClienteBorrar().addActionListener(controlador);
    vCliente.getVistaClienteSalir().addActionListener(controlador);
  }

  void activaVentana() throws Exception {

    vCliente.setTitle("CLIENTES");
    vCliente.setLocation(630, 132);
    vCliente.setVisible(true);
  }

  void rellenaCampos() {


    if (modelo.getClientes().size() > 0) {

      InputVerifier verifierEmail = new InputVerifier() {
        public boolean verify(JComponent input) {
          final JTextComponent source = (JTextComponent) input;
          String text = source.getText();
          boolean esobligatorio = true;
          String tipodato = "email";
          return verficador.esValido(source, text, true, tipodato);

        }
      };


      Cliente cliente = modelo.getClientes().get(posicion);
      vCliente.getId().setText(cliente.getId());
      vCliente.getNombre().setText(cliente.getNombre());
      vCliente.getEmail().setText(cliente.getEmail());
      vCliente.getEmail().setInputVerifier(verifierEmail);
    }
  }

  void menu(String comando) {
    try {
      switch (comando) {
        case "VistaCliente":
          iniciaBotones();
          rellenaCampos();
          activaVentana();
          break;
        case "VistaClienteAnadir":
          anadir();
          break;
        case "VistaClienteModificar":
          modificar();
          break;
        case "VistaClienteBorrar":
          borrar();
          break;
        case "VistaClientePrimero":
          primero();
          break;
        case "VistaClienteUltimo":
          ultimo();
          break;
        case "VistaClienteAntes":
          antes();
          break;
        case "VistaClienteDespues":
          despues();
          break;
        case "VistaClienteSalir":
          desactivaVentana();
          break;
      } // swith
      comando = "";
    } catch (Exception ex) {
      vError.error(vPrincipal, -2);
      //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void desactivaVentana() {
    vCliente.setVisible(false);
  }

  private void primero() {
    posicion = 0;
    rellenaCampos();
  }

  private void ultimo() {
    posicion = modelo.getClientes().size() - 1;
    rellenaCampos();
  }

  private void antes() {
    if (posicion > 0) {
      posicion--;
      rellenaCampos();
    }
  }

  private void despues() {
    if (posicion < modelo.getClientes().size() - 1) {
      posicion++;
      rellenaCampos();
    }
  }

  private void anadir() {

    String Id = obtenerClienteId(modelo.getClientes());
    vCliente.getId().setText(Id);
    String Nombre = vCliente.getNombre().getText();
    String Email = vCliente.getEmail().getText();
    Cliente cliente = new Cliente(Id, Nombre, Email, null);
    modelo.addCliente(cliente);


  }

  private String obtenerClienteId(ArrayList<Cliente> clientes) {
    int max = 0;
    int id = 0;
    Cliente cli = new Cliente();
    Iterator it = clientes.iterator();
    while (it.hasNext()) {
      cli = (Cliente) it.next();
      id = Integer.parseInt(cli.getId());
      if (id > max) {
        max = id;
      }
    }
    return String.valueOf(id + 1);
  }

  private void borrar() {

    int res = 0;

    String Id = vCliente.getId().getText();
    String titulo = "Aviso";
    String texto = "Desea borrar a: " + Id;
    int tipo = JOptionPane.WARNING_MESSAGE;
    // int opc = JOptionPane.showConfirmDialog(vCliente, texto, titulo, tipo);
    int opc = vDialogo.mostrarConfirmDialog(vCliente, texto, titulo, tipo);

    res = modelo.delCliente(posicion);
    if (res < 0) {
      vError.error(vPrincipal, res);
    } else {
      primero();
    }


  }

  private void modificar() {

    String Id = vCliente.getId().getText();
    Cliente cliente = modelo.getCliente(Id);
    cliente.setNombre(vCliente.getNombre().getText());
    cliente.setEmail(vCliente.getEmail().getText());

    modelo.modifyCliente(cliente, posicion);
  }
}
