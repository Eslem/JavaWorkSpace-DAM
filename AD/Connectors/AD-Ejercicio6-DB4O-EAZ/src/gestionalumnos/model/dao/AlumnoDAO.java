/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos.model.dao;

import gestionalumnos.model.dommain.Alumno;

/**
 *
 * @author eslem
 */
public interface AlumnoDAO extends GenericDAO<Alumno>{
    
    public void delete(String id);
}
