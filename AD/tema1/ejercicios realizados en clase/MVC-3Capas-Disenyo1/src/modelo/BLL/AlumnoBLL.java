/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.BLL;

import modelo.DAO.AlumnoDAO;
import POJO.Alumno;
import View.AlumnoVista;
import controlador.Controlador;
import java.util.ArrayList;

/**
 *
 * @author loren
 */
public class AlumnoBLL {
    
    private AlumnoDAO miAlumnoDAO;
    private Controlador miControlador;
    
    public AlumnoBLL(){
        super();
        miAlumnoDAO = new AlumnoDAO();
    }
    
    public void setControlador(Controlador miControlador){
        this.miControlador = miControlador;
    }
    
    public void altaAlumno(Alumno alumno){
        if(alumno.getEdad()<18){
            miControlador.mostrarError("La edad del alumno no es correcta");
        }else{
            miAlumnoDAO.altaAlumno(alumno);
        }
    }

    public ArrayList<Alumno> dameListaAlumnos() {
        return miAlumnoDAO.dameListaAlumnos();
    }

    public Alumno buscarAlumno(Alumno alumno) {
        alumno = this.miAlumnoDAO.buscarAlumno(alumno);
        if (alumno==null){
            this.miControlador.mostrarError("No existe el alumno");
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
