/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act4;

/**
 *
 * @author eslem
 */
public class EjemploYield {

    public static void main(String[] args) {
        X obj = new X();
        Thread t1 = new Thread(obj, "primero");
        //t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        
        X obj1 = new X();
        Thread t2 = new Thread(obj1, "segundo");
        //t2.setPriority(Thread.MIN_PRIORITY);
        t2.start();   

    }

}
