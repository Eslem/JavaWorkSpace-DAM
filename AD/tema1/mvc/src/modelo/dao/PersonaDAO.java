package modelo.dao;

import controlador.Controlador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.conexion.Conexion;
import modelo.POJO.Persona;


/**
 * Clase que permite el acceso a la base de datos
 * @author chenao
 *
 */
public class PersonaDAO{
    
    private Controlador miControlador;

    public PersonaDAO(Controlador miControlador) {
        super();
        this.miControlador = miControlador;
    }

    public void registrarPersona(Persona miPersona) throws Exception{
        try {
            Statement statement = Conexion.getConexion().createStatement();
            statement.executeUpdate("INSERT INTO persona VALUES ('"+miPersona.getId()+"', '"
                            +miPersona.getNombre()+"', '"+miPersona.getEdad()+"', '"
                            +miPersona.getProfesion()+"', '"+miPersona.getTelefono()+"')");
            miControlador.mostrarMensaje("Se ha registrado Exitosamente");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }finally{
            Conexion.cerrarConexion();
        }

    }

    public Persona buscarPersona(Persona miPersona) throws Exception {
        Persona persona= new Persona();
        boolean existe=false;
        try {
            //Statement estatuto = conex.getConnection().createStatement();
            String consulta2 = "SELECT * FROM persona where id = " + miPersona.getId(); 
            PreparedStatement consulta = Conexion.getConexion().prepareStatement("SELECT * FROM persona where id = ? ");
            consulta.setInt(1, miPersona.getId());
            ResultSet res = consulta.executeQuery();
            while(res.next()){
                existe=true;
                persona.setId(Integer.parseInt(res.getString("id")));
                persona.setNombre(res.getString("nombre"));
                persona.setEdad(Integer.parseInt(res.getString("edad")));
                persona.setProfesion(res.getString("profesion"));
                persona.setTelefono(Integer.parseInt(res.getString("telefono")));
             }
            res.close();
        } catch (Exception e) {					
            System.out.println(e);
            throw e;
        }finally{
            Conexion.cerrarConexion();
        }
        if (existe) {
            return persona;
        }else { 
            return null;
        }
    }

    public int modificarPersona(Persona miPersona) throws Exception {
        int retorno = 0;
        try{
            String consulta="UPDATE persona SET id= ? ,nombre = ? , edad=? , profesion=? , telefono= ? WHERE id= ? ";
            PreparedStatement estatuto = Conexion.getConexion().prepareStatement(consulta);

            estatuto.setInt(1, miPersona.getId());
            estatuto.setString(2, miPersona.getNombre());
            estatuto.setInt(3, miPersona.getEdad());
            estatuto.setString(4, miPersona.getProfesion());
            estatuto.setInt(5,miPersona.getTelefono());
            estatuto.setInt(6, miPersona.getId());
            estatuto.executeUpdate();
            
            retorno = 1;

        }catch(Exception e){                     
            System.out.println(e);
            throw e;                   
        }finally{
            Conexion.cerrarConexion();
            return retorno;
        }
    }

    public void eliminarPersona(Persona miPersona) throws Exception {
        try {
            Statement estatuto = Conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM persona WHERE id="+miPersona.getId());
            miControlador.mostrarMensaje("Se ha eliminado correctamente");
            estatuto.close();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }finally{
            Conexion.cerrarConexion();
        }
    }

}
