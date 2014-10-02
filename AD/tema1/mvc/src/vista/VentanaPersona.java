package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.POJO.Persona;

import controlador.Controlador;


public class VentanaPersona extends JFrame implements ActionListener{

    private Controlador miControlador; //objeto miControlador que permite la relacion entre esta clase y la clase Controlador
    private JLabel labelTitulo;
    private JTextField textCod,textNombre,textEdad,textTelefono,textProfesion;
    private JLabel cod,nombre,edad,telefono,profesion;
    private JButton botonGuardar,botonCancelar;

    /**
     * constructor de la clase donde se inicializan todos los componentes
     * de la ventana de registro
     */
    public VentanaPersona() {

        botonGuardar = new JButton();
        botonGuardar.setBounds(110, 220, 120, 25);
        botonGuardar.setText("Registrar");

        botonCancelar = new JButton();
        botonCancelar.setBounds(250, 220, 120, 25);
        botonCancelar.setText("Cancelar");

        labelTitulo = new JLabel();
        labelTitulo.setText("REGISTRO DE PERSONAS");
        labelTitulo.setBounds(120, 20, 380, 30);
        labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

        cod=new JLabel();
        cod.setText("Codigo");
        cod.setBounds(20, 80, 80, 25);
        add(cod);

        nombre=new JLabel();
        nombre.setText("Nombre");
        nombre.setBounds(20, 120, 80, 25);
        add(nombre);

        telefono=new JLabel();
        telefono.setText("telefono");
        telefono.setBounds(290, 160, 80, 25);
        add(telefono);

        edad=new JLabel();
        edad.setText("Edad");
        edad.setBounds(290, 120, 80, 25);
        add(edad);

        profesion=new JLabel();
        profesion.setText("Profesion");
        profesion.setBounds(20, 160, 80, 25);
        add(profesion);

        textCod=new JTextField();
        textCod.setBounds(80, 80, 80, 25);
        add(textCod);

        textNombre=new JTextField();
        textNombre.setBounds(80, 120, 190, 25);
        add(textNombre);

        textTelefono=new JTextField();
        textTelefono.setBounds(340, 160, 80, 25);
        add(textTelefono);

        textEdad=new JTextField();
        textEdad.setBounds(340, 120, 80, 25);
        add(textEdad);

        textProfesion=new JTextField();
        textProfesion.setBounds(80, 160, 190, 25);
        add(textProfesion);

        botonGuardar.addActionListener(this);
        botonCancelar.addActionListener(this);
        add(botonCancelar);
        add(botonGuardar);
        add(labelTitulo);
        limpiar();
        setSize(480, 300);
        setTitle("Patrón de Diseño MVC");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

    }


    private void limpiar(){
        textCod.setText("");
        textNombre.setText("");
        textEdad.setText("");
        textTelefono.setText("");
        textProfesion.setText("");
    }


    public void setControlador(Controlador miControlador) {
        this.miControlador=miControlador;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==botonGuardar){
            try {
                Persona miPersona=new Persona();
                miPersona.setId(Integer.parseInt(textCod.getText()));
                miPersona.setNombre(textNombre.getText());
                miPersona.setTelefono(Integer.parseInt(textTelefono.getText()));
                miPersona.setEdad(Integer.parseInt(textEdad.getText()));
                miPersona.setProfesion(textProfesion.getText());

                miControlador.registrarPersona(miPersona);	
            } catch (Exception ex) {
                    miControlador.mostrarError(7);
            }
        }
        if (e.getSource()==botonCancelar){
                this.dispose();
        }
    }
}
