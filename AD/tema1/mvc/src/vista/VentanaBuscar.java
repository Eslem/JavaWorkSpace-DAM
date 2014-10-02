package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.POJO.Persona;

import controlador.Controlador;

public class VentanaBuscar  extends JFrame implements ActionListener {

    private Controlador miControlador; //objeto miControlador que permite la relacion entre esta clase y la clase controlador
    private JLabel labelTitulo;
    private JTextField textCod,textNombre,textEdad,textTelefono,textProfesion;
    private JLabel cod,nombre,edad,telefono,profesion;
    private JButton botonGuardar,botonCancelar,botonBuscar,botonModificar,botonEliminar;

    /**
     * constructor de la clase donde se inicializan todos los componentes
     * de la ventana de busqueda
     */
    public VentanaBuscar() {

        botonGuardar = new JButton();
        botonGuardar.setBounds(50, 220, 120, 25);
        botonGuardar.setText("Guardar");

        botonCancelar = new JButton();
        botonCancelar.setBounds(190, 250, 120, 25);
        botonCancelar.setText("Cancelar");

        botonBuscar = new JButton();
        botonBuscar.setBounds(170, 80, 80, 25);
        botonBuscar.setText("Buscar");

        botonEliminar = new JButton();
        botonEliminar.setBounds(330, 220, 120, 25);
        botonEliminar.setText("Eliminar");

        botonModificar = new JButton();
        botonModificar.setBounds(190, 220, 120, 25);
        botonModificar.setText("Modificar");

        labelTitulo = new JLabel();
        labelTitulo.setText("MANTENIMIENTO PERSONAS");
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

        profesion=new JLabel();
        profesion.setText("profesion");
        profesion.setBounds(20, 160, 80, 25);
        add(profesion);

        edad=new JLabel();
        edad.setText("Edad");
        edad.setBounds(290, 120, 80, 25);
        add(edad);

        textCod=new JTextField();
        textCod.setBounds(80, 80, 80, 25);
        add(textCod);

        textNombre=new JTextField();
        textNombre.setBounds(80, 120, 190, 25);
        add(textNombre);

        textTelefono=new JTextField();
        textTelefono.setBounds(340, 160, 80, 25);
        add(textTelefono);

        textProfesion=new JTextField();
        textProfesion.setBounds(80, 160, 190, 25);
        add(textProfesion);

        textEdad=new JTextField();
        textEdad.setBounds(340, 120, 80, 25);
        add(textEdad);

        botonModificar.addActionListener(this);
        botonEliminar.addActionListener(this);
        botonBuscar.addActionListener(this);
        botonGuardar.addActionListener(this);
        botonCancelar.addActionListener(this);

        add(botonCancelar);
        add(botonBuscar);
        add(botonModificar);
        add(botonEliminar);
        add(botonGuardar);
        add(labelTitulo);
        limpiar();

        setSize(480, 320);
        setTitle("Patrón de Diseño MVC");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

    }


    public void setControlador(Controlador miControlador) {
        this.miControlador=miControlador;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==botonGuardar){
            try {
                Persona miPersona=new Persona();
                miPersona.setId(Integer.parseInt(textCod.getText()));
                miPersona.setNombre(textNombre.getText());
                miPersona.setTelefono(Integer.parseInt(textTelefono.getText()));
                miPersona.setEdad(Integer.parseInt(textEdad.getText()));
                miPersona.setProfesion(textProfesion.getText());

                miControlador.modificarPersona(miPersona);

                if (miControlador.modificarPersona(miPersona)) {
                    habilita(true, false, false, false, false, true, false, true, true);	
                }
            } catch (Exception e2) {
                miControlador.mostrarError(7);
            }

        }

        if (e.getSource()==botonBuscar){
            Persona miPersona = new Persona();
            miPersona.setId(Integer.parseInt(textCod.getText()));
            miPersona = miControlador.buscarPersona(miPersona);
            if (miPersona!=null){
                muestraPersona(miPersona);
            } else {
                miControlador.mostrarAdvertencia(8);
            }
        }

        if (e.getSource()==botonModificar){
            habilita(false, true, true, true, true, false, true, false, false);
        }

        if (e.getSource()==botonEliminar){
            if (!textCod.getText().equals("")){
                if (miControlador.mostrarConfirmacion("¿Esta seguro de eliminar la Persona?") == 0){
                    Persona miPersona = new Persona();
                    miPersona.setId(Integer.parseInt(textCod.getText()));
                    miControlador.eliminarPersona(miPersona);
                    limpiar();
                }
            } else {
                miControlador.mostrarError(9);
            }

        }
        if (e.getSource()==botonCancelar){
            this.dispose();
        }
    }



    /**
     * permite cargar los datos de la persona consultada
     * @param miPersona
     */
    private void muestraPersona(Persona miPersona) {
        textNombre.setText(miPersona.getNombre());
        textEdad.setText(miPersona.getEdad()+"");
        textTelefono.setText(miPersona.getTelefono()+"");
        textProfesion.setText(miPersona.getProfesion());
        habilita(true, false, false, false, false, true, false, true, true);
    }


    /**
     * Permite limpiar los componentes
     */
    public void limpiar(){
        textCod.setText("");
        textNombre.setText("");
        textEdad.setText("");
        textTelefono.setText("");
        textProfesion.setText("");
        habilita(true, false, false, false, false, true, false, true, true);
    }


    /**
     * Permite habilitar los componentes para establecer una modificacion
     * @param codigo
     * @param nombre
     * @param edad
     * @param tel
     * @param profesion
     * @param bBuscar
     * @param bGuardar
     * @param bModificar
     * @param bEliminar
     */
    public void habilita(boolean codigo, boolean nombre, boolean edad, boolean tel, boolean profesion, boolean bBuscar, boolean bGuardar, boolean bModificar, boolean bEliminar){
        textCod.setEditable(codigo);
        textNombre.setEditable(nombre);
        textEdad.setEditable(edad);
        textTelefono.setEditable(tel);
        textProfesion.setEditable(profesion);
        botonBuscar.setEnabled(bBuscar);
        botonGuardar.setEnabled(bGuardar);
        botonModificar.setEnabled(bModificar);
        botonEliminar.setEnabled(bEliminar);
    }
}
