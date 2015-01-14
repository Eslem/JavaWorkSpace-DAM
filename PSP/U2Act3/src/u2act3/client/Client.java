/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act3.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Slem
 */
public class Client {

    private int port = 5555;
    private String adress = "localhost";
    PrintStream output;

    public Client() {
    }

    public Client(int port, String adress) {
        this.port = port;
        this.adress = adress;
    }

    public void start() {
        try {
            Socket socket = new Socket(adress, port);
            System.out.println("Abriendo conexion");
            output = new PrintStream(socket.getOutputStream());
            InputStream is = socket.getInputStream();
            //BufferedReader reader = new BufferedReader(isReader);

            byte[] mensajeServer = new byte[46];
            is.read(mensajeServer);

            System.out.println(new String(mensajeServer));

            output.println("Hola desde el cliente");

            mensajeServer = new byte[46];
            is.read(mensajeServer);

            System.out.println(new String(mensajeServer));

            output.close();
            is.close();
            socket.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sendMessage(String message) {
        if (output != null) {
            output.println(message);
        }
    }

}
