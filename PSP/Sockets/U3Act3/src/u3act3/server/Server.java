/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u3act3.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Slem
 */
public class Server {

    private int port = 5555;

    public Server() {

    }

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", port);
            serverSocket.bind(addr);
            System.out.println("Quedo a la espera de mensajes de los clientes");
            
            while(true){
                Socket newSocket = serverSocket.accept();
                ConnectionThread connectionThread = new ConnectionThread(newSocket);
                connectionThread.start();
            }
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
