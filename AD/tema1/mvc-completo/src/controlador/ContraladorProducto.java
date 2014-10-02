package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelodatos.Producto;
import modelotablas.TableModelProducto;
import vistabd.Modelo;
import vistapantalla.VistaDialogo;
import vistapantalla.VistaError;
import vistapantalla.VistaPrincipal;
import vistapantalla.VistaProducto;

/**
 * Fichero: ContraladorProducto.java
 *
 * @date 27-abr-2014
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
class ContraladorProducto {

  private Modelo modelo;
  private VistaPrincipal vPrincipal;
  private VistaProducto vProducto1;
  private Controlador controlador;
  private TableModelProducto tablemodelproducto;

  ContraladorProducto(Modelo modelo_, VistaPrincipal vPrincipal_, Controlador controlador_)
          throws Exception {
    modelo = modelo_;
    vPrincipal = vPrincipal_;
    controlador = controlador_;
    vProducto1 = new VistaProducto(modelo);
    vProducto1.setModelo(modelo);
    tablemodelproducto = new TableModelProducto(modelo);

  }

  void iniciaBotones() {

    vProducto1.getVistaProductoAnadir().setActionCommand("VistaProductoAnadir");
    vProducto1.getVistaProductoBorrar().setActionCommand("VistaProductoBorrar");
    vProducto1.getVistaProductoSalir().setActionCommand("VistaProductoSalir");
    vProducto1.getVistaProductoAnadir().addActionListener(controlador);
    vProducto1.getVistaProductoBorrar().addActionListener(controlador);
    vProducto1.getVistaProductoSalir().addActionListener(controlador);

  }

  void activaVentana() throws Exception {

    vProducto1.setTitle("PRODUCTO");
    vProducto1.setLocation(630, 132);
    vProducto1.setVisible(true);
  }

  void menu(String comando) {

    try {
      switch (comando) {
        case "VistaProducto":
          iniciaBotones();
          activaVentana();
          break;
        case "VistaProductoAnadir":
          anadir();
          break;
        case "VistaProductoBorrar":
          borrar();
          break;
        case "VistaProductoSalir":
          desactivaVentana();
          break;
      } // swith
      comando = "";
    } catch (Exception ex) {
      Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void anadir() {
    Producto producto = obtenerDatosProducto();
    modelo.addProducto(producto);
    Object[] row = {producto.getIdproducto(), producto.getNombre(), producto.getPrecio()};
    ((TableModelProducto) vProducto1.getjTable1Producto().getModel()).addRow(row);
  }

  private String obtenerProductoId(ArrayList<Producto> productos) {
    int max = 0;
    int id = 0;
    Producto pro = new Producto();
    Iterator it = productos.iterator();
    while (it.hasNext()) {
      pro = (Producto) it.next();
      id = Integer.parseInt(pro.getIdproducto());
      if (id > max) {
        max = id;
      }
    }
    return String.valueOf(id + 1);
  }

  private Producto obtenerDatosProducto() {

    VistaDialogo vd = new VistaDialogo();
    String id = obtenerProductoId(modelo.getProductos());
    String titulo = "Añadir Producto. Id: " + id;
    String nombre = vd.mostrarInputDialog(vProducto1, "Nombre: ",
            titulo, JOptionPane.QUESTION_MESSAGE);
    String importe = vd.mostrarInputDialog(vProducto1, "Importe: ",
            titulo, JOptionPane.QUESTION_MESSAGE);
    Producto producto = new Producto(id, nombre, importe);
    return producto;
  }

  private void borrar() {

    VistaDialogo vd = new VistaDialogo();
    int res = 0;
    VistaError er = new VistaError();
    JTable jtable = vProducto1.getjTable1Producto();

    int selec = jtable.getSelectedRow();
    if (selec >= 0) {
      String Id = (String) jtable.getModel().getValueAt(selec, 0);
      int opc = vd.mostrarConfirmDialog(vProducto1, "Desea borrar a: "
              + Id + " ", "Aviso", JOptionPane.WARNING_MESSAGE);
      if (opc == 0) {
        res = modelo.delProducto(selec);
        if (res < 0) {
          er.error(null, res);
        } else {
          ((TableModelProducto) jtable.getModel()).removeRow(selec);
        }
      }
    }

  }

  private void desactivaVentana() {
    vProducto1.setVisible(false);
  }
}
