/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistabd;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import controlador.Controlador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import modelodatos.Cliente;
import modelodatos.Configura;
import modelodatos.Linea;
import modelodatos.Pedido;
import modelodatos.Producto;
import util.Constantes;

/**
 * Fichero: Db4o.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 25-mar-2014
 */
public class Db4o implements Bd {

  private ObjectContainer bdoo;
  private static Logger LOGGER;

  public Db4o(ObjectContainer oc) throws IOException {
    bdoo = conectar();
    LOGGER = Logger.getLogger(Controlador.class.getName());
    definelogger();
  }

  public ObjectContainer conectar() {
    ObjectContainer oc;
    if (Constantes.LOG) {
      System.out.println("BDOO Connexión establecida");
    }
    oc = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
            Constantes.FICHERODB4O);


    return oc;
  }

  @Override
  public ArrayList<Cliente> leerClientes1() {

    ArrayList<Cliente> datos = new ArrayList();

    Query query = bdoo.query();

    Constraint constrain = query.constrain(Cliente.class);
    ObjectSet res = query.execute();

    if (Constantes.LOG) {
      System.out.println("query.constrain(Cliente.class)");
    }

    while (res.hasNext()) {
      datos.add((Cliente) res.next());
    }

    return datos;

  }

  public void borrarCliente(Cliente cliente) {

    Cliente patron = new Cliente(cliente.getId(), null, null, null);
    ObjectSet res = bdoo.queryByExample(patron);
    if (res.hasNext()) {
      Cliente aborrar = (Cliente) res.next();
      bdoo.delete(aborrar);
      if (Constantes.LOG) {
        System.out.println("Borrado  Cliente " + cliente.toString());
      }
    }
  }

  @Override
  public void modifyCliente(Cliente cliente) {
    Cliente patron = new Cliente(cliente.getId(), null, null, null);
    ObjectSet res = bdoo.queryByExample(patron);
    if (res.hasNext()) {
      Cliente encontrado = (Cliente) res.next();
      bdoo.store(encontrado);
      if (Constantes.LOG) {
        System.out.println("Modificado  Cliente " + cliente.toString());
      }
    }
  }

  @Override
  public void insertCliente(Cliente cliente) {

    bdoo.store(cliente);
    if (Constantes.LOG) {
      System.out.println("Insertado Cliente " + cliente.toString());
    }

  }

  public ArrayList<Producto> leerProductos1() {

    ArrayList<Producto> datos = new ArrayList();
    Query query = bdoo.query();
    Constraint constrain = query.constrain(Producto.class);
    ObjectSet res = query.execute();
    if (Constantes.LOG) {
      System.out.println("query.constrain(Producto.class)");
    }
    while (res.hasNext()) {
      datos.add((Producto) res.next());
    }
    return datos;
  }

  public void borrarProducto(Producto patron) {

    ObjectSet res = bdoo.queryByExample(patron);
    Producto aborrar = (Producto) res.next();
    bdoo.delete(aborrar);
    if (Constantes.LOG) {
      System.out.println("Borrado Producto " + patron.toString());
    }
  }

  public void insertProducto(Producto producto) {
    bdoo.store(producto);
    if (Constantes.LOG) {
      System.out.println("Insertado Producto " + producto.toString());
    }
  }

  public ArrayList<Pedido> leerPedidos1() {

    ArrayList<Pedido> datos = new ArrayList();
    Query query = bdoo.query();
    Constraint constrain = query.constrain(Pedido.class);
    ObjectSet res = query.execute();
    if (Constantes.LOG) {
      System.out.println("query.constrain(Pedido.class)");
    }
    while (res.hasNext()) {
      datos.add((Pedido) res.next());
    }
    return datos;

  }

  public void borrarPedido(Pedido patron) {
    int resultado = 0;
    ObjectSet res = bdoo.queryByExample(patron);
    Pedido aborrar = (Pedido) res.next();
    bdoo.delete(aborrar);
    if (Constantes.LOG) {
      System.out.println("Borrado Pedido " + patron.toString());
    }

  }

  public void insertPedido(Pedido pedido) {
    bdoo.store(pedido);
    if (Constantes.LOG) {
      System.out.println("Insertado Pedido " + pedido.toString());
    }
  }

  public ArrayList<Linea> leerLineas1() {
    ArrayList<Linea> datos = new ArrayList();
    Query query = bdoo.query();
    Constraint constrain = query.constrain(Linea.class);
    ObjectSet res = query.execute();
    if (Constantes.LOG) {
      System.out.println("query.constrain(Linea.class)");
    }
    while (res.hasNext()) {
      datos.add((Linea) res.next());
    }
    return datos;
  }

  public void insertLinea(Linea linea) {
    bdoo.store(linea);
    if (Constantes.LOG) {
      System.out.println("Insertado Linea " + linea.toString());
    }
  }

  @Override
  public void borrarLinea(Linea patron) {

    //Linea patron = new Linea(id, null, null, null, null, null);
    ObjectSet res = bdoo.queryByExample(patron);
    Linea aborrar = (Linea) res.next();
    bdoo.delete(aborrar);
    if (Constantes.LOG) {
      System.out.println("Borrado Linea " + patron.toString());
    }
  }

  @Override
  public void desconectar() {
    boolean closed = bdoo.close();
    if (Constantes.LOG) {
      System.out.println("BDOO conexión cerrada");
    }
  }

  public Configura getConfigura() {
    Configura datos = new Configura();
    Query query = bdoo.query();
    Constraint constrain = query.constrain(Configura.class);
    ObjectSet res = query.execute();
    if (Constantes.LOG) {
      System.out.println("query.constrain(Configura.class)");
    }
    if (res.hasNext()) {
      datos = (Configura) res.next();
    }
    return datos;
  }

  public void modifyConfigura(Configura configura) {
    Configura encontrado;
    Configura patron = new Configura(configura.getId(), null, null, null, null, null);
    ObjectSet res = bdoo.queryByExample(patron);
    if (res.hasNext()) {
      encontrado = (Configura) res.next();
    } else {
      encontrado = configura;
    }

    bdoo.store(encontrado);
    if (Constantes.LOG) {
      System.out.println("Modificado  Configura " + configura.toString());

    }
  }

  private void definelogger() throws IOException {
    FileHandler fileTxt = new FileHandler("LoggingDb4ojava.txt");
    SimpleFormatter formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    LOGGER.addHandler(fileTxt);
  }
}
