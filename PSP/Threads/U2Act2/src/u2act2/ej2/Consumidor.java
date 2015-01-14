/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act2.ej2;

/**
 *
 * @author eslem
 */
public class Consumidor extends Thread{
    private boolean continuar = true;
    private Cmatriz cmatriz;
    private int valor;
    public Consumidor(Cmatriz cmatriz){
        this.cmatriz = cmatriz;
    }
    
    @Override
    public void run(){
        while(continuar){
            valor=cmatriz.obtenerPrimero();
            System.out.println("Consumidor Thread-"+this.getId()+" obtiene:"+valor);
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
