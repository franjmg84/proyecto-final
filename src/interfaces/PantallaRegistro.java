package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Usuario;
import componentesvisuales.BotonAzul;
import enumeraciones.Idioma;
import enumeraciones.Pais;



public class PantallaRegistro extends JPanel {
	private JTextField campoUsuario;
	private Ventana ventana;
	private JPasswordField campoContrasena;
	private JTextField campoEmail;
	/**
	 * Create the panel.
	 */
	public PantallaRegistro( Ventana v) {
		this.ventana = v;
		setLayout(null);
		
		final JComboBox seleccionIdioma = new JComboBox();
		seleccionIdioma.setModel(new DefaultComboBoxModel(Idioma.values()));
		seleccionIdioma.setBounds(499, 461, 119, 27);
		add(seleccionIdioma);
		
		
		
		JLabel labelIdioma = new JLabel("Idioma");
		labelIdioma.setHorizontalAlignment(SwingConstants.CENTER);
		labelIdioma.setForeground(Color.LIGHT_GRAY);
		labelIdioma.setFont(new Font("Dubai Medium", Font.ITALIC, 20));
		labelIdioma.setBounds(489, 423, 119, 28);
		add(labelIdioma);
		
		campoEmail = new JTextField();
		campoEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		campoEmail.setBounds(254, 284, 382, 28);
		add(campoEmail);
		campoEmail.setColumns(10);
		
		final JLabel labelEmail = new JLabel("Email");
		labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
		labelEmail.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
		labelEmail.setForeground(Color.LIGHT_GRAY);
		labelEmail.setBounds(399, 239, 88, 48);
		add(labelEmail);
		
		campoContrasena = new JPasswordField();
		campoContrasena.setBounds(254, 368, 382, 28);
		add(campoContrasena);
		
		JLabel labelPais = new JLabel("Pais");
		labelPais.setForeground(Color.LIGHT_GRAY);
		labelPais.setHorizontalAlignment(SwingConstants.CENTER);
		labelPais.setFont(new Font("Dubai Medium", Font.ITALIC, 20));
		labelPais.setBounds(277, 423, 119, 28);
		add(labelPais);
		
		JLabel tituloRegistro = new JLabel("Registro");
		tituloRegistro.setForeground(Color.LIGHT_GRAY);
		tituloRegistro.setBounds(310, 75, 268, 102);
		tituloRegistro.setFont(new Font("Dubai Medium", Font.ITALIC, 40));
		tituloRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		add(tituloRegistro);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(444, 153, 45, 13);
		add(lblNewLabel);
		
		JLabel labelUsuario = new JLabel("Usuario");
		labelUsuario.setForeground(Color.LIGHT_GRAY);
		labelUsuario.setBounds(399, 163, 90, 35);
		labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsuario.setFont(new Font("Dubai Medium", Font.ITALIC, 20));
		add(labelUsuario);
		
		campoUsuario = new JTextField();
		campoUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		campoUsuario.setBounds(254, 201, 382, 28);
		campoUsuario.setFont(new Font("Dubai Medium", Font.ITALIC, 20));
		add(campoUsuario);
		campoUsuario.setColumns(10);
		
		JButton botonAtras = new BotonAzul("Atras");
		botonAtras.setBounds(681, 590, 169, 48);
		botonAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAPantalla("login");
			}
		});
		botonAtras.setFont(new Font("Dubai Medium", Font.ITALIC, 20));
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel labelContrasena = new JLabel("Contrase\u00F1a");
		labelContrasena.setForeground(Color.LIGHT_GRAY);
		labelContrasena.setBounds(387, 323, 119, 35);
		labelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasena.setFont(new Font("Dubai Medium", Font.ITALIC, 20));
		add(labelContrasena);
		
		JButton botonRegistrarse = new BotonAzul("Registrarse");
		botonRegistrarse.setBounds(362, 518, 169, 48);
		
		botonRegistrarse.setFont(new Font("Dubai Medium", Font.ITALIC, 20));
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		final JComboBox seleccionPais = new JComboBox();
		seleccionPais.setModel(new DefaultComboBoxModel(Pais.values()));
		seleccionPais.setBounds(277, 461, 119, 27);
		add(seleccionPais);
		add(botonRegistrarse);
		add(botonAtras);
		
		JLabel fondoLogo = new JLabel("");
		fondoLogo.setIcon(new ImageIcon(PantallaRegistro.class.getResource("/imagenes/fondo con logo.png")));
		fondoLogo.setBounds(0, 0, 900, 701);
		add(fondoLogo);
		
		botonRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreUsuario=campoUsuario.getText();
				String contrasena=new String(campoContrasena.getPassword());
				String email=campoEmail.getText();
				
				Pais pais= (Pais)seleccionPais.getSelectedItem();
				Idioma idioma= (Idioma)seleccionIdioma.getSelectedItem();
				try {//String email, String nombre, Pais pais, Idioma idioma, String pass
					new Usuario(email, nombreUsuario, pais, idioma, contrasena);
					
					
					
					JOptionPane.showMessageDialog(ventana,"Registrado con Exito",
							"Registro Completado",JOptionPane.PLAIN_MESSAGE);
					ventana.irAPantalla("login");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}

