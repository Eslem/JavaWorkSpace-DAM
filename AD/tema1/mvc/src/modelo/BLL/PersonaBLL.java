package modelo.BLL;

import modelo.dao.PersonaDAO;
import modelo.POJO.Persona;
import controlador.Controlador;

public class PersonaBLL {
	
    private Controlador miControlador;

    public void setControlador(Controlador miControlador) {
        this.miControlador = miControlador;
    }

    public void validarPersona(Persona miPersona) {
        try{
            PersonaDAO miPersonaDAO;
            if (miPersona.getId() > 0) {
                miPersonaDAO = new PersonaDAO(miControlador);
                Persona existePersona = miPersonaDAO.buscarPersona(miPersona);
                if (existePersona==null)
                  miPersonaDAO.registrarPersona(miPersona);
                else
                  miControlador.mostrarError(10);
            }else {
                miControlador.mostrarAdvertencia(1);
            }
        }catch(Exception e){
            System.out.println(e);
            miControlador.mostrarError(3);
        }
    }
    
    public boolean validarModificacion(Persona miPersona) {
        boolean verificacion = false;
        try{
            PersonaDAO miPersonaDao;
            if (miPersona.getNombre().length()>5) {
                miPersonaDao = new PersonaDAO(miControlador);
                miPersonaDao.modificarPersona(miPersona);
                verificacion = true;
            }else{
                miControlador.mostrarAdvertencia(4);
                verificacion = false;
            }
        }catch(Exception e){
            System.out.println(e);
            miControlador.mostrarError(4);
        }finally{
            return verificacion;
        }
    }    
    
    public Persona validarConsulta(Persona miPersona) {
        PersonaDAO miPersonaDAO;
        Persona persona = null;

        try {
            int codigo=miPersona.getId();	
            if (codigo > 0) {
                miPersonaDAO = new PersonaDAO(miControlador);
                persona = miPersonaDAO.buscarPersona(miPersona);						
            }else{
                miControlador.mostrarAdvertencia(1);
            }
        }catch (NumberFormatException e) {
            miControlador.mostrarError(2);
        } catch (Exception e) {
            miControlador.mostrarError(3);                    
        }
        return persona;
    }
    
    public void validarEliminacion(Persona miPersona) {
        try{
            PersonaDAO miPersonaDao=new PersonaDAO(miControlador);
            miPersonaDao.eliminarPersona(miPersona);
        }catch(Exception e){
            System.out.println(e);
            miControlador.mostrarError(5);
        }                
    }
}
