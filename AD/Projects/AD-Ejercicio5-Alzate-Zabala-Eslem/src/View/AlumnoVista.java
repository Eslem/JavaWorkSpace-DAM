/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import controlador.Controlador;
import modelo.BLL.AlumnoBLL;
import POJO.Alumno;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author loren
 */
public class AlumnoVista {
    
    private BufferedReader stdin = null;
    
    public AlumnoVista(){
        super();
        stdin = new BufferedReader(new InputStreamReader(System.in));        
    }
    
    // Variable que almacen el controlador que enlaza con la vista
    private Controlador miControlador;
    
    // Metodo para asignar el controlador a la vista
    public void setControlador(Controlador miControlador){
        this.miControlador = miControlador;
    }
    
    // 
    public void startApplication() throws IOException{
    // TODO Auto-generated method stub
        int opcion = -1;
        
        while (opcion != 5){
            this.miControlador.showMenuView();
            opcion = Integer.parseInt(stdin.readLine());

            // Dar de alta alumno
            if (opcion == 1){
                // Solicitamos al controlador que nos muestre la vista de alta de alumno
                this.miControlador.showAddAlumnoView();
            // Modificacion de usuario
            }else if(opcion==2){
                // Solicitamos al controlador que nos muestre la vista de modificacion de alumno
                this.miControlador.showUpdateAlumnoView();
            // Borrado de usuario
            }else if(opcion==3){
                // Solicitamos al controlador que nos muestre la vista de borrado de alumno
                this.miControlador.showDeleteAlumnoView();    
            // Listar alumnos	
            }else if(opcion==4){
                // Solicitamos al controlador que nos muestre la vista con el listado de alumnos
                this.miControlador.showListAlumnoView();
            // Salir
            }else if(opcion==5){
                // Solicitamos al controlador que se cierre la aplicacion
                this.miControlador.close();
            }
        }
    }

    public void showErrorView(String mensaje) {
        System.out.println(mensaje);
    }
    
    private int pedirId(BufferedReader stdin) throws IOException{
        System.out.println("Indique el id del alumno:");
        int idAux = Integer.parseInt(stdin.readLine());
        return idAux;
    }
    
    private String pedirNombre(BufferedReader stdin) throws IOException{
        System.out.println("Indique el nombre del alumno:");
        String nombreAux = stdin.readLine();     
        return nombreAux;
    }

    private String pedirApellidos(BufferedReader stdin) throws IOException{ 
        System.out.println("Indique los apellidos del alumno:");
        String apellidosAux = stdin.readLine();   
        return apellidosAux;
    }
    
    private int pedirEdad(BufferedReader stdin) throws IOException{ 
        System.out.println("Indique la edad del alumno:");
        int edadAux = Integer.parseInt(stdin.readLine());  
        return edadAux;
    }

    // Vista que muestra el menú en la pantalla principal
    public void showMenuView(){
        System.out.println();
        System.out.println("1.- Alta de usuario en el sistema.");
        System.out.println("2.- Modificación usuario sistema.");
        System.out.println("3.- Borrar usuario sistema");
        System.out.println("4.- Listar usuarios.");
        System.out.println("5.- Salir.");
    }
    
    // Vista que permite mostrar el alta del alumno
    public void showAddAlumnoView(){
        try{
            System.out.println("**********************************");

            int idAux = pedirId(stdin);
            String nombreAux = pedirNombre(stdin);
            String apellidosAux = pedirApellidos(stdin);
            int edadAux = pedirEdad(stdin);
            
            Alumno alumno = new Alumno(idAux, nombreAux, apellidosAux, edadAux);

            this.miControlador.addAlumno(alumno);
        }catch(Exception e){
            this.miControlador.showErrorView(e.getMessage());
        }
    }    
    
    // Vista que muestra el menú para la modificación de alumnos
    public void showUpdateAlumnoView(){  
        try{
            System.out.println("\n**********************************");
            System.out.println("Indique el id del alumno a modificar:");
            int idAux = Integer.parseInt(stdin.readLine());
            Alumno alumno = new Alumno();
            alumno.setId(idAux);

            alumno = this.miControlador.findAlumno(alumno);
            if(alumno!=null){
                String nombreAux = pedirNombre(stdin);
                String apellidosAux = pedirApellidos(stdin);
                int edadAux = pedirEdad(stdin);  

                alumno.setNombre(nombreAux);
                alumno.setApellidos(apellidosAux);
                alumno.setEdad(edadAux);
                this.miControlador.updateAlumno(alumno);
                System.out.println("Alumno modificado correctamente.\n");
            }   
        }catch(Exception e){
            this.miControlador.showErrorView(e.getMessage());
        }
    }
    
    // Vista de borrado de alumno
    public void showDeleteAlumnoView(){
        try{
            System.out.println("\n**********************************");
            System.out.println("Indique el id del alumno a borrar:");
            int idAux = Integer.parseInt(stdin.readLine());
            Alumno alumno = new Alumno();
            alumno.setId(idAux);                        

            alumno = this.miControlador.findAlumno(alumno);
            if(alumno!=null){
                this.miControlador.deleteAlumno(alumno);
            }
        }catch(Exception e){
            this.miControlador.showErrorView(e.getMessage());
        }

    }

    public void showListAlumnoView() {
        try{
            ArrayList<Alumno> listaAlumnos = miControlador.listAlumnos();
            for (int i=0;i<listaAlumnos.size();i++){
                System.out.println( " Id: " + listaAlumnos.get(i).getId() + 
                                    " - Nombre y Apellidos: " + listaAlumnos.get(i).getNombre() + ' ' +  listaAlumnos.get(i).getApellidos() +
                                    " - Edad: " + listaAlumnos.get(i).getEdad());
            }        
        }catch(Exception e){
            this.miControlador.showErrorView(e.getMessage());
        }
    }
    
    
}
