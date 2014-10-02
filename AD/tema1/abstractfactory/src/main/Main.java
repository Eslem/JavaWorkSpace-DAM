/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import fabricas.FabricaBuses;
import fabricas.FabricaBusetas;
import fabricas.FabricaDeVehiculos;
import fabricas.FabricaTaxis;
import javax.swing.JOptionPane;

/**
 *
 * @author loren
 */
public class Main {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args)
{
 FabricaBusetas busetas=new FabricaBusetas();
 FabricaTaxis taxi=new FabricaTaxis();
 FabricaBuses buses=new FabricaBuses();
 String cad="",salida;
cad+="Ingrese la opción correspondiente para obtener el codigo del servicio\n";
 cad+="1. Codigo servicio de Taxis\n";
 cad+="2. Codigo servicio de Buses\n";
 cad+="3. Codigo servicio de Busetas\n\n";
 try {
 do {
  try
     {
  int opcion=Integer.parseInt(JOptionPane.showInputDialog(cad));
  switch (opcion)
  {
  case 1:FabricaDeVehiculos.crearFabricaDeVehiculo(taxi);
   break;
 case 2:FabricaDeVehiculos.crearFabricaDeVehiculo(buses);
   break;
 case 3:FabricaDeVehiculos.crearFabricaDeVehiculo(busetas);
   break;
default:JOptionPane.showMessageDialog(null,"No es un valor de consultavalido");
    break;
   }
  } catch (Exception e) {
 JOptionPane.showMessageDialog(null,"No es un parametro de consulta valido");
  }
 salida=JOptionPane.showInputDialog("Desea consultar otro codigo? S/N");
  
 } while (salida.toUpperCase().equals("S"));
     } catch (Exception e) {
  JOptionPane.showMessageDialog(null,"Bye!!!");
  }
 }

}
