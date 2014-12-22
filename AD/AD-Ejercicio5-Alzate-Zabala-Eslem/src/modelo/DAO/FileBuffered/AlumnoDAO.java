/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO.FileBuffered;

import POJO.Alumno;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import modelo.DAO.POJODAO;

/**
 *
 * @author loren
 */
public class AlumnoDAO implements POJODAO {

    private ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();

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
        for (int i = 0; i < this.listaAlumnos.size(); i++) {
            if (listaAlumnos.get(i).getId() == a.getId()) {
                return listaAlumnos.get(i);
            }
        }
        return null;
    }

    @Override
    public void update(Object object) {
        int i = 0;
        Alumno a = (Alumno) object;
        boolean enc = false;
        while ((i < this.listaAlumnos.size()) && (enc == false)) {
            if (this.listaAlumnos.get(i).getId() == a.getId()) {
                this.listaAlumnos.set(i, a);
                enc = true;
            } else {
                i++;
            }
        }
    }

    @Override
    public void delete(Object object) {
        Alumno a = (Alumno) object;
        int i = 0;
        while (i < this.listaAlumnos.size()) {
            if (this.listaAlumnos.get(i).getId() == a.getId()) {
                this.listaAlumnos.remove(i);
            } else {
                i++;
            }
        }
    }

    @Override
    public ArrayList list() {
        return listaAlumnos;
    }

    @Override
    public void open() throws Exception {
        try {
            FileReader fr = new FileReader("./datos/alumnos.txt");

            BufferedReader br = new BufferedReader(fr);

            String linea;
            StringTokenizer st = null;

            while ((linea = br.readLine()) != null) {
                st = new StringTokenizer(linea, ";");
                Alumno a = new Alumno(Integer.valueOf(st.nextElement().toString()), String.valueOf(st.nextElement().toString()),
                        String.valueOf(st.nextElement().toString()), Integer.valueOf(st.nextElement().toString()));
                listaAlumnos.add(a);
            }
            br.close();
        } catch (FileNotFoundException fn) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException io) {
            System.out.println("Error de E/S ");
        }
    }

    @Override
    public void close() throws Exception {
        try {
            FileWriter fw = new FileWriter("./datos/alumnos.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int x = 0; x < listaAlumnos.size(); x++) {
                bw.write(String.valueOf(listaAlumnos.get(x).getId()));
                bw.write(";");
                bw.write(listaAlumnos.get(x).getNombre());
                bw.write(";");
                bw.write(listaAlumnos.get(x).getApellidos());
                bw.write(";");
                bw.write(String.valueOf(listaAlumnos.get(x).getEdad())+"\n");
            }
            bw.close();
        } catch (FileNotFoundException fn) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException io) {
            System.out.println("Error de E/S ");
        }
    }

}
