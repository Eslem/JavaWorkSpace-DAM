/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo;

import interfaces.Vehiculo;
import javax.swing.JOptionPane;

/**
 *
 * @author loren
 */
public class Taxi implements Vehiculo{
 private int codigo;
 public int generarCodigo()
 {
  /**Generamos un codigo aleatorio para el taxi*/
  int codigoTaxi=(int) (Math.random()*9999);
  return codigoTaxi;
 }
 public int getCodigo() {
  return codigo;
 }
 public void setCodigo(int codigo) {
  this.codigo = codigo;
 }
 @Override
 public void codigoDeVehiculo() {
  JOptionPane.showMessageDialog(null,"El Codigo del Taxi es : "+getCodigo());
 }
}