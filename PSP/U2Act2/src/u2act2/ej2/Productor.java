/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act2.ej2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eslem
 */
public class Productor extends Thread{
    private boolean continuar = true;
    Cmatriz cmatriz;
    private int valor;
    
    public Productor(Cmatriz cmatriz){
        this.cmatriz = cmatriz;
    }
    
    @Override
    public void run(){
        while(continuar){
            valor=(int) (Math.random() * 100);
            System.out.println("Productor Thread-"+this.getId()+" almacena:"+valor);
            cmatriz.almacenar(valor);
            try {
                sleep((int) (Math.random() * 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public void terminar(){
        continuar=false;
    }
    
}
