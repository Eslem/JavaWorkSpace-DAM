/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act2u3.a;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Slem-Destop
 */
public class ServerThread extends Thread {

    private Socket socket;
    private int clientNumber;
    private boolean active;

    public ServerThread(int client, Socket socket) {
        this.clientNumber =client;
        this.socket = socket;
    }
    
    public void close(){
        this.active = false;
    }

    @Override
    public void run() {
        active=true;
        try {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            //OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            BufferedReader is = null;

            dos.writeChars("Hola cliente numero "+clientNumber);

            //No sabia si cerraba el socket o esperaba asi que he dejado el socket abierto esperando mensajes
            while (active) {
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Esperando mensaje...");                
                String mensajeStr = is.readLine();
                System.out.println("Mensaje recibido: " + mensajeStr);  
            }
            System.out.println("Socket Closed");
            socket.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
