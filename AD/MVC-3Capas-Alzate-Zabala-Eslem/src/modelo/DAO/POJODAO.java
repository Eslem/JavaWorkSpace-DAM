/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.DAO;

import java.util.ArrayList;

/**
 *
 * @author loren
 */
public interface POJODAO {
    /** Creates a object. */
    public void add(Object object);
 
    /** Receives a object by given object. */
    public Object find(Object object);
 
    /** Updates an existing object. */
    public void update(Object object);
 
    /** Deletes a object by object. */
    public void delete(Object object);  
    
    /** Return a list of objects. */
    public ArrayList list();
    
    public void open() throws Exception;
    public void close() throws Exception;
}
