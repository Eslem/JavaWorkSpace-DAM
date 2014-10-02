/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Singleton;

/**
 *
 * @author loren
 */
public class ClaseLogSingleton {
 
private String contenido;
  
/**objeto Singleton*/
private static ClaseLogSingleton miLogSingleton= new ClaseLogSingleton();
 
private ClaseLogSingleton(){
 setContenido("Eventos de Usuario\n\n") ;
}
/**
 * @return the miLogSingleton
 */
public static ClaseLogSingleton getMiLogSingleton() {
 return miLogSingleton;
}
/**
 * @return the contenido
 */
public String getContenido() {
 return contenido;
}
/**
 * @param contenido the contenido to set
 */
public void setContenido(String contenido) {
 this.contenido = contenido;
}
}
