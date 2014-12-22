/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos.model.dao;

import java.util.List;

/**
 *
 * @author eslem
 */
public interface GenericDAO<T> {

    public T get(int id);

    public T insert(T object);

    public T update(T object);

    public void delete(int id);

    public List<T> findAll();

}
