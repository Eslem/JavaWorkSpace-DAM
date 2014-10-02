/**
 * 
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import pojo.Persona;


/**
 * Ventana Principal desde donde se escojen las diferentes opciones
 * para el ejemplo del patron Singleton
 * 
 * @author chenao
 * 
 */
public class VentanaPrincipal extends JFrame implements ActionListener {

	private JTextArea areaIntroduccion;
	private JLabel labelTitulo, labelSeleccion;
	private JButton botonAceptar;
	private JComboBox comboItems;
	
	/**
	 * Permite establecer un valor dependiendo de
	 * la opcion seleccionada en el JComboBox comboItems
	 */
	public int seleccionaCombo = 0;
	public String textoIntroduccion = "";
	private String arregloItems[] = new String[4];
	private Persona listaPersonas[];

	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana principal
	 */
	public VentanaPrincipal(Persona[] personas) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/*Obtiene la lista de personas que se enviaran como parametros*/
		listaPersonas=personas;
		
		arregloItems[0] = "Ver Eventos";
		arregloItems[1] = listaPersonas[0].getNombre();
		arregloItems[2] = listaPersonas[1].getNombre();
		arregloItems[3] = listaPersonas[2].getNombre();		

		botonAceptar = new JButton();
		botonAceptar.setBounds(330, 250, 60, 25);
		botonAceptar.setText("OK");

		labelTitulo = new JLabel();
		labelTitulo.setText("PATRON SINGLETON");
		labelTitulo.setBounds(100, 40, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 20));

		labelSeleccion = new JLabel();
		labelSeleccion.setText("Seleccione una Opcion");
		labelSeleccion.setBounds(40, 230, 250, 25);

		comboItems = new JComboBox();
		comboItems.setModel(new javax.swing.DefaultComboBoxModel(
				arregloItems));
		comboItems.setBounds(40, 250, 280, 25);

		textoIntroduccion = "Esta aplicación presenta un ejemplo practico usando el "
				+ "patron singleton, se tiene una clase singleton que permite "
				+ "almacenar un historico de los eventos ejecutados por 3 usuarios "
				+ "cada vez que estos presionan un boton de la ventana de eventos.";

		areaIntroduccion = new JTextArea();
		areaIntroduccion.setBounds(40, 100, 350, 120);
		areaIntroduccion.setEditable(false);
		areaIntroduccion.setFont(new java.awt.Font("Verdana", 0, 14));
		areaIntroduccion.setLineWrap(true);
		areaIntroduccion.setText(textoIntroduccion);
		areaIntroduccion.setWrapStyleWord(true);
		areaIntroduccion.setBorder(javax.swing.BorderFactory.createBevelBorder(
				javax.swing.border.BevelBorder.LOWERED, null, null, null,
				new java.awt.Color(0, 0, 0)));

		botonAceptar.addActionListener(this);
		comboItems.addActionListener(this);

		add(botonAceptar);
		add(labelSeleccion);
		add(labelTitulo);
		add(areaIntroduccion);
		add(comboItems);

		setSize(440, 320);
		setTitle("Patron Singleton");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

	}

	/*Eventos de la interfaz ActionListener*/
	public void actionPerformed(ActionEvent e) {
		/*envia el valor de la lista seleccionada del combo*/
		if (e.getSource() == botonAceptar) {
			muestraVentana(seleccionaCombo);
		}
		/*Establece un valor dependiendo de la opcion de la lista seleccionada*/
		if (e.getSource() == comboItems) {
			if (comboItems.getSelectedIndex() == 0)
				seleccionaCombo = 0;
			if (comboItems.getSelectedIndex() == 1)
				seleccionaCombo = 1;
			if (comboItems.getSelectedIndex() == 2)
				seleccionaCombo = 2;
			if (comboItems.getSelectedIndex() == 3)
				seleccionaCombo = 3;
		}

	}

	private void muestraVentana(int comboSeleccionado) {
		VentanaLog miLog;
		ventanaEventos miVentanaEventos;
		switch (comboSeleccionado) 
		{
		case 0:	miLog=new VentanaLog();
				miLog.setVisible(true);	
				break;
		case 1:	miVentanaEventos=new ventanaEventos(listaPersonas[0]);
				miVentanaEventos.setVisible(true);
				break;
		case 2:	miVentanaEventos=new ventanaEventos(listaPersonas[1]);
				miVentanaEventos.setVisible(true);
				break;
		case 3:	miVentanaEventos=new ventanaEventos(listaPersonas[2]);
				miVentanaEventos.setVisible(true);
				break;
		default:JOptionPane.showMessageDialog(null,"Por definir");
			break;
		}
		
		
	}

}
