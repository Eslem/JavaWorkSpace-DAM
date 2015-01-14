/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act2u3.a;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Slem-Destop
 */
public class Server {

    private int port;

    private int clients = 0;

    public Server(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void start() {
        try {
            System.out.println("Escucho por el puerto " + port);
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", port);
            serverSocket.bind(addr);
            
            while (clients < 3) {
                Socket newSocket = serverSocket.accept();
                clients++;
                System.out.println("Sirvo al cliente: " + clients);
                Thread serverThread = new ServerThread(clients, newSocket);
                serverThread.start();
            }
            System.out.println("El servidor no acepta mas clientes");

            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
