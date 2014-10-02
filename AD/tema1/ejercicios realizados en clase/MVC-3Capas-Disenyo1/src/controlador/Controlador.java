/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import View.AlumnoVista;
import java.io.IOException;
import java.util.ArrayList;
import modelo.BLL.AlumnoBLL;
import POJO.Alumno;

/**
 *
 * @author loren
 */
public class Controlador {
    
    // Variables para la vista
    private AlumnoVista miAlumnoVista;
    
    // Variables para el modelo
    private AlumnoBLL miAlumnoBLL;
    
    public Controlador(){
        super();
    }

    /**
     * @return the miAlumnoVista
     */
    public AlumnoVista getMiAlumnoVista() {
        return miAlumnoVista;
    }

    /**
     * @param miAlumnoVista the miAlumnoVista to set
     */
    public void setMiAlumnoVista(AlumnoVista miAlumnoVista) {
        this.miAlumnoVista = miAlumnoVista;
    }

    /**
     * @return the miAlumnoBLL
     */
    public AlumnoBLL getMiAlumnoBLL() {
        return miAlumnoBLL;
    }

    /**
     * @param miAlumnoBLL the miAlumnoBLL to set
     */
    public void setMiAlumnoBLL(AlumnoBLL miAlumnoBLL) {
        this.miAlumnoBLL = miAlumnoBLL;
    }
    
    public void arrancarAplicacion() throws IOException{
        this.miAlumnoVista.arrancaAplicacion();
    }
    
    public void altaAlumno(Alumno alumno){
        this.miAlumnoBLL.altaAlumno(alumno);
    }

    public ArrayList<Alumno> dameListaAlumnos() {
        return this.miAlumnoBLL.dameListaAlumnos();
    }

    public void mostrarError(String mensaje) {
        this.miAlumnoVista.mostrarError(mensaje);
    }

    public Alumno buscarAlumno(Alumno alumno) {
       alumno = this.miAlumnoBLL.buscarAlumno(alumno);
       return alumno;
    }

    public void modificarAlumno(Alumno alumno) {
       this.miAlumnoBLL.modificarAlumno(alumno);
    }

    public void borrarAlumno(Alumno alumno) {
       this.miAlumnoBLL.borrarAlumno(alumno);
    }
    
}
