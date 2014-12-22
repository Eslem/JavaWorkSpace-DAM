/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.DAO.MySQL;

import java.util.ArrayList;
import modelo.DAO.POJODAO;

/**
 *
 * @author loren
 */
public class AlumnoDAO implements POJODAO{
    
    // *****************
    // Implementacion de las operacions propias del OperationsDAO
    // *****************
    
    @Override
    public void add(Object object) {
    }

    @Override
    public Object find(Object object) {
        return null;
    }

    @Override
    public void update(Object object) {
    }

    @Override
    public void delete(Object object) {
    }

    @Override
    public ArrayList list() {
        return null;
    }

    @Override
    public void open() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
