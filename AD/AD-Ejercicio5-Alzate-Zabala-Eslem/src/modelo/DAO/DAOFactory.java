/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.DAO;

import modelo.DAO.FileBuffered.FileBufferedDAOFactory;
import modelo.DAO.FileText.FileTextDAOFactory;
import modelo.DAO.MySQL.AlumnoDAO;
import modelo.DAO.MySQL.MySQLDAOFactory;
import modelo.DAO.XML.XMLDAOFactory;

/**
 *
 * @author loren
 */
public abstract class DAOFactory {
 
    /** Variables que contienen todas las clases */
    protected static DAOFactory instancia = null; 
    
    /** Static member for file text. */
    public static final int FILETEXT = 0; 
     /** Static member for MYSQL. */
    public static final int MySQL = 1;
    public static final int FILEBUFFERED = 2;
    public static final int XML = 3;
    
    /** Abstract method for the FileTextFactoryDAO.
     * @return  */
    public abstract void openDAOFactory() throws Exception;
    public abstract void closeDAOFactory() throws Exception;
    public abstract POJODAO getAlumnoDAO();

    
    
    public static DAOFactory getDAOFactory(int database) {
        switch (database) {
        case FILETEXT:
            return FileTextDAOFactory.getInstance();
        case MySQL:
            return MySQLDAOFactory.getInstance();  
        case FILEBUFFERED:
            return FileBufferedDAOFactory.getInstance();      
        case XML:
            return XMLDAOFactory.getInstance();
        default:
            return null;
        }
    }
}
