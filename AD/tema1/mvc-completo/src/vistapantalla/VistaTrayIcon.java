package vistapantalla;
/*
 * VistaTrayIcon.java
 */

import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

public class VistaTrayIcon {

  private PopupMenu popup;
  private TrayIcon trayIcon;
  private SystemTray tray;
  private MenuItem exitItem;
  private MenuItem aboutItem;
  private Menu displayMenu;
  private MenuItem clienteItem;
  private MenuItem productoItem;
  private MenuItem pedidoItem;

  public VistaTrayIcon() {
    initComponents();
  }

  public void initComponents() {
    //Check the SystemTray support

    popup = new PopupMenu();
    trayIcon =
            //new TrayIcon(createImage("images/bulb.gif", "tray icon"));
            new TrayIcon(createImage("/imagenes/carrito.jpg", "tray icon"));
    tray = SystemTray.getSystemTray();

    // Create a popup menu components
    aboutItem = new MenuItem("About");
    exitItem = new MenuItem("Exit");
    displayMenu = new Menu("Mostrar");
    clienteItem = new MenuItem("Cliente");
    productoItem = new MenuItem("Producto");
    pedidoItem = new MenuItem("Pedido");

    //Add components to popup menu
    popup.add(aboutItem);
    popup.addSeparator();
    popup.add(displayMenu);
    displayMenu.add(clienteItem);
    displayMenu.add(productoItem);
    displayMenu.add(pedidoItem);
    popup.addSeparator();
    popup.add(exitItem);

    trayIcon.setPopupMenu(popup);

    try {
      tray.add(trayIcon);
    } catch (AWTException e) {
      System.out.println("TrayIcon could not be added.");
    }
    trayIcon.setImageAutoSize(true);
  }

  //Obtain the image URL
  public static Image createImage(String path, String description) {
    URL imageURL = VistaTrayIcon.class.getResource(path);

    if (imageURL == null) {
      System.err.println("Resource not found: " + path);
      return null;
    } else {
      return (new ImageIcon(imageURL, description)).getImage();
    }
  }

  public TrayIcon gettrayIcon() {
    return trayIcon;
  }

  public MenuItem getaboutItem() {
    return aboutItem;
  }

  public MenuItem getexitItem() {
    return exitItem;
  }

  public SystemTray gettray() {
    return tray;
  }

  public MenuItem getclienteItem() {
    return clienteItem;
  }

  public MenuItem getproductoItem() {
    return productoItem;
  }

  public MenuItem getpedidoItem() {
    return pedidoItem;
  }
}
