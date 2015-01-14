/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2act2.ej2;

import java.util.Arrays;

/**
 *
 * @author eslem
 */
public class Cmatriz {

    int[] m;
    int indProd=0;
    int indCons;
    int elementosVacios;
    int elementosLlenos;

    public Cmatriz(int val) {
        this.m = new int[val];
        elementosLlenos = 0;
        elementosVacios = val;
    }
    
    public synchronized void almacenar(int val){
        while(elementosVacios==0){
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        
        m[indProd] = val;
        
        indProd++;
        //indCons = indProd;
        
        elementosVacios--;
        elementosLlenos++;
        notify();
        
        //System.out.println("Almacenar vacios: "+elementosVacios+" llenos: "+elementosLlenos);
        
    }
    
    /*
    public synchronized int obtener(){
        int valor=0;
        while(elementosLlenos==0){
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        notify();
        
        valor=m[indCons];
        
        
        indCons--;
        indProd = indCons;     
        
        
        elementosVacios++;
        elementosLlenos--;
        
        System.out.println("Obtener vacios:"+elementosVacios+" llenos: "+elementosLlenos);
        
        return valor;
    }*/
    
    public synchronized int obtenerPrimero(){
        int valor=0;
        while(elementosLlenos==0){
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        
        
        //Siguiendo el ejemplo del administrador de impresion tengo que liberar el primero que llego por tanto
        //Obtengo siempre el valor que mas tiempo esta ahi
        valor=m[0];
        reordenarArray();
        
        indProd--;
        
        elementosVacios++;
        elementosLlenos--;
        
        //System.out.println("Obtener vacios:"+elementosVacios+" llenos: "+elementosLlenos);
        
        //Reesctructuro el array para quitar el primer elemento que ya he obtenido
        
        notify();
        return valor;
    }
    
    
    private void reordenarArray(){
        for(int x=1; x<indProd; x++){
            m[x-1]= m[x];
        }
        System.out.println("Reesctructura: "+Arrays.toString(m));
    }

}
