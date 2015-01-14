/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eslem
 */
public class Principal {

    static public void main(String[] args) {
// al declararlas NO comienzan
        Thread hebraA = new Thread(new Contador(1, 10, "HebraA"));
        Thread hebraB = new Thread(new Contador(20, 30, "HebraB"));
        System.out.println("Vamos a iniciar las dos hebras");
        hebraA.start();
        System.out.println("HebraA esta viva: " + hebraA.isAlive());
        hebraB.start();
        System.out.println("Hebras inicializadas");

        try {
            // hacemos un poco de tiempo antes de despedirnos
            hebraA.join();
            hebraB.join();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex.toString());
        }
        System.out.println("HebraA esta viva: " + hebraA.isAlive());
        System.out.println("Programa principal terminado");

    }
}
