package principal;

import controlador.Controlador;
import modelo.BLL.PersonaBLL;
import vista.*;

public class Main {
	
    PersonaBLL miPersonaBLL;
    VentanaPrincipal miVentanaPrincipal;
    VentanaBuscar miVentanaBuscar;
    VentanaPersona miVentanaPersona;
    VentanaMensaje miVentanaMensajes;
    VentanaError miVentanaError;
    VentanaConfirmacion miVentanaConfirmacion;
    Controlador miControlador;
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        Main miPrincipal=new Main();
        miPrincipal.iniciar();
    }

    /**
     * Permite instanciar todas las clases con las que trabaja
     * el sistema
     */
    private void iniciar() {
        /*Se instancian las clases*/
        miVentanaPrincipal=new VentanaPrincipal();
        miVentanaPersona=new VentanaPersona();
        miVentanaBuscar= new VentanaBuscar();
        miVentanaMensajes=new VentanaMensaje();
        miVentanaError=new VentanaError();
        miVentanaConfirmacion=new VentanaConfirmacion();
        miPersonaBLL=new PersonaBLL();
        miControlador = new Controlador();

        /*Se establecen las relaciones entre clases*/
        miVentanaPrincipal.setControlador(miControlador);
        miVentanaPersona.setControlador(miControlador);
        miVentanaBuscar.setControlador(miControlador);
        miVentanaMensajes.setControlador(miControlador);
        miVentanaError.setControlador(miControlador);
        miVentanaConfirmacion.setControlador(miControlador);
        miPersonaBLL.setControlador(miControlador);

        /*Se establecen relaciones con la clase coordinador*/
        miControlador.setMiVentanaPrincipal(miVentanaPrincipal);
        miControlador.setMiVentanaPersona(miVentanaPersona);
        miControlador.setMiVentanaBuscar(miVentanaBuscar);
        miControlador.setMiVentanaMensaje(miVentanaMensajes);
        miControlador.setMiVentanaError(miVentanaError);
        miControlador.setMiPersonaBLL(miPersonaBLL);
        miControlador.setMiVentanaConfirmacion(miVentanaConfirmacion);
        
        miVentanaPrincipal.setVisible(true);
    }
}
