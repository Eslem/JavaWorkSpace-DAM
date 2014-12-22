/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos.model.bll;

import gestionalumnos.controller.MainController;
import gestionalumnos.model.dommain.Alumno;
import gestionalumnos.model.dao.AlumnoDAO;
import java.util.List;

/**
 *
 * @author eslem
 */
public class AlumnoBLL {

    MainController controller;
    AlumnoDAO alumnoDAO;

    public AlumnoBLL(MainController controller, AlumnoDAO alumnoDAO) {
        this.controller = controller;
        this.alumnoDAO = alumnoDAO;
    }

    public AlumnoBLL() {
    }

    public MainController getController() {
        return controller;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public AlumnoDAO getAlumnoDAO() {
        return alumnoDAO;
    }

    public void setAlumnoDAO(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    public List<Alumno> getAlumnos() {
        return alumnoDAO.findAll();
    }

    public void add(Alumno alumno) {
        if (alumno != null) {
            alumnoDAO.insert(alumno);
        } else {
            controller.showError("El alumno no puede estar vacio");
        }

    }

    ;
    
    public void delete(String id) {
        if (id != null) {
            alumnoDAO.delete(id);
        } else {
            controller.showError("El ndi del alumno no puede estar vacio");
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(.\\d+)?");
    }

}
