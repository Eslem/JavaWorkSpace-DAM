/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

/**
 *
 * @author loren
 */
public class Organiza {
 /**
  * Metodo para calcular el mayor de entre 2 numeros
  * @param a
  * @param b
  * @return
  */
 public double calculaMayor(double a, double b){
  if (a > b)
   return a;
  else
         return b;
 }
 
 /**
  * Metodo para calcular el menor de entre 2 numeros
  * @param a
  * @param b
  * @return
  */
 public double calculaMenor(double a, double b) {
  if (a < b)
    return a;
  else
   return b;
 }
}