/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fabricas;

import interfaces.Vehiculo;
import interfaces.VehiculoDeTransporte;
import javax.swing.JOptionPane;
import pojo.Bus;

/**
 *
 * @author loren
 */
public class FabricaBuses implements VehiculoDeTransporte{
  public Vehiculo crearVehiculo() {
  Bus miBus=new Bus();
  miBus.setCodigo(miBus.generarCodigo());
  JOptionPane.showMessageDialog(null, "Se ha creado un nuevo Objeto Bus  ");
  return miBus;
 }
}
