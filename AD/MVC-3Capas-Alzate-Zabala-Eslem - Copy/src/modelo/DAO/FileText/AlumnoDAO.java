/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.DAO.FileText;

import POJO.Alumno;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import modelo.DAO.POJODAO;

/**
 *
 * @author loren
 */
public class AlumnoDAO implements POJODAO{
    
    private ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();

    public void open() throws Exception {
        File f = new File("./datos/alumnos.txt");
        FileReader fr = null;
        try{
            fr = new FileReader(f);
            int c = fr.read();
            String valor="";
            int pos=0;
            Alumno a = null;
            while(c!=-1){
                char ca = (char) c;
                if (ca==';'){
                    if(pos==0){
                        a= new Alumno();
                        a.setId(Integer.parseInt(valor));
                    }else if(pos==1){
                        a.setNombre(valor);
                    }else if(pos==2){
                        a.setApellidos(valor);
                    }
                    valor="";
                    pos++;
                }else if (ca=='\n'){
                   a.setEdad(Integer.parseInt(valor));                   
                   listaAlumnos.add(a);
                   pos=0;
                   valor="";
                }else {
                   valor = valor.concat(String.valueOf(ca));
                }
                c = fr.read();
            }
        }catch(Exception e){
            throw e;
        }finally{
            if (fr!=null)
                fr.close();
        }
    }
    
    public void close() throws Exception{
        File f = new File("./datos/alumnos.txt");
        // Crearemos el fichero cada vez
        FileWriter fw = null;
        try{
            fw = new FileWriter(f);
     
            for (int i=0;i<listaAlumnos.size();i++){
                // Escribimos el id
                char[] cad = String.valueOf(listaAlumnos.get(i).getId()).toCharArray();

                for(int j=0; j<cad.length; j++)
                    fw.write(cad[j]);             
                fw.append(';');

                // Escribimos el nombre
                cad = String.valueOf(listaAlumnos.get(i).getNombre()).toCharArray();

                for(int j=0; j<cad.length; j++)
                    fw.write(cad[j]);             
                fw.append(';');            

                // Escribimos los apellidos
                cad = String.valueOf(listaAlumnos.get(i).getApellidos()).toCharArray();

                for(int j=0; j<cad.length; j++)
                    fw.write(cad[j]);             
                fw.append(';');            

                // Escribimos la edad
                cad = String.valueOf(listaAlumnos.get(i).getEdad()).toCharArray();

                for(int j=0; j<cad.length; j++)
                    fw.write(cad[j]);             
                fw.append('\n');             
            }
        }catch(Exception e){
            throw e;
        }finally{
            if (fw!=null)
                fw.close();    
        } 
    }
    
    

    // *****************
    // Implementacion de las operacions propias del OperationsDAO
    // *****************
    
    @Override
    public void add(Object object) {
        listaAlumnos.add((Alumno) object);
    }

    @Override
    public Object find(Object object) {
        Alumno a = (Alumno) object;
        for(int i=0;i<this.listaAlumnos.size();i++){
            if(listaAlumnos.get(i).getId()==a.getId()){
                return listaAlumnos.get(i);
            }
        }
        return null;
    }

    @Override
    public void update(Object object) {
        int i=0;
        Alumno a = (Alumno) object;        
        boolean enc=false;
        while((i<this.listaAlumnos.size())&&(enc==false)){
            if(this.listaAlumnos.get(i).getId()==a.getId()){
                this.listaAlumnos.set(i, a);
                enc=true;
            }else{
                i++;
            }
        }
    }

    @Override
    public void delete(Object object) {
        Alumno a = (Alumno) object;           
        int i=0;
        while(i<this.listaAlumnos.size()){
            if(this.listaAlumnos.get(i).getId()==a.getId()){
                this.listaAlumnos.remove(i);
            }else{
                i++;
            }
        } 
    }

    @Override
    public ArrayList list() {
        return listaAlumnos;
    }
}
