/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fabricas;

import interfaces.Vehiculo;
import interfaces.VehiculoDeTransporte;
import javax.swing.JOptionPane;
import pojo.Taxi;

/**
 *
 * @author loren
 */
public class FabricaTaxis implements VehiculoDeTransporte{
 @Override
 public Vehiculo crearVehiculo() {
  Taxi miTaxi=new Taxi();
  miTaxi.setCodigo(miTaxi.generarCodigo());
  JOptionPane.showMessageDialog(null, "Se ha creado un nuevo Objeto Taxi");
  return miTaxi;
 }
}