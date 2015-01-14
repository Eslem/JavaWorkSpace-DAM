/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Slem-Destop
 */
public class Conector {

    ServerSocket server;
    Socket socket;
    int puerto = 9000;
    DataOutputStream salida;
    BufferedReader entrada;
    MainConsole frame;

    public Conector(MainConsole frame) {
        this.frame = frame;
    }

    public void iniciar() {
        try {
            server = new ServerSocket(puerto);
            socket = new Socket();
            frame.append("Experando conexion de un cliente");
            frame.append("DNS: " + server.getInetAddress().getHostName());
            socket = server.accept();

            showDataConnection();

            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje = entrada.readLine();
            frame.append("He recibido del cliente " + mensaje);

            salida = new DataOutputStream(socket.getOutputStream());
            salida.writeUTF("Adios mundo");
            frame.append("Fin del programa");
            socket.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showDataConnection() {
        if (server == null) {
            frame.append("No se ha iniciado el servidor");
        } else {
            if (socket != null) {
                frame.append("Conectado a " + server.getInetAddress().getHostAddress() + " en el puerto " + server.getLocalPort() + " desde el puerto " + socket.getPort() + " de " + socket.getInetAddress().getHostAddress());
            } else {
                frame.append("No hay ningun cliente conectado");
            }
        }
    }
}
