/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerobj;

import java.io.Serializable;

/**
 *
 * @author eslem
 */
public class Persona implements Serializable {

    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        super();
        this.nombre = nombre;
        this.edad = edad;
    }

    public Persona() {
        super();
    }

    public void setNombre(String nom) {
        this.nombre = nom;
    }

    public void setEdad(int ed) {
        this.edad = ed;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }
}