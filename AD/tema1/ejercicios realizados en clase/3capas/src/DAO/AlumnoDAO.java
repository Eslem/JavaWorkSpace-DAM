/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import POJO.Alumno;
import java.util.ArrayList;

/**
 *
 * @author loren
 */
public class AlumnoDAO {
    
    private ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
    public AlumnoDAO(){
        super();
        this.altaAlumno(new Alumno(1,"Loren","Diaz",38));
    }
    
    public boolean altaAlumno(Alumno alumno){
        listaAlumnos.add(alumno);
        return true;
    }
    
    public ArrayList<Alumno> dameListaAlumnos() {
        return listaAlumnos;
    }

    public Alumno buscarAlumno(Alumno alumno) {
        for(int i=0;i<this.listaAlumnos.size();i++){
            if(listaAlumnos.get(i).getId()==alumno.getId()){
                return listaAlumnos.get(i);
            }
        }
        return null;
    }

    public void modificarAlumno(Alumno alumno) {
        int i=0;
        boolean enc=false;
        while((i<this.listaAlumnos.size())&&(enc==false)){
            if(this.listaAlumnos.get(i).getId()==alumno.getId()){
                this.listaAlumnos.set(i, alumno);
                enc=true;
            }else{
                i++;
            }
        }
    }

    public void borrarAlumno(Alumno alumno) {
        int i=0;
        while(i<this.listaAlumnos.size()){
            if(this.listaAlumnos.get(i).getId()==alumno.getId()){
                this.listaAlumnos.remove(i);
            }else{
                i++;
            }
        }        
    }
}
