/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

/**
 *
 * @author eslem
 */
public class Singleton {
    public static Singleton singleton = new Singleton();
    
    public static Singleton get(){
        return singleton;
    }
}
