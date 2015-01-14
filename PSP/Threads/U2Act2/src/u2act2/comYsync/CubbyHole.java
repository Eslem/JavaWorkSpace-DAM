/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act2.comYsync;

/**
 *
 * @author eslem
 */
public class CubbyHole {

    private int contents;
    private boolean available = false;

    public synchronized int get() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        available = false;
        notify();
        return contents;

    }

    public synchronized void put(int value) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        available = true;
        notify();
        contents = value;
    }

}
