/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.BLL;

import POJO.Alumno;
import controlador.Controlador;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DAO.DAOFactory;
import modelo.DAO.POJODAO;

/**
 *
 * @author loren
 */
public class AlumnoBLL {
    
    private POJODAO miAlumnoDAO;
    private Controlador miControlador;
    private int resourceType;
    
    public AlumnoBLL(){
        super();
    }
    
    public void setControlador(Controlador miControlador){
        this.miControlador = miControlador;
    }
    
    public void addAlumno(Alumno alumno){
        if(alumno.getEdad()<18){
            this.miControlador.showErrorView("La edad del alumno no es correcta");
        }else{
            this.miAlumnoDAO.add(alumno);
        }
    }

    public ArrayList listAlumnos() {
        return this.miAlumnoDAO.list();
    }

    public Alumno findAlumno(Alumno alumno) {
        alumno = (Alumno) this.miAlumnoDAO.find(alumno);
        if (alumno==null){
            this.miControlador.showErrorView("No existe el alumno");
            return null;
        }else{
            return alumno;
        }
    }

    public void updateAlumno(Alumno alumno) {
        this.miAlumnoDAO.update(alumno);
    }

    public void deleteAlumno(Alumno alumno) {
        this.miAlumnoDAO.delete(alumno);
    }

    public void close() {
        try{
            DAOFactory.getDAOFactory(resourceType).closeDAOFactory();
        }catch(Exception e){
            miControlador.showErrorView("Error al cerrar la base de datos.");
        }
    }

    public void setTypeSource(int opcion) throws Exception {
        switch (opcion){
                case 1:
                    this.miAlumnoDAO = (modelo.DAO.FileText.AlumnoDAO) DAOFactory.getDAOFactory(DAOFactory.FILETEXT).getAlumnoDAO(); 
                    try {
                        DAOFactory.getDAOFactory(DAOFactory.FILETEXT).openDAOFactory();
                    } catch (Exception ex) {
                        this.miControlador.showErrorView("Error en la apertura de la base de datos.");
                    }    
                    this.resourceType=DAOFactory.FILETEXT;
                    break;
                case 2:
                    this.miAlumnoDAO = (modelo.DAO.MySQL.AlumnoDAO) DAOFactory.getDAOFactory(DAOFactory.MySQL).getAlumnoDAO(); 
                    try {
                        DAOFactory.getDAOFactory(DAOFactory.MySQL).openDAOFactory();
                    } catch (Exception ex) {
                        this.miControlador.showErrorView("Error en la apertura de la base de datos.");
                    }  
                    this.resourceType=DAOFactory.MySQL;
                    break;
                case 3:
                    this.miAlumnoDAO = (modelo.DAO.FileBuffered.AlumnoDAO) DAOFactory.getDAOFactory(DAOFactory.FILEBUFFERED).getAlumnoDAO(); 
                    try {
                        DAOFactory.getDAOFactory(DAOFactory.FILEBUFFERED).openDAOFactory();
                    } catch (Exception ex) {
                        this.miControlador.showErrorView("Error en la apertura de la base de datos.");
                    }  
                    this.resourceType=DAOFactory.FILEBUFFERED;
                    break;                    
                default:
                    this.miControlador.showErrorView("No es correcto el acceso seleccionado");
        }
        this.miControlador.startMainMenu();
    }
    
}
