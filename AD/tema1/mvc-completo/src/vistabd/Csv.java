/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistabd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import modelodatos.Cliente;
import modelodatos.Configura;
import modelodatos.Linea;
import modelodatos.Pedido;
import modelodatos.Producto;
import util.Constantes;

/**
 *
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
public class Csv implements Bd {


  /*
   * Para borrar un cliente
   * Leemos el fichero sus datos menos el que
   * queremos borrar, Despues se graba lo leido.
   */
  public void borrarCliente(Cliente cli) {

    String id_ = cli.getId();

    ArrayList<Cliente> datos = new ArrayList();
    File fs = new File(Constantes.FICHEROCLIENTES);

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        while ((linea = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(linea, ";");

          String id;
          String nombre;
          String email;

          id = stringTokenizer.nextToken();
          nombre = stringTokenizer.nextToken();
          email = stringTokenizer.nextToken();

          // public Cliente(String id, String nombre, String email, Set pedidos) {
          Cliente cliente = new Cliente(id, nombre, email, null);

          if (!id.equals(id_)) {
            datos.add(cliente);
          }

        } // while

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    grabarCliente1(datos);
  }

  /*
   * Lee de un fichero csv los datos y los almacena
   * en un arrayList para el modelo.
   */
  public ArrayList leerClientes1() {
    ArrayList<Cliente> datos = new ArrayList();
    File fs = new File(Constantes.FICHEROCLIENTES);

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        if (Constantes.LOG) {
          System.out.println("Cargando Clientes del fichero " + Constantes.FICHEROCLIENTES);
        }

        while ((linea = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(linea, ";");

          String id;
          String nombre;
          String email;

          id = stringTokenizer.nextToken();
          nombre = stringTokenizer.nextToken();
          email = stringTokenizer.nextToken();

          Cliente cliente = new Cliente(id, nombre, email, null);

          datos.add(cliente);

        } // while

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    return datos;
  }

  public void grabarCliente1(ArrayList<Cliente> clientes) {

    File fs = new File(Constantes.FICHEROCLIENTES);
    try {
      FileWriter fw = new FileWriter(fs);

      Iterator it = clientes.iterator();

      while (it.hasNext()) {

        Cliente cliente = (Cliente) it.next();

        String id = cliente.getId();
        String nombre = cliente.getNombre();
        String email = cliente.getEmail();

        fw.write(id, 0, id.length());
        fw.write(";", 0, 1);
        fw.write(nombre, 0, nombre.length());
        fw.write(";", 0, 1);
        fw.write(email, 0, email.length());
        fw.write("\r\n");
      }
      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public ArrayList leerProductos1() {

    ArrayList<Producto> datos = new ArrayList();
    File fs = new File(Constantes.FICHEROPRODUCTOS);

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        if (Constantes.LOG) {
          System.out.println("Cargando Productos del fichero " + Constantes.FICHEROPRODUCTOS);
        }


        while ((linea = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(linea, ";");

          String id;
          String nombre;
          String importe;

          id = stringTokenizer.nextToken();
          nombre = stringTokenizer.nextToken();
          importe = stringTokenizer.nextToken();

          Producto producto = new Producto(id, nombre, importe);


          datos.add(producto);

        } // while

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    return datos;
  }

  public void grabarProductos3(ArrayList datos) {

    File fs = new File(Constantes.FICHEROPRODUCTOS);
    try {
      FileWriter fw = new FileWriter(fs);


      Iterator it = datos.iterator();

      while (it.hasNext()) {

        Object[] fila = (Object[]) it.next();

        String id = (String) fila[0];
        String nombre = (String) fila[1];
        String importe = (String) fila[2];

        fw.write(id, 0, id.length());
        fw.write(";", 0, 1);
        fw.write(nombre, 0, nombre.length());
        fw.write(";", 0, 1);
        fw.write(importe, 0, importe.length());
        fw.write("\r\n");
      }
      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public ArrayList<Pedido> leerPedidos1() {
    ArrayList<Pedido> datos = new ArrayList<>();
    File fs = new File(Constantes.FICHEROPEDIDOS);

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        if (Constantes.LOG) {
          System.out.println("Cargando Pedidos del fichero " + Constantes.FICHEROPEDIDOS);
        }

        while ((linea = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(linea, ";");

          String idpedido;
          String idcliente;
          String fechapedido;

          idpedido = stringTokenizer.nextToken();
          idcliente = stringTokenizer.nextToken();
          fechapedido = stringTokenizer.nextToken();


          Cliente cliente = new Cliente();
          cliente.setId(idcliente);

          Pedido pedido = new Pedido(idpedido, cliente, fechapedido, null);

          datos.add(pedido);

        } // while

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    return datos;
  }

  public void grabarPedido(ArrayList datosPedido) {
    File fs = new File(Constantes.FICHEROPEDIDOS);
    try {
      FileWriter fw = new FileWriter(fs);


      Iterator it = datosPedido.iterator();

      while (it.hasNext()) {

        Object[] fila = (Object[]) it.next();


        String idpedido = (String) fila[0];
        String idcliente = (String) fila[1];
        String fechapedido = (String) fila[2];

        fw.write(idpedido, 0, idpedido.length());
        fw.write(";", 0, 1);
        fw.write(idcliente, 0, idcliente.length());
        fw.write(";", 0, 1);
        fw.write(fechapedido, 0, fechapedido.length());
        fw.write("\r\n");
      }
      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public void grabarPedido1(ArrayList<Pedido> pedidos) {
    File fs = new File(Constantes.FICHEROPEDIDOS);
    try {
      FileWriter fw = new FileWriter(fs);

      Iterator it = pedidos.iterator();

      while (it.hasNext()) {

        Pedido pedido = (Pedido) it.next();

        String idpedido = pedido.getId();
        String idcliente = pedido.getCliente().getId();
        String fechapedido = pedido.getFecha();

        fw.write(idpedido, 0, idpedido.length());
        fw.write(";", 0, 1);
        fw.write(idcliente, 0, idcliente.length());
        fw.write(";", 0, 1);
        fw.write(fechapedido, 0, fechapedido.length());
        fw.write("\r\n");
      }
      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public void grabarLinea(ArrayList datosLinea) {
    File fs = new File(Constantes.FICHEROLINEAS);
    try {
      FileWriter fw = new FileWriter(fs);


      Iterator it = datosLinea.iterator();

      while (it.hasNext()) {

        Object[] fila = (Object[]) it.next();


        String idpedido = (String) fila[0];
        String idlinea = (String) fila[1];
        String idarticulo = (String) fila[2];
        String cantidad = (String) fila[3];
        String precioventa = (String) fila[4];
        String fechalinea = (String) fila[5];


        fw.write(idpedido, 0, idpedido.length());
        fw.write(";", 0, 1);
        fw.write(idlinea, 0, idlinea.length());
        fw.write(";", 0, 1);
        fw.write(idarticulo, 0, idarticulo.length());
        fw.write(";", 0, 1);
        fw.write(cantidad, 0, cantidad.length());
        fw.write(";", 0, 1);
        fw.write(precioventa, 0, precioventa.length());
        fw.write(";", 0, 1);
        fw.write(fechalinea, 0, fechalinea.length());
        fw.write("\r\n");
      }
      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public void grabarLinea1(ArrayList datosLinea) {
    File fs = new File(Constantes.FICHEROLINEAS);
    try {
      FileWriter fw = new FileWriter(fs);

      Iterator it = datosLinea.iterator();

      while (it.hasNext()) {

        Linea linea = (Linea) it.next();

        String idpedido = linea.getPedido().getId();
        String idlinea = linea.getIdlinea();
        String idarticulo = linea.getProducto().getIdproducto();
        String cantidad = linea.getCantidad();
        String precioventa = linea.getImporte();
        String fechalinea = linea.getFecha();

        fw.write(idpedido, 0, idpedido.length());
        fw.write(";", 0, 1);
        fw.write(idlinea, 0, idlinea.length());
        fw.write(";", 0, 1);
        fw.write(idarticulo, 0, idarticulo.length());
        fw.write(";", 0, 1);
        fw.write(cantidad, 0, cantidad.length());
        fw.write(";", 0, 1);
        fw.write(precioventa, 0, precioventa.length());
        fw.write(";", 0, 1);
        fw.write(fechalinea, 0, fechalinea.length());
        fw.write("\r\n");
      }
      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public ArrayList<Linea> leerLineas1() {

    ArrayList<Linea> datos = new ArrayList<>();
    File fs = new File(Constantes.FICHEROLINEAS);

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String lineafichero;

        if (Constantes.LOG) {
          System.out.println("Cargando Lineas del fichero " + Constantes.FICHEROLINEAS);
        }

        while ((lineafichero = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(lineafichero, ";");

          String idpedido;
          String idlinea;
          String idarticulo;
          String cantidad;
          String precioventa;
          String fechalinea;

          idpedido = stringTokenizer.nextToken();
          idlinea = stringTokenizer.nextToken();
          idarticulo = stringTokenizer.nextToken();
          cantidad = stringTokenizer.nextToken();
          precioventa = stringTokenizer.nextToken();
          fechalinea = stringTokenizer.nextToken();

          Producto producto = new Producto();
          producto.setIdproducto(idarticulo);

          Pedido pedido = new Pedido();
          pedido.setId(idpedido);


          // Linea(String idlinea, Producto producto, Pedido pedido, String importe, String cantidad, String fecha)

          Linea linea = new Linea(idlinea, producto, pedido, cantidad, precioventa, fechalinea);

          datos.add(linea);

        } // while

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if

    return datos;

  }

  public void insertCliente(Cliente cliente) {
    File fs = new File(Constantes.FICHEROCLIENTES);
    Boolean anadiralfinal = true;
    try {
      FileWriter fw = new FileWriter(fs, anadiralfinal);

      String id = cliente.getId();
      String nombre = cliente.getNombre();
      String email = cliente.getEmail();

      fw.write(id, 0, id.length());
      fw.write(";", 0, 1);
      fw.write(nombre, 0, nombre.length());
      fw.write(";", 0, 1);
      fw.write(email, 0, email.length());
      fw.write("\r\n");

      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public void insertProducto(Producto producto) {
    File fs = new File(Constantes.FICHEROPRODUCTOS);
    Boolean anadiralfinal = true;
    try {
      FileWriter fw = new FileWriter(fs, anadiralfinal);

      String id = producto.getIdproducto();
      String nombre = producto.getNombre();
      String importe = producto.getPrecio();

      fw.write(id, 0, id.length());
      fw.write(";", 0, 1);
      fw.write(nombre, 0, nombre.length());
      fw.write(";", 0, 1);
      fw.write(importe, 0, importe.length());
      fw.write("\r\n");

      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public void borrarProducto(Producto producto_) {
    ArrayList<Producto> datos = new ArrayList();
    File fs = new File(Constantes.FICHEROPRODUCTOS);

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        while ((linea = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(linea, ";");

          String id;
          String nombre;
          String importe;

          id = stringTokenizer.nextToken();
          nombre = stringTokenizer.nextToken();
          importe = stringTokenizer.nextToken();

          Producto producto = new Producto(id, nombre, importe);

          if (!id.equals(producto_.getIdproducto())) {
            datos.add(producto);
          }

        } // while

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    grabarProductos3(datos);
  }

  public void borrarPedido(Pedido pedido_) {

    int res = 0;
    ArrayList<Pedido> datos = new ArrayList();
    File fs = new File(Constantes.FICHEROPEDIDOS);
    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        while ((linea = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(linea, ";");
          String idpedido;
          String idcliente;
          String fechapedido;
          idpedido = stringTokenizer.nextToken();
          idcliente = stringTokenizer.nextToken();
          fechapedido = stringTokenizer.nextToken();


          Cliente cliente = new Cliente();
          cliente.setId(idcliente);
          Pedido pedido = new Pedido(idpedido, cliente, fechapedido, null);
          if (!idpedido.equals(pedido_.getId())) {
            datos.add(pedido);
          }
        } // while
        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    grabarPedido1(datos);

  }

  public void insertPedido(Pedido pedido) {
    File fs = new File(Constantes.FICHEROPEDIDOS);
    Boolean anadiralfinal = true;
    try {
      FileWriter fw = new FileWriter(fs, anadiralfinal);

      String idpedido = pedido.getId();
      String idcliente = pedido.getCliente().getId();
      String fecha = pedido.getFecha();

      if (Constantes.LOG) {
        System.out.println("Insertando pedido " + idpedido + " de " + Constantes.FICHEROPEDIDOS);
      }


      fw.write(idpedido, 0, idpedido.length());
      fw.write(";", 0, 1);
      fw.write(idcliente, 0, idcliente.length());
      fw.write(";", 0, 1);
      fw.write(fecha, 0, fecha.length());
      fw.write("\r\n");

      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public void insertLinea(Linea linea) {

    File fs = new File(Constantes.FICHEROLINEAS);
    Boolean anadiralfinal = true;
    try {
      FileWriter fw = new FileWriter(fs, anadiralfinal);

      String idpedido = linea.getPedido().getId();
      String idlinea = linea.getIdlinea();
      String idproducto = linea.getProducto().getIdproducto();
      String cantidad = linea.getCantidad();
      String precio = linea.getImporte();
      String fecha = linea.getFecha();

      if (Constantes.LOG) {
        System.out.println("Insertando linea " + idlinea + " de " + Constantes.FICHEROLINEAS);
      }

      fw.write(idpedido, 0, idpedido.length());
      fw.write(";", 0, 1);
      fw.write(idlinea, 0, idlinea.length());
      fw.write(";", 0, 1);
      fw.write(idproducto, 0, idproducto.length());
      fw.write(";", 0, 1);
      fw.write(cantidad, 0, cantidad.length());
      fw.write(";", 0, 1);
      fw.write(precio, 0, precio.length());
      fw.write(";", 0, 1);
      fw.write(fecha, 0, fecha.length());
      fw.write("\r\n");

      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }
  }

  public void borrarLinea(Linea linea_) {

    String id_ = linea_.getIdlinea();

    ArrayList<Linea> datos = new ArrayList();
    File fs = new File(Constantes.FICHEROLINEAS);

    if (Constantes.LOG) {
      System.out.println("Borrando linea " + id_ + " de " + Constantes.FICHEROLINEAS);
    }

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String lineafichero;

        while ((lineafichero = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(lineafichero, ";");

          String idpedido;
          String idlinea;
          String idarticulo;
          String cantidad;
          String precioventa;
          String fechalinea;

          idpedido = stringTokenizer.nextToken();
          idlinea = stringTokenizer.nextToken();
          idarticulo = stringTokenizer.nextToken();
          cantidad = stringTokenizer.nextToken();
          precioventa = stringTokenizer.nextToken();
          fechalinea = stringTokenizer.nextToken();


          Producto producto = new Producto();
          producto.setIdproducto(idarticulo);

          Pedido pedido = new Pedido();
          pedido.setId(idpedido);

          // Linea(String idlinea, Producto producto, Pedido pedido, String importe, String cantidad, String fecha)

          Linea linea = new Linea(idlinea, producto, pedido, cantidad, precioventa, fechalinea);

          if (!idlinea.equals(id_)) {
            datos.add(linea);
          }

        } // while

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    grabarLinea1(datos);
  }

  public void desconectar() {

    if (Constantes.LOG) {
      System.out.println("Cerrando Ficheros");
    }
  }

  @Override
  public void modifyCliente(Cliente cliente_) {

    String id_ = cliente_.getId();

    ArrayList<Cliente> datos = new ArrayList();
    File fs = new File(Constantes.FICHEROCLIENTES);

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        if (Constantes.LOG) {
          System.out.println("Modificando fichero " + Constantes.FICHEROCONFIGURA);
        }

        while ((linea = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(linea, ";");

          String id;
          String nombre;
          String email;

          id = stringTokenizer.nextToken();
          nombre = stringTokenizer.nextToken();
          email = stringTokenizer.nextToken();

          // public Cliente(String id, String nombre, String email, Set pedidos) {
          Cliente cliente = new Cliente(id, nombre, email, null);

          if (!id.equals(id_)) {
            datos.add(cliente);
          } else {
            datos.add(cliente_);
          }


        } // while

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    grabarCliente1(datos);

  }

  public Configura getConfigura() {
    Configura configura = new Configura();
    File fs = new File(Constantes.FICHEROCONFIGURA);

    if (fs.exists()) {
      try {
        StringTokenizer stringTokenizer;
        FileReader fr = new FileReader(fs);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        if (Constantes.LOG) {
          System.out.println("Cargando Configura del fichero " + Constantes.FICHEROCONFIGURA);
        }

        if ((linea = br.readLine()) != null) {
          stringTokenizer = new StringTokenizer(linea, ";");

          String id;
          String nombreaplicacion;
          String autor;
          String basedatos;
          String fuentedatos;
          String fecha;

          id = stringTokenizer.nextToken();
          nombreaplicacion = stringTokenizer.nextToken();
          autor = stringTokenizer.nextToken();
          basedatos = stringTokenizer.nextToken();
          fuentedatos = stringTokenizer.nextToken();
          fecha = stringTokenizer.nextToken();

          configura = new Configura(id, nombreaplicacion, autor, basedatos, fuentedatos, fecha);


        } // if

        if (fr != null) {
          fr.close();
        }

      } catch (IOException e) {
      }
    } // if
    return configura;
  }

  public void modifyConfigura(Configura configura) {

    File fs = new File(Constantes.FICHEROCONFIGURA);
    Boolean anadiralfinal = false;
    try {
      FileWriter fw = new FileWriter(fs, anadiralfinal);

      String id = configura.getId();
      String nombreaplicacion = configura.getNombreaplicacion();
      String autor = configura.getAutor();
      String basedatos = configura.getBasedatos();
      String fuentedatos = configura.getFuentedatos();
      String fecha = configura.getFecha();

      if (Constantes.LOG) {
        System.out.println("Modificando configura " + id + " de " + Constantes.FICHEROLINEAS);
      }

      fw.write(id, 0, id.length());
      fw.write(";", 0, 1);
      fw.write(nombreaplicacion, 0, nombreaplicacion.length());
      fw.write(";", 0, 1);
      fw.write(autor, 0, autor.length());
      fw.write(";", 0, 1);
      fw.write(basedatos, 0, basedatos.length());
      fw.write(";", 0, 1);
      fw.write(fuentedatos, 0, fuentedatos.length());
      fw.write(";", 0, 1);
      fw.write(fecha, 0, fecha.length());
      fw.write("\r\n");

      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
    }

  }
}
