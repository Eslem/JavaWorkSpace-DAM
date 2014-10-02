/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import javax.swing.JOptionPane;

/**
 *
 * @author loren
 */
public class Ingreso extends OperacionesMatematicas
{
 Organiza miOrganiza;
 double mayor,menor;
 public Ingreso()
 {
  miOrganiza=new Organiza();/**delega para emplear la relacion "Usa"*/
 }
 
 /**
  * Permite el ingreso de 2 numeros enteros para el posterior
  * calculo de su division
  */
 public void ingresar()
 {
  try {
  numero1=Integer.parseInt(JOptionPane.showInputDialog("valor1: "));
  numero2=Integer.parseInt(JOptionPane.showInputDialog("valor2: "));
  /**Usa miOrganiza para obtener el mayor*/ 
  mayor=miOrganiza.calculaMayor(numero1, numero2);
  /**Usa miOrganiza para obtener el menor*/
  menor=miOrganiza.calculaMenor(numero1, numero2);
  
  JOptionPane.showMessageDialog(null,"La division entre:" +
   ""+mayor+"/"+menor+" =  "+dividir(mayor, menor));
   
  } catch (Exception e) {
 JOptionPane.showMessageDialog(null, "Error en el ingreso de datos");
  }
 }
}