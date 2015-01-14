/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithread;

/**
 *
 * @author eslem
 */
public class TestTh extends Thread{
    private String nombre;
    
    public TestTh(String nombre){
        this.nombre=nombre;
    }
    
    @Override
    public void run(){
        System.out.println("Hola! soy "+nombre);
    }
}
