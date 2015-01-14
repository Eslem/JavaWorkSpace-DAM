/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.DAO.FileText;

import modelo.DAO.DAOFactory;

/**
 *
 * @author loren
 */
public class FileTextDAOFactory extends DAOFactory {
    
    private AlumnoDAO alumnoDAO = null;

    public synchronized static FileTextDAOFactory getInstance() {
        // si no existe la instancia lo crea caso contrario retorna el que ya existe
        if (instancia == null){
            instancia = new FileTextDAOFactory();
        }
        return (FileTextDAOFactory) instancia;
    } 
    
    public AlumnoDAO getAlumnoDAO() {
        if(alumnoDAO == null){
            alumnoDAO = new AlumnoDAO();        
        }
        return alumnoDAO;
    }    

    // Cuando se cierra la factoria se salvan los datos
    @Override
    public void closeDAOFactory() throws Exception{
        alumnoDAO.close();
    }

    @Override
    public void openDAOFactory() throws Exception{
        try {
            alumnoDAO.open();
        } catch (Exception ex) {
            throw ex;
        }
    }
   
}
