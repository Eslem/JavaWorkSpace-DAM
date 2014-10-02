/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistabd;

import com.db4o.ObjectContainer;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import modelodatos.*;

/**
 * Fichero: Modelo.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 18-feb-2014
 */
public class Modelo {

  private ArrayList<Cliente> clientes = new ArrayList<>();
  private ArrayList<Producto> productos = new ArrayList<>();
  private ArrayList<Pedido> pedidos = new ArrayList<>();
  private ArrayList<Linea> lineas = new ArrayList<>();
  private Bd bd;
  private String fuente;
  private Connection bdr = null;
  private ObjectContainer bdoo = null;

  public Modelo(String fuente_) throws IOException {
    fuente = fuente_;

    if (fuente.equals("cvs")) {
      bd = (Bd) new Csv();
    }
    if (fuente.equals("mysql")) {
      bd = (Bd) new Mysql(bdr);
    }
    if (fuente.equals("hibernate")) {
      bd = (Bd) new Hibernate();
    }
    if (fuente.equals("db4o")) {
      bd = (Bd) new Db4o(bdoo);
    }


    clientes = inicializaClientes();
    productos = inicializaProductos();
    pedidos = inicializaPedidos();
    lineas = inicializaLineas();
  }

  /*
   * Clientes
   */
  public ArrayList<Cliente> inicializaClientes() {
    ArrayList<Cliente> resultado = new ArrayList<>();
    resultado = bd.leerClientes1();
    return resultado;
  }

  public Object[] getNombreClientes() {

    int talla = clientes.size();
    Object[] nombres = new String[talla];
    Cliente cliente;

    int i = 0;
    Iterator it = clientes.iterator();
    while (it.hasNext()) {
      cliente = (Cliente) it.next();
      nombres[i] = (Object) cliente.getNombre();
      i++;
    }
    return nombres;
  }

  public ArrayList<Cliente> getClientes() {
    return clientes;
  }

  public void addCliente(Cliente cliente) {
    clientes.add(cliente);
    bd.insertCliente(cliente);
  }

  public int delCliente(int selec) {

    int res = 0;

    Cliente cliente = clientes.get(selec);
    if (existePedidoCliente(cliente)) { // Existe pedudo con ese cliente
      res = -3;
    } else {
      clientes.remove(selec);
      bd.borrarCliente(cliente);

    }
    return res;
  }

  public int delProducto(int selec) {
    int res = 0;

    Producto producto = productos.get(selec);
    String idproducto = producto.getIdproducto();
    if (existeLineaProducto(idproducto)) { // Existe linea con ese producto
      res = -2;
    } else {
      bd.borrarProducto(producto);
      productos.remove(selec);
    }
    return res;
  }

  public int delPedido(int selec) {
    int res = 0;
    Pedido pedido = pedidos.get(selec);
    String idpedido = pedido.getId();
    if (existeLineaPedido(idpedido)) { // Existe linea con ese pedido
      res = -1;
    } else {
      bd.borrarPedido(pedido);
      pedidos.remove(selec);
    }
    return res;
  }

  public String getclienteNombre(String idcliente) {

    String id = "";
    Cliente cliente;
    String nombre = "";

    Iterator it = clientes.iterator();
    while (it.hasNext()) {
      cliente = (Cliente) it.next();
      id = cliente.getId();
      if (idcliente.equals(id)) {
        nombre = cliente.getNombre();
      }
    }
    return nombre;
  }

  /*
   * Prodcuto
   */
  public ArrayList<Producto> inicializaProductos() {
    ArrayList<Producto> resultado = new ArrayList<>();
    resultado = bd.leerProductos1();
    return resultado;
  }

  public void addProducto(Producto producto) {
    bd.insertProducto(producto);
    productos.add(producto);
  }

  public ArrayList<Producto> getProductos() {
    return productos;
  }

  /*
   * Pedido
   */
  public ArrayList<Pedido> inicializaPedidos() {
    ArrayList<Pedido> resultado = new ArrayList<>();
    resultado = bd.leerPedidos1();
    return resultado;
  }

  public ArrayList<Pedido> getPedidos() {
    return pedidos;
  }

  public void addPedido(Pedido pedido, Cliente cliente) {

    // Añadimos el pedido a la clase cliente
    pedido.setCliente(cliente);
    // Añadimos el pedido
    pedidos.add(pedido);
    bd.insertPedido(pedido);

  }

  public void addLinea(Linea linea, Pedido pedido, Producto producto) {

    linea.setPedido(pedido);
    linea.setProducto(producto);
    lineas.add(linea);
    bd.insertLinea(linea);
  }

  public ArrayList<Linea> inicializaLineas() {
    ArrayList<Linea> resultado = new ArrayList<>();
    resultado = bd.leerLineas1();
    return resultado;
  }

  public ArrayList<Linea> getLineas() {
    return lineas;
  }

  public void setLinea(Linea linea) {
    lineas.add(linea);
  }

  public void delLinea(Linea linea) {

    bd.borrarLinea(linea);
    lineasBorraId(linea);
  }

  public Bd getBd() {
    return bd;
  }

  public String getProductoNombre(String idproducto_) {
    String id = "";
    Producto producto;
    String nombre = "";

    Iterator it = productos.iterator();
    while (it.hasNext()) {
      producto = (Producto) it.next();
      id = producto.getIdproducto();
      if (idproducto_.equals(id)) {
        nombre = producto.getNombre();
      }
    }
    return nombre;
  }

