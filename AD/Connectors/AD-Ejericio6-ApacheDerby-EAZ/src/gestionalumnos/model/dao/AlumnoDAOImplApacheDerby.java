/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos.model.dao;

import gestionalumnos.model.dommain.Alumno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eslem
 */
public class AlumnoDAOImplApacheDerby implements AlumnoDAO {

    public AlumnoDAOImplApacheDerby() {
    }
       

    private Connection connection = null;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        if (connection == null) {
            //Establecemos	la	conexion	con	la	BD
            connection = DriverManager.getConnection("jdbc:derby:./data/gestionalumnos");
        }
        return connection;
    }

    @Override
    public Alumno get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Alumno insert(Alumno object) {
        try {
            String sql = "INSERT INTO alumno(dni,nombre,apellidos,edad)	VALUES(?,?,?,?)";
            System.out.println(sql);
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            sentencia.setString(1, object.getDni());
            sentencia.setString(2, object.getNombre());
            sentencia.setString(3, object.getApellidos());
            sentencia.setInt(4, object.getEdad());
            int filas = sentencia.executeUpdate();
            return object;
        } catch (SQLException | ClassNotFoundException ex) {
             throw new RuntimeException(ex);
        }
    }

    @Override
    public Alumno update(Alumno object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM alumno WHERE id =?";
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            sentencia.setInt(1, id);
            
            int resultado = sentencia.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Alumno> findAll() {
        List<Alumno> alumnos = new ArrayList<>();
        try {

            String sql = "SELECT	*	FROM	alumno";
            Statement sentencia = getConnection().createStatement();

            ResultSet resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                Alumno alumno = new Alumno();
                alumno.setDni(resultado.getString("dni"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setApellidos(resultado.getString("apellidos"));
                alumno.setId(resultado.getInt("id"));
                alumno.setEdad(resultado.getInt("edad"));
                alumnos.add(alumno);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return alumnos;
    }

}
