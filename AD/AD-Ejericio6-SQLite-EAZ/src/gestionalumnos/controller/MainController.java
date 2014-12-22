/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos.controller;

import gestionalumnos.model.dommain.Alumno;
import gestionalumnos.model.bll.AlumnoBLL;
import gestionalumnos.model.dao.AlumnoDAO;
import gestionalumnos.model.dao.AlumnoDAOImplSQLITE;
import gestionalumnos.view.MainView;
import java.util.List;

/**
 *
 * @author eslem
 */
public class MainController {
    MainView mainView;
    AlumnoBLL alumnoBLL;

    public MainController(MainView mainView, AlumnoBLL alumnoBLL) {
        this.mainView = mainView;
        this.alumnoBLL = alumnoBLL;
    }
    
    public void start(){
        AlumnoDAO alumnoDAOalumnoDAO = new AlumnoDAOImplSQLITE();
        alumnoBLL.setAlumnoDAO(alumnoDAOalumnoDAO);
        mainView.start();

    }

    public MainController() {
    }
       

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public AlumnoBLL getAlumnoBLL() {
        return alumnoBLL;
    }

    public void setAlumnoBLL(AlumnoBLL alumnoBLL) {
        this.alumnoBLL = alumnoBLL;
    }
    
    public void showError(String error){
        mainView.showError(error);
    }
    
    public List<Alumno> getAlumnos(){
        return alumnoBLL.getAlumnos();
    }
    
    public void add(Alumno alumno){
        alumnoBLL.add(alumno);
    };
    
    public void delete(int id){
        alumnoBLL.delete(id);
    }
}
