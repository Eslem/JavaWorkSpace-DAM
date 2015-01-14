/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act3.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Slem
 */
public class ConnectionThread extends Thread {

    private Socket client;

    public ConnectionThread() {
    }

    public ConnectionThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            System.out.println("Conexion con un nuevo hilo");
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            dos.writeUTF("Hola, espero tus mensajes");

            String mensajeStr = is.readLine();
            System.out.println("Mensaje enviado desde cliente: " + mensajeStr);
            sleep(10000);

            dos.writeUTF("Adios!!!");
            is.close();
            dos.close();
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                client.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}
