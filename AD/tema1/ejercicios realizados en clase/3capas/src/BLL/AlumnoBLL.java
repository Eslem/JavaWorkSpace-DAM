/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAO.AlumnoDAO;
import POJO.Alumno;
import View.AlumnoVista;
import java.util.ArrayList;

/**
 *
 * @author loren
 */
public class AlumnoBLL {
    
    private AlumnoDAO miAlumnoDAO;
    private AlumnoVista miAlumnoVista;
    
    public AlumnoBLL(AlumnoVista alumnoVista){
        super();
        miAlumnoDAO = new AlumnoDAO();
        miAlumnoVista = alumnoVista;
    }
    
    public void altaAlumno(Alumno alumno){
        if(alumno.getEdad()<18){
            miAlumnoVista.mostrarError("La edad del alumno no es correcta");
        }else{
            miAlumnoDAO.altaAlumno(alumno);
        }
    }

    public ArrayList<Alumno> dameListaAlumnos() {
        return miAlumnoDAO.dameListaAlumnos();
    }
    
    public void mostrarError(String mensaje){
        miAlumnoVista.mostrarError(mensaje);
    }

    public Alumno buscarAlumno(Alumno alumno) {
        alumno = this.miAlumnoDAO.buscarAlumno(alumno);
        if (alumno==null){
            miAlumnoVista.mostrarError("No existe el alumno");
            return null;
        }else{
            return alumno;
        }
    }

    public void modificarAlumno(Alumno alumno) {
        this.miAlumnoDAO.modificarAlumno(alumno);
    }

    public void borrarAlumno(Alumno alumno) {
        this.miAlumnoDAO.borrarAlumno(alumno);
    }    
}
