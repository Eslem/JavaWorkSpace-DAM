package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import Singleton.ClaseLogSingleton;
import pojo.Persona;


/**
 * interfaz de usuario que presenta varios botones para
 * que el usuario que la ejecuta presione y asi genere los
 * eventos que se registraran en el objeto singleton
 * @author chenao
 *
 */
public class ventanaEventos extends JFrame implements ActionListener{
	
	JLabel labelTitulo,labelUsuario, evento;
	JButton boton1,boton2, boton3, boton4,boton5,boton6, boton7;
	Persona miPersonaVo;
	/**
	 * metodo constructor que permite inicializar los componentes de la ventana 
	 * @param listaPersonas 
	 */
	public ventanaEventos(Persona persona)
	{
		miPersonaVo=persona;
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		boton1=new JButton();
		boton1.setBounds(40, 80, 100, 25);
		boton1.setText("boton1");
		
		boton2=new JButton();
		boton2.setBounds(160, 80, 100, 25);
		boton2.setText("boton2");
		
		boton3=new JButton();
		boton3.setBounds(280, 80, 100, 25);
		boton3.setText("boton3");
		
		boton4=new JButton();
		boton4.setBounds(40, 120, 100, 25);
		boton4.setText("boton4");
		
		boton5=new JButton();
		boton5.setBounds(160, 120, 100, 25);
		boton5.setText("boton5");
		
		boton6=new JButton();
		boton6.setBounds(280, 120, 100, 25);
		boton6.setText("boton6");
		
		labelTitulo=new JLabel();
		labelTitulo.setText("EVENTOS");
		labelTitulo.setBounds(160, 15, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1,18));
		
		labelUsuario=new JLabel();
		labelUsuario.setText("Usuario: "+miPersonaVo.getNombre());
		labelUsuario.setBounds(40, 40, 380, 30);
		labelUsuario.setFont(new java.awt.Font("Verdana", 1,15));
		
		evento=new JLabel();
		evento.setBounds(40, 160, 380, 30);
		evento.setFont(new java.awt.Font("Verdana", 1,15));
		
		boton1.addActionListener(this);
		boton2.addActionListener(this);
		boton3.addActionListener(this);
		boton4.addActionListener(this);
		boton5.addActionListener(this);
		boton6.addActionListener(this);
	
       	add(boton1);
		add(boton2);
		add(boton3);
		add(boton4);
		add(boton5);
		add(boton6);
		add(labelTitulo);
		add(labelUsuario);
		add(evento);
		//add(areaTexto);
	
		setSize(420,220);
		setTitle("EVENTOS");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String fechaRegistro=obtenerFechaHora();
		String contenido=ClaseLogSingleton.getMiLogSingleton().getContenido();;
		if (e.getSource()==boton1) {
			contenido+=fechaRegistro+": "+miPersonaVo.getNombre()+" Presiona el botón 1\n";
			ClaseLogSingleton.getMiLogSingleton().setContenido(contenido);
			evento.setText("Ejecuta boton 1");
		}
		if (e.getSource()==boton2) {
			contenido+=fechaRegistro+": "+miPersonaVo.getNombre()+" Presiona el botón 2\n";
			ClaseLogSingleton.getMiLogSingleton().setContenido(contenido);
			evento.setText("Ejecuta boton 2");
		}
		if (e.getSource()==boton3) {
			contenido+=fechaRegistro+": "+miPersonaVo.getNombre()+" Presiona el botón 3\n";
			ClaseLogSingleton.getMiLogSingleton().setContenido(contenido);
			evento.setText("Ejecuta boton 3");
		}
		if (e.getSource()==boton4) {
			contenido+=fechaRegistro+": "+miPersonaVo.getNombre()+" Presiona el botón 4\n";
			ClaseLogSingleton.getMiLogSingleton().setContenido(contenido);
			evento.setText("Ejecuta boton 4");
		}
		if (e.getSource()==boton5) {
			contenido+=fechaRegistro+": "+miPersonaVo.getNombre()+" Presiona el botón 5\n";
			ClaseLogSingleton.getMiLogSingleton().setContenido(contenido);
			evento.setText("Ejecuta boton 5");
		}
		if (e.getSource()==boton6) {
			contenido+=fechaRegistro+": "+miPersonaVo.getNombre()+" Presiona el botón 6\n";
			ClaseLogSingleton.getMiLogSingleton().setContenido(contenido);
			evento.setText("Ejecuta boton 6");
		}
	}

	/**
	 * Permite retornar la fecha y hora de ejecuci�n del evento
	 * @return
	 */
	private String obtenerFechaHora() {
		 Calendar cal1 = Calendar.getInstance();
		  String hora=(""+cal1.get(Calendar.DATE)+"/"+(cal1.get(Calendar.MONTH)+1)
		    +"/"+cal1.get(Calendar.YEAR)+" "+cal1.get(Calendar.HOUR)
		    +":"+cal1.get(Calendar.MINUTE)+":"+cal1.get(Calendar.SECOND));
		return hora;
	}
}
