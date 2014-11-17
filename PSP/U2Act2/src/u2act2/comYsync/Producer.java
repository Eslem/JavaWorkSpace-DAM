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
public class Producer extends Thread{

    private CubbyHole cubbyhole;
    private int number;

    public Producer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            cubbyhole.put(i);
            System.out.println("Productor #" + this.number + " put: " + i);
            try {
                sleep((int) (Math.random() * 50));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