  public Object[] getNombreProductos() {

    int talla = productos.size();
    Object[] nombres = new String[talla];
    Producto producto;

    int i = 0;
    Iterator it = productos.iterator();
    while (it.hasNext()) {
      producto = (Producto) it.next();
      nombres[i] = (Object) producto.getNombre();
      i++;
    }
    return nombres;
  }

  public String getIdProducto(String toString) {
    String id = "";
    String nombre = "";
    Producto producto;

    Iterator it = productos.iterator();
    while (it.hasNext()) {
      producto = (Producto) it.next();
      nombre = producto.getNombre();
      if (nombre.equals(toString)) {
        id = producto.getIdproducto();
      }
    }
    return id;
  }

  public String getProductoPrecio(String idproducto_) {
    String id = "";
    Producto producto;
    String precio = "";

    Iterator it = productos.iterator();
    while (it.hasNext()) {
      producto = (Producto) it.next();
      id = producto.getIdproducto();
      if (idproducto_.equals(id)) {
        precio = producto.getPrecio();
      }
    }
    return precio;
  }

  public String getTotalLinea(String precio, String cantidad) {

    Double precionum = Double.parseDouble(precio);
    Double cantidadnum = Double.parseDouble(cantidad);
    Double total = precionum * cantidadnum;
    return total.toString();
  }

  public String getFuente() {
    return fuente;
  }

  public String getIdPedidoDeLinea(Linea linea) {
    Pedido pedido = linea.getPedido();
    return pedido.getId();
  }

  private boolean existeLineaPedido(String idpedido) {

    boolean existe = false;
    Linea linea;
    String id;

    Iterator it = lineas.iterator();
    while (it.hasNext()) {
      linea = (Linea) it.next();
      id = getIdPedidoDeLinea(linea);
      if (idpedido.equals(id)) {
        existe = true;
        break;
      }
    }
    return existe;

  }

  private void lineasBorraId(Linea linea_) {
    Linea linea;
    String id;
    String idlinea = null;
    int posicion = 0;

    if (lineas.isEmpty()) {
      return;
    }

    Iterator it = lineas.iterator();
    while (it.hasNext()) {
      linea = (Linea) it.next();
      idlinea = linea.getIdlinea();
      if (idlinea.equals(linea_.getIdlinea())) {
        break;
      }
      posicion++;
    }
    lineas.remove(posicion);
  }

  public String calculaTotalPedido(Pedido pedido) {
    Linea linea;
    String importe;
    String cantidad;
    String idpedido;
    String total = null;
    Double suma = 0.0;

    if (lineas.isEmpty()) {
      return "0";
    }

    Iterator it = lineas.iterator();
    while (it.hasNext()) {
      linea = (Linea) it.next();
      if (linea.getPedido().getId().equals(pedido.getId())) {
        cantidad = linea.getCantidad();
        importe = linea.getImporte();
        total = getTotalLinea(importe, cantidad);
        suma = suma + Double.valueOf(total);
      }
    }

    return suma.toString();
  }

  private boolean existeLineaProducto(String idproducto_) {
    boolean existe = false;
    Linea linea;
    String id;

    Iterator it = lineas.iterator();
    while (it.hasNext()) {
      linea = (Linea) it.next();
      id = linea.getProducto().getIdproducto();
      if (idproducto_.equals(id)) {
        existe = true;
        break;
      }
    }
    return existe;
  }

  private boolean existePedidoCliente(Cliente cliente) {
    boolean existe = false;
    Pedido pedido;
    String id;
    Cliente clientex;

    Iterator it = pedidos.iterator();
    while (it.hasNext()) {
      pedido = (Pedido) it.next();
      clientex = pedido.getCliente();
      if (clientex.equals(cliente)) {
        existe = true;
        break;
      }
    }
    return existe;
  }

  public Pedido getPedido(String idpedido) {

    String id = "";
    Pedido pedido = null;
    String nombre = "";

    Iterator it = pedidos.iterator();
    while (it.hasNext()) {
      pedido = (Pedido) it.next();
      id = pedido.getId();
      if (idpedido.equals(id)) {
        break;
      }
    }
    return pedido;
  }

  public Producto getProducto(String idproducto) {

    String id = "";
    Producto producto = null;
    String nombre = "";

    Iterator it = productos.iterator();
    while (it.hasNext()) {
      producto = (Producto) it.next();
      id = producto.getIdproducto();
      if (id.equals(idproducto)) {
        break;
      }
    }
    return producto;
  }

  public String getIdCliente(String toString) {

    String id = "";
    String nombre = "";
    Cliente cliente;

    Iterator it = clientes.iterator();
    while (it.hasNext()) {
      cliente = (Cliente) it.next();
      nombre = cliente.getNombre();
      if (nombre.equals(toString)) {
        id = cliente.getId();
      }
    }
    return id;

  }

  public Cliente getCliente(String idcliente) {

    String id = "";
    Cliente cliente = null;


    Iterator it = clientes.iterator();
    while (it.hasNext()) {
      cliente = (Cliente) it.next();
      id = cliente.getId();
      if (id.equals(idcliente)) {
        break;
      }
    }
    return cliente;
  }

  public void modifyCliente(Cliente cliente, int index) {
    clientes.set(index, cliente);
    bd.modifyCliente(cliente);

  }

  public Linea getLinea(String id_) {

    String id = "";
    Linea linea = null;


    Iterator it = lineas.iterator();
    while (it.hasNext()) {
      linea = (Linea) it.next();
      id = linea.getIdlinea();
      if (id_.equals(id)) {
        break;
      }
    }
    return linea;

  }

  public void modifyConfigura(Configura configura) {
    bd.modifyConfigura(configura);
  }

  public Configura getConfigura() {
    return bd.getConfigura();

  }
}
