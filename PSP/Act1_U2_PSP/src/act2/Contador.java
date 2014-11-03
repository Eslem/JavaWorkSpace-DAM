/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act2;

/**
 *
 * @author eslem
 */
class Contador extends Thread {

    private int inicio, fin; // valor inicial y final del contador
    private String nombre; // nombre de la hebra

    public Contador(int inicio, int fin, String nombre) {
        this.inicio = inicio;
        this.fin = fin;
        this.nombre = nombre;
    }

    public void run() {
        System.out.println(nombre + " empieza...");
        for (int i = inicio; i <= fin; i++) {
            System.out.println(nombre + " dice: " + i + ". ");
            try {
                sleep(10); // paramos una centesima de segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " acaba.");
    }
}
