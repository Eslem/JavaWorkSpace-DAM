/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos.model.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import gestionalumnos.model.dommain.Alumno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eslem
 */
public class AlumnoDAOImpDB4O implements AlumnoDAO {

    final static String DBName = "DBAlumnos.yap";
    
    private int lastID=0;

    public AlumnoDAOImpDB4O() {
    }

    private ObjectContainer oContainer = null;

    public ObjectContainer getContainer() throws SQLException, ClassNotFoundException {
        if (oContainer == null) {
            oContainer = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBName);
        }
        return oContainer;
    }

    @Override
    public Alumno get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Alumno insert(Alumno object) {
        try {
            getContainer().store(object);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return object;
    }

    @Override
    public Alumno update(Alumno object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String dni) {
        try {
            Alumno alumno = new Alumno(dni,  0, null, null);
            ObjectSet<Alumno> result = getContainer().queryByExample(alumno);

            if (result.size() != 0) {
                while (result.hasNext()) {
                    getContainer().delete(result.next());
                }
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Alumno> findAll() {
        List<Alumno> alumnos = new ArrayList<>();
        try {
            Alumno alumno = new Alumno(null, 0, null, null);
            ObjectSet<Alumno> result = getContainer().queryByExample(alumno);

            if (result.size() != 0) {
                while (result.hasNext()) {
                    alumnos.add(result.next());
                }
            }

            return alumnos;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
