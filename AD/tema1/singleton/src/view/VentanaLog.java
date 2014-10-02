package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Singleton.ClaseLogSingleton;


/**
 * interfaz de usuario que permite mostrar el historico de eventos
 * @author chenao
 *
 */
public class VentanaLog extends JFrame {
	
	JTextArea areaTexto;
	JLabel labelTitulo;
	private JScrollPane scroll;
	/**
	 * metodo constructor que permite inicializar los componentes de la ventana 
	 */
	public VentanaLog()
	{
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		scroll = new JScrollPane();
		
		labelTitulo=new JLabel();
		labelTitulo.setText("LOG EVENTOS");
		labelTitulo.setBounds(90, 15, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 25));
		
		areaTexto= new JTextArea();
		areaTexto.setText(ClaseLogSingleton.getMiLogSingleton().getContenido());
		areaTexto.setFont(new java.awt.Font("Verdana", 0, 12));
		areaTexto.setLineWrap(true);
		areaTexto.setWrapStyleWord(true);
		areaTexto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, null, null, new java.awt.Color(0, 0, 0)));
		scroll.setViewportView(areaTexto);
		scroll.setBounds(15,55,340,280);
		 
		add(scroll);
		add(labelTitulo);
		//add(areaTexto);
	
		setSize(380,380);
		setTitle("LOG EVENTOS");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
	}
}
