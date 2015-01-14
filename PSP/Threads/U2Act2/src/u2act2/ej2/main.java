/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act2.ej2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author eslem
 */
public class main {

    public static void main(String[] args) {
        Cmatriz matriz = new Cmatriz(10);
        Productor productor1 = new Productor(matriz);
        Consumidor consumidor1 = new Consumidor(matriz);
        System.out.println("Pulse [Entrar] para continuar y");
        System.out.println("vuelva a pulsar [Entrar] para finalizar.");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        try {
            br.readLine(); // ejecucion detenida hasta pulsar [Entrar]
            productor1.start();
            consumidor1.start();
            br.readLine(); // ejecucion detenida hasta pulsar [Entrar]
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Permitir a los hilos finalizar
        productor1.terminar();
        consumidor1.terminar();

    }
}
