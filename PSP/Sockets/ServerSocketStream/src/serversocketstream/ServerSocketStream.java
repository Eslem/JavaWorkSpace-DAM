/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocketstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Eslem
 */
public class ServerSocketStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Creando socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            System.out.println("Realizando el bind");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);
            System.out.println("Aceptando conexiones ");
            
            socketAccept(serverSocket);
            socketAccept(serverSocket);
            
            System.out.println("Cerrando el socket servidor");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }// TODO code application logic here
    }

    public static void socketAccept(ServerSocket serverSocket) throws IOException {
        Socket newSocket = serverSocket.accept();
        System.out.println("Conexión recibida");
        InputStream is = newSocket.getInputStream();
        OutputStream os = newSocket.getOutputStream();

        String mensajeCliente = "Estoy escuchando para que me mandes un mensaje";
        os.write(mensajeCliente.getBytes());

        byte[] mensaje = new byte[25];
        is.read(mensaje);
        System.out.println("Mensaje recibido: " + new String(mensaje));
        System.out.println("Cerrando el nuevo socket");
        newSocket.close();
    }

}
