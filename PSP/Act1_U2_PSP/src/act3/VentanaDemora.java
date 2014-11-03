/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act3;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author eslem
 */
public class VentanaDemora extends JFrame {

    private Button boton;
    private Choice combo;

    public VentanaDemora() {

        setLayout(new FlowLayout());

        boton = new Button("Esto se va a demorar");
        add(boton);

        combo = new Choice();
        add(combo);
        boton.addActionListener(new EscuchaBoton());

        combo.addItem("opción 1");
        combo.addItem("opción 2");
        combo.addItem("opción 3");
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class EscuchaBoton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TareaBoton tarea = new TareaBoton();
            tarea.start();
        }
    }

    public static void main(String[] args) {
        new VentanaDemora();
    }

    class TareaBoton extends Thread {

        @Override
        public void run() {
            try {
                boton.setEnabled(false);
                Thread.sleep(5000);
                boton.setEnabled(true);
                System.out.println("Terminó la espera");
            } catch (InterruptedException v) {
                System.out.println(v);
            }
        }
    }

}
