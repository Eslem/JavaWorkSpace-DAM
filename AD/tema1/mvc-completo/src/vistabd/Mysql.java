/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodatos.Cliente;
import modelodatos.Configura;
import modelodatos.Linea;
import modelodatos.Pedido;
import modelodatos.Producto;
import util.Constantes;

/**
 * Fichero: Mysql.java
 *
 * @date 24-mar-2014
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
public class Mysql implements Bd {

  private Connection con = null;
  private Statement st = null;
  private ResultSet rs = null;

  public Mysql(Connection c) {
    con = conectar();

  }

  public void desconectar() {
    try {
      if (Constantes.LOG) {
        System.out.println("BDR Mysql Connexión cerrada");
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Connection conectar() {
    Connection con = null;
    try {
      //Registrando el Driver
      String driver = "com.mysql.jdbc.Driver";
      Class.forName(driver).newInstance();

      if (Constantes.LOG) {
        System.out.println("Driver Registrado correctamente");
      }

      //Abrir la conexion con la Base de Datos
      System.out.println("Conectando con la Base de datos...");
      String jdbcUrl = "jdbc:mysql://localhost:3306/prg1314finalgrupo1";
      con = DriverManager.getConnection(jdbcUrl, Constantes.BDUSUARIO, Constantes.BDPASSWORD);

      if (Constantes.LOG) {
        System.out.println("Conexion establecida con la Base de datos...");
      }


    } catch (SQLException se) {
      //Errores de JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Errores debidos al Class.forName
      e.printStackTrace();
    }//end try
    return con;
  }

  public ArrayList<Cliente> leerClientes1() {

    ArrayList<Cliente> datos = new ArrayList();

    try {
      st = con.createStatement();

      //Ejecutamos la SELECT sobre la tabla articulos
      String sql = "select id,nombre,email from Cliente";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      rs = st.executeQuery(sql);

      rs.isBeforeFirst();

      String id;
      String nombre;
      String email;

      while (rs.next()) {
        id = rs.getString("id");
        nombre = rs.getString("nombre");
        email = rs.getString("email");
        Cliente cliente = new Cliente(id, nombre, email, null);
        datos.add(cliente);
      }
    } catch (SQLException se) {
      //Errores de JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Errores de Class.forName
      e.printStackTrace();

    }//end try
    return datos;
  }

  public void insertCliente(Cliente cliente) {

    int resultado;
    String sql;

    try {
      st = con.createStatement();
      String id = cliente.getId();
      String nombre = cliente.getNombre();
      String email = cliente.getEmail();
      sql = "insert into Cliente(id,nombre,email) "
              + "values('" + id + "','" + nombre + "','" + email + "')";
      resultado = st.executeUpdate(sql);
      if (Constantes.LOG) {
        System.out.println(sql);
      }

    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ArrayList<Producto> leerProductos1() {

    ArrayList<Producto> datos = new ArrayList();
    try {
      st = con.createStatement();

      //Ejecutamos la SELECT sobre la tabla articulos
      String sql = "select idproducto,nombre,precio from Producto";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      rs = st.executeQuery(sql);

      rs.isBeforeFirst();

      String idproducto;
      String nombre;
      String precio;

      while (rs.next()) {
        idproducto = rs.getString("idproducto");
        nombre = rs.getString("nombre");
        precio = rs.getString("precio");
        Producto producto = new Producto(idproducto, nombre, precio);
        datos.add(producto);
      }
    } catch (SQLException se) {
      //Errores de JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Errores de Class.forNameCliente
      e.printStackTrace();
    }
    return datos;
  }

  public ArrayList<Pedido> leerPedidos1() {
    ArrayList<Pedido> datos = new ArrayList();

    try {
      st = con.createStatement();

      //Ejecutamos la SELECT sobre la tabla articulos
      String sql = "select p.id, p.idcliente, p.fecha, c.nombre, c.email  "
              + "from Pedido as p, Cliente as c where p.id=c.id";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      rs = st.executeQuery(sql);

      rs.isBeforeFirst();

      String idpedido;
      String idcliente;
      String fechapedido;
      String nombre;
      String email;

      while (rs.next()) {
        idpedido = rs.getString("id");
        idcliente = rs.getString("idcliente");
        fechapedido = rs.getString("fecha");
        nombre = rs.getString("nombre");
        email = rs.getString("email");

        Cliente cliente = new Cliente(idcliente, nombre, email, null);
        Pedido pedido = new Pedido(idpedido, cliente, fechapedido, null);
        datos.add(pedido);
      }
    } catch (SQLException se) {
      //Errores de JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Errores de Class.forNameCliente
      e.printStackTrace();
    }
    return datos;
  }

  public ArrayList<Linea> leerLineas1() {
    ArrayList<Linea> datos = new ArrayList();

    try {
      st = con.createStatement();

      //Ejecutamos la SELECT sobre la tabla
      String sql = "select idpedido, idlinea, importe, cantidad, fecha, idproducto from Linea";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      rs = st.executeQuery(sql);

      rs.isBeforeFirst();

      String idpedido;
      String idlinea;
      String fecha;
      String importe;
      String idproducto;
      String cantidad;

      while (rs.next()) {
        idpedido = rs.getString("idpedido");
        idlinea = rs.getString("idlinea");
        importe = rs.getString("importe");
        idproducto = rs.getString("idproducto");
        cantidad = rs.getString("cantidad");
        fecha = rs.getString("fecha");

        Producto producto = getProducto(idproducto);
        Pedido pedido = getPedido(idpedido);

        // Linea(String idlinea, Producto producto, Pedido pedido, String importe, String cantidad, String fecha)

        Linea linea = new Linea(idlinea, producto, pedido, importe, cantidad, fecha);

        datos.add(linea);
      }
    } catch (SQLException se) {
      //Errores de JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Errores de Class.forNameCliente
      e.printStackTrace();
    }
    return datos;
  }

  public void borrarCliente(Cliente cli) {
    String id_;
    id_ = cli.getId();

    try {
      String sql = "delete from Cliente where id='" + id_ + "';";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      st = con.createStatement();
      int res = st.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void insertProducto(Producto producto) {

    int resultado;
    String sql;

    try {
      st = con.createStatement();
      String idproducto = producto.getIdproducto();
      String nombre = producto.getNombre();
      String precio = producto.getPrecio();
      sql = "insert into Producto(idproducto,nombre,precio) "
              + "values('" + idproducto + "','" + nombre + "','" + precio + "')";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      resultado = st.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void borrarProducto(Producto producto_) {
    try {
      String sql = "delete from Producto where idproducto='" + producto_.getIdproducto() + "';";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      st = con.createStatement();
      int res = st.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void borrarPedido(Pedido pedido_) {
    int res = 0;
    try {
      String sql = "delete from Pedido where id='" + pedido_.getId() + "';";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      st = con.createStatement();
      res = st.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
      res = -1;
    }

  }

  @Override
  public void insertPedido(Pedido pedido) {
    int resultado;
    String sql;

    try {
      st = con.createStatement();
      String idpedido = pedido.getId();
      String idcliente = pedido.getCliente().getId();
      String fecha = pedido.getFecha();
      sql = "insert into Pedido(id,idcliente,fecha) "
              + "values('" + idpedido + "','" + idcliente + "','" + fecha + "')";

      if (Constantes.LOG) {
        System.out.println(sql);
      }

      resultado = st.executeUpdate(sql);


    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void insertLinea(Linea linea) {
    int resultado;
    String sql;

    try {
      st = con.createStatement();
      String idlinea = linea.getIdlinea();
      String idpedido = linea.getPedido().getId();
      String importe = linea.getImporte();
      String idproducto = linea.getProducto().getIdproducto();
      String cantidad = linea.getCantidad();
      String fecha = linea.getFecha();


      sql = "insert into Linea(idlinea,idpedido,importe,idproducto,cantidad,fecha) "
              + "values('" + idlinea + "','" + idpedido + "','" + importe
              + "','" + idproducto + "','" + cantidad + "','" + fecha
              + "');";
      if (Constantes.LOG) {
        System.out.println(sql);
      }

      resultado = st.executeUpdate(sql);


    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void borrarLinea(Linea linea_) {
    try {
      String sql = "delete from Linea where id='" + linea_.getIdlinea() + "';";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      st = con.createStatement();
      int res = st.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private Producto getProducto(String idarticulo) {
    Producto producto = null;
    try {
      st = con.createStatement();

      //Ejecutamos la SELECT sobre la tabla articulos
      String sql = "select idproducto,nombre,precio from Producto";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      rs = st.executeQuery(sql);

      rs.isBeforeFirst();

      String id;
      String nombre;
      String precio;

      if (rs.next()) {
        id = rs.getString("idproducto");
        nombre = rs.getString("nombre");
        precio = rs.getString("precio");
        producto = new Producto(id, nombre, precio, null);
      }

    } catch (SQLException se) {
      //Errores de JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Errores de Class.forName
      e.printStackTrace();

    }//end try
    return producto;

  }

  private Pedido getPedido(String idpedido) {
    Pedido pedido = null;
    try {
      st = con.createStatement();

      //Ejecutamos la SELECT sobre la tabla articulos
      String sql = "select id,fecha,idcliente from Pedido";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      rs = st.executeQuery(sql);

      rs.isBeforeFirst();

      String id;
      String clienteid;
      String fecha;

      if (rs.next()) {
        id = rs.getString("id");
        clienteid = rs.getString("idcliente");
        fecha = rs.getString("fecha");

        Cliente cliente;
        cliente = getCliente(clienteid);

        pedido = new Pedido(id, cliente, fecha, null);
      }

    } catch (SQLException se) {
      //Errores de JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Errores de Class.forName
      e.printStackTrace();

    }//end try
    return pedido;
  }

  private Cliente getCliente(String clienteid) {
    Cliente cliente = null;
    try {
      st = con.createStatement();

      //Ejecutamos la SELECT sobre la tabla articulos
      String sql = "select id,nombre,email from Cliente";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      rs = st.executeQuery(sql);

      rs.isBeforeFirst();

      String id;
      String nombre;
      String email;

      if (rs.next()) {
        id = rs.getString("id");
        nombre = rs.getString("nombre");
        email = rs.getString("email");

        cliente = new Cliente(id, nombre, email, null);
      }

    } catch (SQLException se) {
      //Errores de JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Errores de Class.forName
      e.printStackTrace();

    }//end try
    return cliente;
  }

  @Override
  public void modifyCliente(Cliente cliente) {
    int resultado;
    String sql;

    try {
      st = con.createStatement();
      String id = cliente.getId();
      String nombre = cliente.getNombre();
      String email = cliente.getEmail();
      sql = "update Cliente set nombre='" + nombre + "',email='" + email + "' "
              + "where id =" + "'" + id + "'";
      if (Constantes.LOG) {
        System.out.println(sql);
      }
      resultado = st.executeUpdate(sql);


    } catch (SQLException ex) {
      Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Configura getConfigura() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public void modifyConfigura(Configura configura) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
