/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Slem-Destop
 */
public class Conector {

    Socket cliente;
    int puerto = 9000;
    BufferedReader entrada, teclado;
    PrintStream salida;

    public void iniciar() {
        try {
            teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introduce la url del servidor:");
            String url = teclado.readLine();

            cliente = new Socket(InetAddress.getByName(url), puerto);

            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            System.out.println("Introduce el mensaje:");
            String tec = teclado.readLine();

            salida = new PrintStream(cliente.getOutputStream());
            salida.println(tec);
            String msg = entrada.readLine();
            System.out.println(msg);

            entrada.close();
            salida.close();
            teclado.close();
            cliente.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
