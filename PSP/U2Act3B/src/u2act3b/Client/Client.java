/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act3b.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author Slem
 */
public class Client {

    private int port = 5555;

    public Client() {
    }

    public Client(int port) {
        this.port = port;
    }

    public void start() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Introduce el usuario");
            String user = scanner.nextLine();
            
            System.out.println("Introduce la contraseña");
            String pass = scanner.nextLine();
             

            String mensaje = user+"."+pass;
            DatagramPacket datagrama = new DatagramPacket(
                    mensaje.getBytes(),
                    mensaje.getBytes().length,
                    address, 5555);
            datagramSocket.send(datagrama);
            datagramSocket.close();
        } catch (SocketException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}
