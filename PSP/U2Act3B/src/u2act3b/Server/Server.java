/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act3b.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Slem
 */
public class Server {

    private int port = 5555;
    private String username ="user";
    private String pass ="java";
    

    public Server() {

    }

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            byte[] ary = new byte[128];
            DatagramPacket pack = new DatagramPacket(ary, 128);
            
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Socket open");
            socket.receive(pack);
            String message = new String(pack.getData());
            
            String[] splited = message.split("\\.");
            System.out.println(splited[1]);
            if(splited[0].equals(username) && splited[1].trim().equals(pass)){
                System.out.println("El usuario y la contraseña son correctas");
            }else{
                System.out.println("El usuario y la contraseña no son correctas");
            }

            System.out.println("Socket close");
            socket.close();
        } catch (SocketException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
