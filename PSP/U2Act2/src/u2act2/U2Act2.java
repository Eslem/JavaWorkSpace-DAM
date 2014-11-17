/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act2;

import u2act2.comYsync.Consumer;
import u2act2.comYsync.CubbyHole;
import u2act2.comYsync.Producer;

/**
 *
 * @author eslem
 */
public class U2Act2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CubbyHole cubbyHole = new CubbyHole();
        Producer producer = new Producer(cubbyHole, 0);
        Consumer consumer  = new Consumer(cubbyHole, 0);
        
        producer.start();
        consumer.start();
                
    }
    
}
