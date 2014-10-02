/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistabd;

import java.util.ArrayList;
import modelodatos.Cliente;
import modelodatos.Configura;
import modelodatos.Linea;
import modelodatos.Pedido;
import modelodatos.Producto;

/**
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
public interface Bd {

  // Clientes
  public ArrayList<Cliente> leerClientes1();

  public void borrarCliente(Cliente cliente);

  /**
   *
   * @param cliente
   */
  public void insertCliente(Cliente cliente);

  public void modifyCliente(Cliente cliente);

  // Productos
  public ArrayList<Producto> leerProductos1();

  public void borrarProducto(Producto producto);

  public void insertProducto(Producto producto);

  // Pedidos
  public ArrayList<Pedido> leerPedidos1();

  public void borrarPedido(Pedido pedido);

  public void insertPedido(Pedido pedido);

  // Lineas de Pedido
  public ArrayList<Linea> leerLineas1();

  public void insertLinea(Linea linea);

  public void borrarLinea(Linea linea);

  public void desconectar();

  public Configura getConfigura();

  public void modifyConfigura(Configura configura);
}
