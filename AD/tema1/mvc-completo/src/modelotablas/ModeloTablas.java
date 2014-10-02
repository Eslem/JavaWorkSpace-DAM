/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelotablas;

import java.util.ArrayList;
import java.util.Iterator;
import modelodatos.*;
import vistabd.Modelo;

/**
 * Fichero: ModeloTablas.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 06-mar-2014
 *
 * Esta clase transforma la lista de objetos en un vector de de
 * elementos que se pueden poner en un Modelo de Tabla.
 *
 */
public class ModeloTablas {

  public ArrayList leerTablaModeloCliente(ArrayList<Cliente> clientes) {

    ArrayList datos = new ArrayList();


    Iterator it;
    it = clientes.iterator();
    while (it.hasNext()) {
      Cliente cliente = (Cliente) it.next();
      String id;
      String nombre;
      String email;

      id = cliente.getId();
      nombre = cliente.getNombre();
      email = cliente.getEmail();

      Object[] fila = new Object[3];
      fila[0] = id;
      fila[1] = nombre;
      fila[2] = email;

      datos.add(fila);

    } // while
    return datos;

  }

  public ArrayList leerTablaModeloProducto(ArrayList<Producto> productos) {

    ArrayList datos = new ArrayList();
    Iterator it;
    it = productos.iterator();
    while (it.hasNext()) {
      Producto producto = (Producto) it.next();
      String id;
      String nombre;
      String importe;

      id = producto.getIdproducto();
      nombre = producto.getNombre();
      importe = producto.getPrecio();

      Object[] fila = new Object[3];
      fila[0] = id;
      fila[1] = nombre;
      fila[2] = importe;

      datos.add(fila);

    } // while
    return datos;

  }

  public ArrayList leerTablaModeloPedido(Modelo modelo) {
    ArrayList datos = new ArrayList();
    Iterator it;
    it = modelo.getPedidos().iterator();
    while (it.hasNext()) {

      Pedido pedido = (Pedido) it.next();

      String idpedido;
      String idcliente;
      String nombre;
      String fecha;

      idpedido = pedido.getId();
      idcliente = pedido.getCliente().getId();
      nombre = pedido.getCliente().getNombre();
      fecha = pedido.getFecha();

      Object[] fila = new Object[4];
      fila[0] = idpedido;
      fila[1] = idcliente;
      fila[2] = nombre;
      fila[3] = fecha;

      datos.add(fila);
    } // while
    return datos;
  }

  public ArrayList leerTablaModeloLinea(Modelo modelo, ArrayList<Linea> lineas, String idpedido_) {

    ArrayList datos = new ArrayList();

    Iterator it;
    it = lineas.iterator();
    while (it.hasNext()) {
      Linea linea = (Linea) it.next();

      Pedido pedido = linea.getPedido();
      String idpedido = pedido.getId();
      if (idpedido.equals(idpedido_)) {

        String idlinea = linea.getIdlinea();

        Producto producto = linea.getProducto();
        String idproducto = producto.getIdproducto();
        String nombre = modelo.getProductoNombre(idproducto);
        String cantidad = linea.getCantidad();
        String precio = linea.getImporte();
        String fecha = linea.getFecha();


        Object[] fila = new Object[8];
        fila[0] = idpedido;
        fila[1] = idlinea;
        fila[2] = idproducto;
        fila[3] = nombre;
        fila[4] = cantidad;
        fila[5] = precio;
        fila[6] = modelo.getTotalLinea(precio, cantidad);
        fila[7] = fecha;

        datos.add(fila);
      } // if

    } // while
    return datos;

  }
}
