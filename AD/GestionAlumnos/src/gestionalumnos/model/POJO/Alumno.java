/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos.model.POJO;

/**
 *
 * @author eslem
 */
public class Alumno {
    int id;
    String dni;
    int edad;
    String Nombre;
    String Apellidos;

    public Alumno(int id, String dni, int edad, String Nombre, String Apellidos) {
        this.id = id;
        this.dni = dni;
        this.edad = edad;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
    }

    public Alumno() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
       
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }  
    
    
}
