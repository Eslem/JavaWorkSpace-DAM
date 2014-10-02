package controlador;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelodatos.Cliente;
import modelodatos.Linea;
import modelodatos.Pedido;
import modelodatos.Producto;
import modelotablas.TableModelLinea;
import modelotablas.TableModelPedido;
import modelotablas.TableModelProducto;
import util.Constantes;
import util.Funciones;
import vistabd.Modelo;
import vistapantalla.VistaDialogo;
import vistapantalla.VistaError;
import vistapantalla.VistaPedido;
import vistapantalla.VistaPrincipal;

/**
 * Fichero: ContraladorPedido.java
 *
 * @date 27-abr-2014
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
class ContraladorPedido {

  private Modelo modelo;
  private VistaPrincipal vPrincipal;
  private VistaPedido vPedido1;
  private Controlador controlador;
  private TableModelProducto tablemodelproducto;
  private TableModelPedido tablemodelpedido;
  private TableModelLinea tablemodellinea;
  private Pedido pedidoSelected;
  private String idpedidoSelected = "0";

  ContraladorPedido(Modelo modelo_, VistaPrincipal vPrincipal_, Controlador controlador_)
          throws Exception {
    modelo = modelo_;
    vPrincipal = vPrincipal_;
    controlador = controlador_;
    vPedido1 = new VistaPedido(modelo);
    //VistaPedido1.setModelo(modelo);
    tablemodelproducto = new TableModelProducto(modelo);
    tablemodelpedido = new TableModelPedido(modelo);
    tablemodellinea = new TableModelLinea(modelo, idpedidoSelected);
  }

  void iniciaBotones() {
    vPedido1.getVistaPedidoAnadir().setActionCommand("VistaPedidoAnadir");
    vPedido1.getVistaPedidoBorrar().setActionCommand("VistaPedidoBorrar");
    vPedido1.getVistaPedidoSalir().setActionCommand("VistaPedidoSalir");
    vPedido1.getVistaLineaAnadir().setActionCommand("VistaLineaAnadir");
    vPedido1.getVistaLineaBorrar().setActionCommand("VistaLineaBorrar");


    vPedido1.getVistaPedidoAnadir().addActionListener(controlador);
    vPedido1.getVistaPedidoBorrar().addActionListener(controlador);
    vPedido1.getVistaPedidoSalir().addActionListener(controlador);
    vPedido1.getVistaLineaAnadir().addActionListener(controlador);
    vPedido1.getVistaLineaBorrar().addActionListener(controlador);

    JTable jTable1Pedido = vPedido1.getJTable1Pedido();
    jTable1Pedido.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jTable1PedidoMouseClicked(evt);
      }
    });

  }

  void activaVentana() throws Exception {

    vPedido1.setTitle("PEDIDO");
    vPedido1.setLocation(630, 132);
    vPedido1.setVisible(true);
  }

  void menu(String comando) {

    try {
      switch (comando) {
        case "VistaPedido":
          iniciaBotones();
          activaVentana();
          break;
        case "VistaPedidoAnadir":
          anadirPedido();
          break;
        case "VistaPedidoBorrar":
          borrarPedido();
          break;
        case "VistaLineaAnadir":
          anadirLinea();
          break;
        case "VistaLineaBorrar":
          borrarLinea();
          break;
        case "VistaPedidoSalir":
          desactivaVentana();
          break;
      } // swith
      comando = "";
    } catch (Exception ex) {
      Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private Linea obtenerDatosLinea() {

    VistaDialogo vd = new VistaDialogo();
    Funciones f = new Funciones();

    if (idpedidoSelected == null) {
      return null;
    }
    String idpedido = idpedidoSelected;
    String idlinea = obtenerLineaId(modelo.getLineas());
    String Titulo = "Alta Linea. Pedido " + idpedidoSelected + ". Linea " + idlinea;
    Object[] nombresProductos = modelo.getNombreProductos();
    String seleccion = (String) vd.mostrarInputDialog(
            vPedido1,
            "Id Producto: ",
            Titulo,
            JOptionPane.QUESTION_MESSAGE,
            null, // null para icono defecto
            nombresProductos,
            nombresProductos[0]);
    String idproducto = modelo.getIdProducto(seleccion.toString());
    Producto producto = modelo.getProducto(idproducto);
    //String cantidad = (String) vd.mostrarInputDialog(vPedido1, "Cantidad: ", Titulo, JOptionPane.QUESTION_MESSAGE, null, null, "1");
    String cantidad = (String) JOptionPane.showInputDialog(
            vPedido1,
            "Cantidad: ",
            Titulo,
            JOptionPane.QUESTION_MESSAGE,
            null, // null para icono defecto
            null,
            1);
    String precio = modelo.getProductoPrecio(idproducto);
    String ahora = f.ahora(Constantes.ANIO_MES_DIA);
    //String fechalinea = (String) vd.mostrarInputDialog(vPedido1, "Fecha: ", Titulo, JOptionPane.QUESTION_MESSAGE, null, null, ahora);
    String fechalinea = (String) JOptionPane.showInputDialog(
            vPedido1,
            "Fecha: ",
            Titulo,
            JOptionPane.QUESTION_MESSAGE,
            null, // null para icono defecto
            null,
            ahora);
    Linea linea = new Linea(idlinea, producto, pedidoSelected, precio, cantidad, fechalinea);
    idpedidoSelected = null;
    return linea;
  }

  private Pedido obtenerDatosPedido() {

    Funciones f = new Funciones();
    String ahora = f.ahora(Constantes.ANIO_MES_DIA);

    String idpedido = obtenerPedidoId(modelo.getPedidos());
    String Titulo = "Alta Pedido. Id: " + idpedido;
    Object[] nombresClientes = modelo.getNombreClientes();

    // Con JCombobox
    // http://chuwiki.chuidiang.org/index.php?title=JOptionPane_y_di%C3%A1logos_modales
    Object seleccion = JOptionPane.showInputDialog(
            vPedido1,
            "Id Cliente: ",
            Titulo,
            JOptionPane.QUESTION_MESSAGE,
            null, // null para icono defecto
            nombresClientes,
            nombresClientes[0]);
    if (seleccion == null) {
      return null;
    }
    String idcliente = modelo.getIdCliente(seleccion.toString());


    Cliente cliente = new Cliente();
    cliente = modelo.getCliente(idcliente);
    //String nombre = nombre = modelo.getclienteNombre(idcliente);

    String fechapedido = (String) JOptionPane.showInputDialog(vPedido1, "Fecha Pedido: ", Titulo, JOptionPane.QUESTION_MESSAGE, null, null, ahora);
    Pedido pedido = new Pedido(idpedido, cliente, fechapedido, null);

    return pedido;
  }

  public ArrayList getDatosPedido() {
    return tablemodelpedido.getDatos();
  }

  public ArrayList getDatosLinea() {
    return tablemodellinea.getDatos();
  }

  private String obtenerPedidoId(ArrayList<Pedido> pedidos) {

    int max = 0;
    int id = 0;
    Pedido ped = new Pedido();
    Iterator it = pedidos.iterator();

    if (pedidos.size() == 0) {
      return "1";
    }

    while (it.hasNext()) {
      ped = (Pedido) it.next();
      id = Integer.parseInt(ped.getId());
      if (id > max) {
        max = id;
      }
    }
    return String.valueOf(id + 1);
  }

  private String obtenerLineaId(ArrayList<Linea> lineas) {

    int maxidlinea = 0;
    int id = 1;
    int idlinea = 0;
    Linea lin = new Linea();

    if (lineas.isEmpty()) {
      return "1";
    }

    Iterator it = lineas.iterator();
    while (it.hasNext()) {
      lin = (Linea) it.next();
      idlinea = Integer.parseInt(lin.getIdlinea());
      if (idlinea > maxidlinea) {
        maxidlinea = idlinea;
      }
    }
    maxidlinea++;
    return String.valueOf(maxidlinea);
  }

  private void desactivaVentana() {
    vPedido1.setVisible(false);
  }

  private void anadirPedido() {

    JTable jTable1Pedido = vPedido1.getJTable1Pedido();
    Pedido pedido = obtenerDatosPedido();
    if (pedido == null) {
      return;
    }

    Cliente cliente = pedido.getCliente();
    String idcliente = cliente.getId();
    String nombre = modelo.getclienteNombre(idcliente);
    Object[] row = {pedido.getId(), idcliente, nombre, pedido.getFecha()};

    ((TableModelPedido) jTable1Pedido.getModel()).addRow(row);
    modelo.addPedido(pedido, cliente);
  }

  private void borrarPedido() {
    int res = 0;
    VistaError er = new VistaError();
    VistaDialogo vd = new VistaDialogo();
    JTable jTable1Pedido = vPedido1.getJTable1Pedido();

    int selec = jTable1Pedido.getSelectedRow();
    if (selec >= 0) {
      String id = (String) jTable1Pedido.getModel().getValueAt(selec, 0);
      int opc;
      opc = vd.mostrarConfirmDialogBorrarPedido(vPedido1, "Desea borrar: " + id + " ", "Aviso", JOptionPane.WARNING_MESSAGE);
      //int opc = JOptionPane.showConfirmDialog(vPedido1, "Desea borrar: " + id + " ", "Aviso", JOptionPane.WARNING_MESSAGE);
      if (opc == 0) {
        res = modelo.delPedido(selec);
        if (res < 0) {
          // No se puede borrar un pedido si hay lineas de pedido!!
          er.error(null, res);
        } else {
          ((TableModelPedido) jTable1Pedido.getModel()).removeRow(selec);
        }
      }
    }
  }

  /*
   *  Añade una linea de pedido nueva.
   */
  private void anadirLinea() {

    JTable jTable2Linea = vPedido1.getJTable2Linea();
    JLabel jLabel4Totalpedido = vPedido1.getjLabel4Totalpedido();

    if (idpedidoSelected == null) {
      return;
    }

    Linea linea = obtenerDatosLinea();
    Producto producto = linea.getProducto();
    Pedido pedido = pedidoSelected;

    String productoid = linea.getProducto().getIdproducto();
    String nombre = modelo.getProductoNombre(productoid);

    Object[] row = {
      linea.getPedido().getId(),
      linea.getIdlinea(),
      productoid,
      nombre,
      linea.getCantidad(),
      linea.getImporte(),
      modelo.getTotalLinea(linea.getImporte(), linea.getCantidad()),
      linea.getFecha()
    };

    ((TableModelLinea) jTable2Linea.getModel()).addRow(row);


    modelo.addLinea(linea, pedido, producto);

    jLabel4Totalpedido.setText(modelo.calculaTotalPedido(pedido));
  }

  private void borrarLinea() {

    JTable jTable2Linea = vPedido1.getJTable2Linea();
    JLabel jLabel4Totalpedido = vPedido1.getjLabel4Totalpedido();

    int selec = jTable2Linea.getSelectedRow();

    if (selec >= 0) { // Si se ha seleccionado algo.
      String idlinea = (String) jTable2Linea.getModel().getValueAt(selec, 1);
      int opc = JOptionPane.showConfirmDialog(vPedido1, "Desea borrar Linea: " + idlinea + " ", "Aviso", JOptionPane.WARNING_MESSAGE);
      if (opc == 0) { // Se pulsa si.
        Linea linea = modelo.getLinea(idlinea);
        modelo.delLinea(linea);
        ((TableModelLinea) jTable2Linea.getModel()).removeRow(selec);
        Pedido pedido = linea.getPedido();
        jLabel4Totalpedido.setText(modelo.calculaTotalPedido(pedido));
      }
    }

  }

  private void jTable1PedidoMouseClicked(MouseEvent evt) {
    // http://saforas.wordpress.com/2011/01/06/codigo-java-evento-mouseclicked-en-jtable/

    JTable jTable1Pedido = vPedido1.getJTable1Pedido();
    JTable jTable2Linea = vPedido1.getJTable2Linea();
    JLabel jLabel4Totalpedido = vPedido1.getjLabel4Totalpedido();

    int row = jTable1Pedido.rowAtPoint(evt.getPoint());
    if (row >= 0 && jTable1Pedido.isEnabled()) {
      idpedidoSelected = jTable1Pedido.getValueAt(row, 0).toString();

      if (Constantes.LOG) {
        System.out.println("Click id" + idpedidoSelected);
      }

      ((TableModelLinea) jTable2Linea.getModel()).Actualiza(idpedidoSelected);
      pedidoSelected = modelo.getPedido(idpedidoSelected);
      jLabel4Totalpedido.setText(modelo.calculaTotalPedido(pedidoSelected));



    }

  }
}
