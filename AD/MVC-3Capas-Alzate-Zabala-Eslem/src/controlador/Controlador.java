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
import View.OrigenDatosVista;
import java.io.BufferedReader;

/**
 *
 * @author loren
 */
public class Controlador {
    
    // Variables para la vista
    private AlumnoVista miAlumnoVista;
    
    // Variables para el modelo
    private AlumnoBLL miAlumnoBLL;
    private OrigenDatosVista miOrigenDatosVista;
    
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
    
    public void startAplicattion() throws IOException, Exception{
        this.miOrigenDatosVista.showSourceDataView();
    }
    
    public void addAlumno(Alumno alumno){
        this.miAlumnoBLL.addAlumno(alumno);
    }

    public ArrayList listAlumnos() {
        return this.miAlumnoBLL.listAlumnos();
    }

    public Alumno findAlumno(Alumno alumno) {
       alumno = this.miAlumnoBLL.findAlumno(alumno);
       return alumno;
    }

    public void updateAlumno(Alumno alumno) {
       this.miAlumnoBLL.updateAlumno(alumno);
    }

    public void deleteAlumno(Alumno alumno) {
       this.miAlumnoBLL.deleteAlumno(alumno);
    }

    public void close(){
       this.miAlumnoBLL.close();
    }

    // ******************************
    // Metodos de llamada a las vistas
    // ******************************
    
    public void showMenuView(){
        this.miAlumnoVista.showMenuView();
    }
    
    public void showAddAlumnoView() {
       this.miAlumnoVista.showAddAlumnoView();
    }    

    public void showUpdateAlumnoView() {
        this.miAlumnoVista.showUpdateAlumnoView();
    }
    
    public void showDeleteAlumnoView(){
        this.miAlumnoVista.showDeleteAlumnoView();
    }
    
    public void showErrorView(String mensaje) {
        this.miAlumnoVista.showErrorView(mensaje);
    }

    public void showListAlumnoView() {
        this.miAlumnoVista.showListAlumnoView();
    }

    public void setMiOrigenDatosVista(OrigenDatosVista vista2) {
        this.miOrigenDatosVista = vista2;
    }

    public void setTypeSource(int opcion) throws Exception {
        this.miAlumnoBLL.setTypeSource(opcion);
    }

    public void startMainMenu() throws IOException {
        this.miAlumnoVista.startApplication();
    }
    
}
