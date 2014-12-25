/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientsocketstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Eslem
 */
public class ClientSocketStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Creando socket cliente");
            Socket clientSocket = new Socket();
            System.out.println("Estableciendo la conexión");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            clientSocket.connect(addr);
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            
            byte[] mensajeServer = new byte[46];
            is.read(mensajeServer);
            System.out.println("Mensaje recibido del servidor:  " + new String(mensajeServer));
            
            System.out.println("Enviando mensaje");
            String mensaje = "mensaje desde el cliente";
            os.write(mensaje.getBytes());
            System.out.println("Mensaje enviado");
            System.out.println("Cerrando el socket cliente");
            clientSocket.close();
            System.out.println("Terminado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
