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
public class Consumer extends Thread {

    private CubbyHole cubbyHole;
    private int number;

    public Consumer(CubbyHole cubbyHole, int number) {
        this.cubbyHole = cubbyHole;
        this.number = number;
    }

    @Override
    public void run() {
        int value = 0;
        for (int i = 0; i < 10; i++) {
            value = cubbyHole.get();
            System.out.println("Consumer #" + this.number + " get: "
                    + value);
            try {
                sleep((int) (Math.random() * 1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
