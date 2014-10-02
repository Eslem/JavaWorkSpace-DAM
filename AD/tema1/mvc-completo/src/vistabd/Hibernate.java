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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import util.Constantes;

/**
 * Fichero: Db4o.java
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 * @date 25-mar-2014
 */
public class Hibernate implements Bd {

  private Session session;

  public Hibernate() {
    session = conectar();
  }

  public Session conectar() {
    SessionFactory sessionFactory;

    if (Constantes.LOG) {
      System.out.println("Hibernate Sesion establecida");
    }

    Configuration configuration = new Configuration();
    configuration.configure();
    sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.openSession();

    return session;
  }

  @Override
  public ArrayList<Cliente> leerClientes1() {

    session.beginTransaction();
    ArrayList<Cliente> datos = (ArrayList<Cliente>) session.createQuery("from Cliente").list();
    session.createQuery("from Cliente").list();
    if (Constantes.LOG) {
      System.out.println("from Cliente");
    }
    return datos;

  }

  @Override
  public void borrarCliente(Cliente cliente) {

    session.beginTransaction();
    session.delete(cliente);
    session.getTransaction().commit();

    if (Constantes.LOG) {
      System.out.println("Borrado  Cliente " + cliente.toString());
    }

  }

  @Override
  public void modifyCliente(Cliente cliente) {

    session.beginTransaction();
    session.update(cliente);
    session.getTransaction().commit();

    if (Constantes.LOG) {
      System.out.println("Modificado  Cliente " + cliente.toString());
    }

  }

  @Override
  public void insertCliente(Cliente cliente) {

    session.beginTransaction();
    session.saveOrUpdate(cliente);
    session.getTransaction().commit();
    if (Constantes.LOG) {
      System.out.println("Insertado Cliente " + cliente.toString());
    }

  }

  public ArrayList<Producto> leerProductos1() {

    session.beginTransaction();
    ArrayList<Producto> datos = (ArrayList<Producto>) session.createQuery("from Producto").list();
    session.createQuery("from Producto").list();
    if (Constantes.LOG) {
      System.out.println("from Producto");
    }
    return datos;
  }

  public void borrarProducto(Producto producto) {

    session.beginTransaction();
    session.delete(producto);
    session.getTransaction().commit();

    if (Constantes.LOG) {
      System.out.println("Borrado  Producto " + producto.toString());
    }
  }

  public void insertProducto(Producto producto) {
    session.beginTransaction();
    session.saveOrUpdate(producto);
    session.getTransaction().commit();
    if (Constantes.LOG) {
      System.out.println("Insertado Producto " + producto.toString());
    }
  }

  public ArrayList<Pedido> leerPedidos1() {

    session.beginTransaction();
    ArrayList<Pedido> datos = (ArrayList<Pedido>) session.createQuery("from Pedido").list();
    session.createQuery("from Pedido").list();
    if (Constantes.LOG) {
      System.out.println("from Pedido");
    }
    return datos;
  }

  public void borrarPedido(Pedido pedido) {

    session.beginTransaction();
    session.delete(pedido);
    session.getTransaction().commit();

    if (Constantes.LOG) {
      System.out.println("Borrado  Producto " + pedido.toString());
    }
  }

  public void insertPedido(Pedido pedido) {
    session.beginTransaction();
    session.saveOrUpdate(pedido);
    session.getTransaction().commit();
    if (Constantes.LOG) {
      System.out.println("Insertado Pedido " + pedido.toString());
    }
  }

  public ArrayList<Linea> leerLineas1() {
    session.beginTransaction();
    ArrayList<Linea> datos = (ArrayList<Linea>) session.createQuery("from Linea").list();
    session.createQuery("from Linea").list();
    if (Constantes.LOG) {
      System.out.println("from Linea");
    }
    return datos;
  }

  public void insertLinea(Linea linea) {
    session.beginTransaction();
    session.saveOrUpdate(linea);
    session.getTransaction().commit();
    if (Constantes.LOG) {
      System.out.println("Insertado Linea " + linea.toString());
    }
  }

  public void borrarLinea(Linea linea) {

    session.beginTransaction();
    session.delete(linea);
    session.getTransaction().commit();
    if (Constantes.LOG) {
      System.out.println("Borrado  Linea " + linea.toString());
    }
  }

  @Override
  public void desconectar() {
    session.close();
    if (Constantes.LOG) {
      System.out.println("Hibernate Sesión cerrada");
    }
  }

  @Override
  public Configura getConfigura() {
    Configura configura;
    session.beginTransaction();
    ArrayList<Configura> datos = (ArrayList<Configura>) session.createQuery("from Configura").list();
    session.createQuery("from Configura").list();
    if (Constantes.LOG) {
      System.out.println("from Configura");
    }
    configura = datos.get(0);
    return configura;
  }

  @Override
  public void modifyConfigura(Configura configura) {

    session.beginTransaction();
    session.update(configura);
    session.getTransaction().commit();

    if (Constantes.LOG) {
      System.out.println("Modificado  Configura " + configura.toString());
    }
  }
}
