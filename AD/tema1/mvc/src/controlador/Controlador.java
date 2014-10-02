package controlador;

import modelo.BLL.PersonaBLL;
import modelo.POJO.Persona;
import vista.*;


public class Controlador {

    private PersonaBLL miPersonaBLL;
    private VentanaPrincipal miVentanaPrincipal;
    private VentanaPersona miVentanaPersona;
    private VentanaBuscar miVentanaBuscar;
    private VentanaError miVentanaError;
    private VentanaMensaje miVentanaMensaje;
    private VentanaConfirmacion miVentanaConfirmacion;

    public VentanaPrincipal getMiVentanaPrincipal() {
        return miVentanaPrincipal;
    }
    public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) {
        this.miVentanaPrincipal = miVentanaPrincipal;
    }
    public VentanaPersona getMiVentanaPersona() {
        return miVentanaPersona;
    }
    public void setMiVentanaPersona(VentanaPersona miVentanaPersona) {
        this.miVentanaPersona = miVentanaPersona;
    }
    public VentanaBuscar getMiVentanaBuscar() {
        return miVentanaBuscar;
    }
    public void setMiVentanaBuscar(VentanaBuscar miVentanaBuscar) {
        this.miVentanaBuscar = miVentanaBuscar;
    }
    public PersonaBLL getMiPersonaBLL() {
        return miPersonaBLL;
    }
    public void setMiPersonaBLL(PersonaBLL miPersonaBLL) {
        this.miPersonaBLL = miPersonaBLL;
    }
    public VentanaError getMiVentanaError() {
        return miVentanaError;
    }
    public void setMiVentanaError(VentanaError miVentanaError) {
        this.miVentanaError = miVentanaError;
    }          
    public VentanaMensaje getMiVentanaMensaje(){
        return this.miVentanaMensaje;
    }
    public void setMiVentanaMensaje(VentanaMensaje miVentanaMensaje){
        this.miVentanaMensaje = miVentanaMensaje;
    }
    
    public VentanaConfirmacion getMiVentanaConfirmacion(){
        return this.miVentanaConfirmacion;
    }
    
    public void setMiVentanaConfirmacion(VentanaConfirmacion miVentanaConfirmacion){
        this.miVentanaConfirmacion = miVentanaConfirmacion;
    }

    // ***********
    // METODOS PROPIOS DEL CONTROLADOR
    // ***********
    public void registrarPersona(Persona miPersona) {
        miPersonaBLL.validarPersona(miPersona);
    }

    public Persona buscarPersona(Persona miPersona) {
        return miPersonaBLL.validarConsulta(miPersona);
    }

    public boolean modificarPersona(Persona miPersona) {
        return miPersonaBLL.validarModificacion(miPersona);
    }
    public void eliminarPersona(Persona miPersona) {
        miPersonaBLL.validarEliminacion(miPersona);
    }

    public void mostrarError(int error){
        miVentanaError.mostrarError(error);
    }

    public void mostrarAdvertencia(int advertencia){
        miVentanaError.mostrarAdvertencia(advertencia);
    } 

    public void mostrarMensaje(String mensaje){
        miVentanaMensaje.mostrarMensaje(mensaje);
    }
    
    public void mostrarVentanaPersona() {
        miVentanaPersona.setVisible(true);
    }
    public void mostrarVentanaConsulta() {
        miVentanaBuscar.setVisible(true);
    }   
    
    public int mostrarConfirmacion(String mensaje){
        return miVentanaConfirmacion.mostrarConfirmacion(mensaje);
    }
}
