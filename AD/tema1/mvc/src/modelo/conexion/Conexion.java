package modelo.conexion;

import java.sql.*;


/**
 * Clase que permite conectar con la base de datos
 * @author chenao
 *
 */
public class Conexion {

    static Connection con; 
    private static Conexion INSTANCE = null;
 
    // ****************************
    // METODOS PARA LA CONSTRUCCION DE LA CONEXION
    // ****************************
    
    /**
     * Constructor
     */
    private Conexion() throws Exception {
    	crearConexion();
    }
    
    /**
     * Metodo que hace la conexion a la base de dades
     */
    private void crearConexion() throws Exception {
        String host = "localhost";
        String user = "fpmislata";
        String pass = "12345";
        String dtbs = "PersonasDB";

        try { // preparem la connexi√≥
                Class.forName("com.mysql.jdbc.Driver");
                String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?"
                                + "user=" + user + "&password=" + pass;
                con = DriverManager.getConnection(newConnectionURL);
                con.setAutoCommit(true);
        } catch (Exception e) {
                System.out.println("Error en la obertura de la conexión");
                throw e;
        }
    }   
    
    // ***********************
    // METODOS PARA LA CREACION DE LA INSTANCIA Y SU DESTRUCCION
    // ***********************
 
    /**
     * Crea una instancia de la base de datos en caso de no estar creada.
     */
    
    private static void crearInstancia() throws Exception {
        // Si no existe la instancia la inicializa
        if (INSTANCE == null) { 
            INSTANCE = new Conexion();
        }
    }
 
    /**
     * Metodo para retorna una instancia de la conexion. Si no esta creada la crea, y si esta creada la retorna
     * retorna una instancia de la conexion a la base de datos
     */
    private static Conexion getInstancia() throws Exception {
        if (INSTANCE == null) 
          crearInstancia();
        return INSTANCE;
    }
    

    // **********************
    // METODOS PUBLICOS
    // ***********************


    /**
     * metodo para cerrar la conexion de la base de datos
     */
    public static void cerrarConexion() throws Exception {
        try {
            con.close();
            INSTANCE = null;
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexion.");
            throw e;
        }
    }	
    
    /**
     * 
     * Metodo que obtiene la conexion a la base de datos. Si no esta creada la crea
     */
    public static Connection getConexion() throws Exception{
        return getInstancia().con;
    }

}




