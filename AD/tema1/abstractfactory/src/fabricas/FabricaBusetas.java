/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fabricas;

import interfaces.Vehiculo;
import interfaces.VehiculoDeTransporte;
import javax.swing.JOptionPane;
import pojo.Buseta;

/**
 *
 * @author loren
 */
public class FabricaBusetas implements VehiculoDeTransporte{
 @Override
 public Vehiculo crearVehiculo() {
  Buseta miBuseta=new Buseta();
  miBuseta.setCodigo(miBuseta.generarCodigo());
 JOptionPane.showMessageDialog(null, "Se ha creado un nuevo Objeto Buseta");
  return miBuseta;
 }
}
