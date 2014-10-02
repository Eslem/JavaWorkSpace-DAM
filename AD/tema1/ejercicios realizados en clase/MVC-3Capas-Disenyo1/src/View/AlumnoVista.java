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
    
    // Variable que almacen el controlador que enlaza con la vista
    private Controlador miControlador;
    
    // Metodo para asignar el controlador a la vista
    public void setControlador(Controlador miControlador){
        this.miControlador = miControlador;
    }
    
    // 
    public void arrancaAplicacion() throws IOException{
    // TODO Auto-generated method stub
        int opcion = -1;

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        while (opcion != 5){
            mostrarMenu();
            opcion = Integer.parseInt(stdin.readLine());

            // Carga de datos
            if (opcion == 1){
                System.out.println("**********************************");

                int idAux = pedirId(stdin);
                String nombreAux = pedirNombre(stdin);
                String apellidosAux = pedirApellidos(stdin);
                int edadAux = pedirEdad(stdin);

                Alumno alumno = new Alumno(idAux, nombreAux, apellidosAux, edadAux);

                miControlador.altaAlumno(alumno);

            // Modificacion de usuario
            }else if(opcion==2){
                System.out.println("\n**********************************");
                System.out.println("Indique el id del alumno a modificar:");
                int idAux = Integer.parseInt(stdin.readLine());
                Alumno alumno = new Alumno();
                alumno.setId(idAux);

                alumno = this.miControlador.buscarAlumno(alumno);
                if(alumno!=null){
                    String nombreAux = pedirNombre(stdin);
                    String apellidosAux = pedirApellidos(stdin);
                    int edadAux = pedirEdad(stdin);  

                    alumno.setNombre(nombreAux);
                    alumno.setApellidos(apellidosAux);
                    alumno.setEdad(edadAux);
                    this.miControlador.modificarAlumno(alumno);
                    System.out.println("Alumno modificado correctamente.\n");
                }

            // Borrado de usuario
            }else if(opcion==3){
                System.out.println("\n**********************************");
                System.out.println("Indique el id del alumno a borrar:");
                int idAux = Integer.parseInt(stdin.readLine());
                Alumno alumno = new Alumno();
                alumno.setId(idAux);                        

                alumno = this.miControlador.buscarAlumno(alumno);
                if(alumno!=null){
                    this.miControlador.borrarAlumno(alumno);
                }

            // Listar alumnos	
            }else if(opcion==4){
                ArrayList<Alumno> listaAlumnos = miControlador.dameListaAlumnos();
                for (int i=0;i<listaAlumnos.size();i++){
                    System.out.println( " Id: " + listaAlumnos.get(i).getId() + 
                                        " - Nombre y Apellidos: " + listaAlumnos.get(i).getNombre() + ' ' +  listaAlumnos.get(i).getApellidos() +
                                        " - Edad: " + listaAlumnos.get(i).getEdad());
                }

            // Salir
            }else if(opcion==5){
                // No hace nada
            }
        }
    }
	
    private static void mostrarMenu(){
        System.out.println("1.- Alta de usuario en el sistema.");
        System.out.println("2.- Modificación usuario sistema.");
        System.out.println("3.- Borrar usuario sistema");
        System.out.println("4.- Listar usuarios.");
        System.out.println("5.- Salir.");
    }

    public void mostrarError(String mensaje) {
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
    
}
