package interfaces;

import java.awt.GridLayout;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import java.awt.FlowLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import clases.Artista;
import clases.CancionConOpcion;
import enumeraciones.Idioma;
import enumeraciones.Pais;
import utils.ConexionBD;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaJuego extends JPanel implements ActionListener {
	private JPanel panel;
	private JTextField textField;
	private JRadioButton rdbtnNewRadioButton;
	
	
	
	private JRadioButton rdbtnNewRadioButtonSeleccionado;
	
	private ArrayList<CancionConOpcion> canciones = new ArrayList<>();
	private JPanel panel_1;
	private Label label;
	private Label label_1;
	private Label labelFallos;
	private Label preguntasTotales;
	private Label label_4;
	private Label labelAciertos;
	private Button buttonSiguiente, buttonPlay, buttonStop;
	private JPanel panelJugada;
	
	private JRadioButton radio1,radio2,radio3,radio4;
	private ButtonGroup grupoDeRadios;
	
	
	int preguntaActual = 0;
	int preguntasTotal = 0;
	int aciertos = 1;
	int fallos = 0;
	private CancionConOpcion cancionActual;

	/**
	 * Create the panel.
	 */
	public PantallaJuego(Ventana v) {
		initComponents();
	}
	private void initComponents() {
		cargarCanciones();
		
		setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 900, 800);
		add(panel_1);
		panel_1.setLayout(null);
		
		label = new Label("INICIO JUEGO");
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 10, 508, 22);
		panel_1.add(label);
		
		label_1 = new Label("Fallos");
		label_1.setBounds(10, 38, 68, 22);
		panel_1.add(label_1);
		
		labelFallos = new Label("hola");
		labelFallos.setBounds(84, 38, 62, 22);
		panel_1.add(labelFallos);
		
		preguntasTotales = new Label("New label");
		preguntasTotales.setAlignment(Label.CENTER);
		preguntasTotales.setBounds(152, 38, 206, 22);
		panel_1.add(preguntasTotales);
		preguntasTotal = canciones.size();
		preguntaActual++;
		preguntasTotales.setText(""+preguntaActual+"/"+preguntasTotal);
		
		label_4 = new Label("ACIERTOS");
		label_4.setBounds(364, 38, 86, 22);
		panel_1.add(label_4);
		
		labelAciertos = new Label("New label");
		labelAciertos.setBounds(456, 38, 62, 22);
		panel_1.add(labelAciertos);
		
		buttonSiguiente = new Button("SIGUIENTE");
		buttonSiguiente.addActionListener(this);
		buttonSiguiente.setBounds(0, 600, 548, 45);
		panel_1.add(buttonSiguiente);
		
		buttonPlay = new Button("PLAY");
		buttonPlay.addActionListener(this);
		buttonPlay.setBounds(0, 60, 150, 45);
		panel_1.add(buttonPlay);
		
		
		buttonStop = new Button("STOP");
		buttonStop.addActionListener(this);
		buttonStop.setBounds(100, 60, 150, 45);
		panel_1.add(buttonStop);
		
		panelJugada = new JPanel();
		panelJugada.setBounds(10, 300, 900, 300);
		panel_1.add(panelJugada);
		panelJugada.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		
		buttonPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancionActual.getClipCancion().start();
			}
		});	
		
		buttonStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancionActual.getClipCancion().stop();
			}
		});	
		
		//AQUI LIMPIAMOS EL JPANEL DE LA JUGADA
		panelJugada.removeAll();
		panelJugada.repaint();
		
		//AQUI PONEMOS EL TEXTO DE LOS CIERTOS Y FALLOS
		labelAciertos.setText(""+aciertos);
		labelFallos.setText(""+fallos);
		System.out.println("PREGUNTA ACTUAL "+preguntaActual);
		
		//AQUI CARGAMOS EL PANEL
		cargarPnael(preguntaActual-1);
		if(preguntaActual>=preguntasTotal  ) {
			System.out.println();
			buttonSiguiente.setEnabled(false);
			buttonSiguiente.setVisible(false);
			System.out.println("if "+preguntaActual);
			preguntasTotales.setText(""+preguntaActual+"/"+preguntasTotal);
			System.out.println("RESPUESTA COREECTA "+canciones.get(preguntaActual-1).getRespuestaCorrecta()+"RESPUTA USUARIO "+rdbtnNewRadioButtonSeleccionado.getText()+"pREGUNTAS TOTAL "+preguntasTotal+" pregunta actual "+preguntaActual);
			if(esCorrecta(rdbtnNewRadioButtonSeleccionado.getText(), canciones.get(preguntaActual-1).getRespuestaCorrecta())) {
				aciertos++;
				labelAciertos.setText(""+aciertos);
			}else {
				fallos++;
				labelFallos.setText(""+fallos);
			}
		}else {
			buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancionActual.getClipCancion().stop();
				if(aciertos>=canciones.size()) {
					JOptionPane.showMessageDialog(null, "GANASTE", "WIN", JOptionPane.INFORMATION_MESSAGE);
					Statement smt = ConexionBD.conectar();
					LocalDateTime ld = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					String formattedDateTime = ld.format(formatter);
					String s = "INSERT INTO `partida` (`nombre_usuario`, `cancion_jugada`, `cacion`, `fecha_hora`, `acierto`) VALUES ('"+PantallaLogin.emailJugador+"', NULL, NULL, '"+formattedDateTime+"', '"+aciertos+"');";
					System.out.println("SQL INSERT -> "+s);
					try {
						if (smt.executeUpdate(s) > 0) {
							ConexionBD.desconectar();
							System.exit(0);

						} else {
							ConexionBD.desconectar();
							throw new SQLException("No se ha podido insertar");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(preguntaActual>=canciones.size() && aciertos<preguntasTotal) {
					JOptionPane.showMessageDialog(null, "PERDISTE", "LOSE", JOptionPane.ERROR_MESSAGE);
					Statement smt = ConexionBD.conectar();
					LocalDateTime ld = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					String formattedDateTime = ld.format(formatter);
					String s = "INSERT INTO `partida` (`nombre_usuario`, `cancion_jugada`, `cacion`, `fecha_hora`, `acierto`) VALUES ('"+PantallaLogin.emailJugador+"', NULL, NULL, '"+formattedDateTime+"', '"+aciertos+"');";
					System.out.println("SQL INSERT -> "+s);
					try {
						if (smt.executeUpdate(s) > 0) {
							ConexionBD.desconectar();
							System.exit(0);

						} else {
							ConexionBD.desconectar();
							throw new SQLException("No se ha podido insertar");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(preguntaActual<preguntasTotal) {
					if(esCorrecta(rdbtnNewRadioButtonSeleccionado.getText(), cancionActual.getRespuestaCorrecta())) {
						aciertos++;
						labelAciertos.setText(""+aciertos);
					}else {
						fallos++;
						labelFallos.setText(""+fallos);
					}
					preguntaActual++;
					//preguntasTotales.setText(""+(preguntaActual+=1)+"/"+preguntasTotal);
					preguntasTotales.setText(""+preguntaActual+"/"+preguntasTotal);
					cargarPnael(preguntaActual-1);
					System.out.println("PREGUNTACA CTUAL "+preguntaActual);	
				}
			}
		});	
		}
		
	}
	
	
	private boolean esCorrecta(String opcionElegida, String opcionCorrecta) {
		boolean correcto = false;
		//cpmparamos la opcion elegida la pasamos ambas opciones minuscula y comparamos
		if(opcionElegida.toLowerCase().equals(opcionCorrecta.toLowerCase())) {
			correcto = true;
		}
		
		return correcto;
	}
	
	
	private void cargarPnael(int posicion) {
		//QUITAMOS EL CONTENIDO DEL PANEL
		panelJugada.removeAll();
		panelJugada.repaint();
		
		//ESTA VARIABLE INDICA EL RADIOBUTTON QUE HEMOS SELECCIONADO
		rdbtnNewRadioButtonSeleccionado = null;
		
		//DEL ARRAYLIST DE CANCIONES CON COPCIONES SACAMOS LA CACNICON DE LA POSICION QUE VA INDICANDO
		CancionConOpcion cancion = canciones.get(posicion);
		//GUARDMOS LA CANCION EN UNA VARIABLE A NIEL DE CLASE
		cancionActual = cancion;
		System.out.println("CANCION ACTUAL..."+cancionActual.getRespuestaCorrecta());
		//SACAMOS EL CLIP DE LA CANCION
		Clip clip = cancion.getClipCancion();
		
		///SACAMOS LAS RESPUESTAS CORRECTAS
		ArrayList<String> respuestasCorrectas = cancion.getRespuestasPosibles();
		
		//SACAMOS LA RESPUESTA CORRECTA
		String respuestaCorrecta = cancion.getRespuestaCorrecta();
		
		
		//CREAMOS LOS RADIO BUTTON
		grupoDeRadios=new ButtonGroup();
		  
		  radio1=new JRadioButton();
		  radio1.setText(respuestasCorrectas.get(0));
		  radio1.setBounds(20, 30, 200, 30);
		  
		  radio3=new JRadioButton();
		  radio3.setText(respuestasCorrectas.get(2));
		  radio3.setBounds(20, 90, 200, 30);
		  
		  
		  
		  radio2=new JRadioButton();
		  radio2.setText(respuestasCorrectas.get(1));
		  radio2.setBounds(20, 60, 200, 30);
		  
		  
		  radio4=new JRadioButton();
		  radio4.setText(respuestasCorrectas.get(3));
		  radio4.setBounds(20, 120, 200, 30);
		  
		  grupoDeRadios.add(radio1);
		  grupoDeRadios.add(radio2);
		  grupoDeRadios.add(radio3);
		  grupoDeRadios.add(radio4);
		  
		  panelJugada.add(radio1);
		  panelJugada.add(radio2);
		  panelJugada.add(radio3);
		  panelJugada.add(radio4);
		  
		  
		  //CREAMOS EL LISTENER DE LOS RADIOBUTTONS
		  radio1.addActionListener(this);
		  radio2.addActionListener(this);
		  radio3.addActionListener(this);
		  radio4.addActionListener(this);
		  
	}
	
	
	//METODO QUE CARGA LAS CANCIONES
	private void cargarCanciones() {
		
		//VARIABLE QUE INDICA LA RUTA DE LA CANCION
		String ruta = "C:/Users/fernando/Documents/PROYECTOS_JAVA/proyecto-final/musica/";
		//VARIABLE QUE INDICA EL FORMATO DE LA CANCION
		String formato = ".wav";
		//SE GUARDAN LAS CANCIONES
		String cancion1 = ruta+"ACDC"+formato;
		String cancion2 = ruta+"BonJovi"+formato;
		String cancion3 = ruta+"los_simpson"+formato;
		String cancion4 = ruta+"Lil_Nas_X_THATS_WHAT_I_WANT"+formato;
		String cancion5 = ruta+"Queen_We_Are_The_Champions"+formato;
		String cancion6 = ruta+"Queen_We_Will_Rock_You"+formato;
		String cancion7 = ruta+"Smash-Mouth-I-m-A-Believer"+formato;
		String cancion8 = ruta+"The_Black_Eyed Peas_Pump_It"+formato;
		String cancion9 = ruta+"Zzoilo_&_Aitana_Mon_Amour"+formato;
		String cancion10 = ruta+"Nirvana_Smells_Like_Teen_Spirit"+formato;
		
		//AÑADIMOS LAS CANCIONES AL ARRAYLIST
		canciones.add(cargarCancion1(cancion1));
		canciones.add(cargarCancion2(cancion2));
		canciones.add(cargarCancion3(cancion3));
		canciones.add(cargarCancion4(cancion4));
		canciones.add(cargarCancion5(cancion5));
		
		canciones.add(cargarCancion6(cancion6));
		canciones.add(cargarCancion7(cancion7));
		canciones.add(cargarCancion8(cancion8));
		canciones.add(cargarCancion9(cancion9));
		canciones.add(cargarCancion10(cancion10));
	}
	
	
	private CancionConOpcion cargarCancion1(String cancion) {
		//PONEMOS EL NOMBRE DE LA CANCION
		String nombre = "ACDC";
		//CREAMOS UNA VARIABLE CLIP
		Clip clip = null; 
		//CREAMOS UN FILE A PARTIR DE LA RUTA PASADA
		File musica = new File(cancion);
		//AGREGAMOS LA RESPUESTA CORRECTA TAL CUAL LA VAYAMNOS A PONER EN EL ARRAYLIST DE RESPUESTAS POSIBLES
		String respuestaCorrecta = "ACDC";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		//AGREAMOS LAS POSIBLES RESPUESTAS INCLUYENDO LA QUE DEBERIA SER LA BUENA
		respuestasPosibles.add("Respuesta 1 ACDC");
		respuestasPosibles.add("Respuesta 2 ACDC");
		respuestasPosibles.add("Respuesta 3 ACDC");
		respuestasPosibles.add("ACDC");
		
		Collections.shuffle(respuestasPosibles);
		
		Artista artista = new Artista();
		
		//COMPROBAMOS SI EL CLIP DE MUSICA EXISTE
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("ACDC", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//CREAMOS UNA CACNCION CON OPCION
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		//retorno cancion con respuestas
		return c;
	}
	
	private CancionConOpcion cargarCancion2(String cancion) {
		String nombre = "BonJovi";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "BonJovi";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 BonJovi");
		respuestasPosibles.add("Respuesta 2 BonJovi");
		respuestasPosibles.add("Respuesta 3 BonJovi");
		respuestasPosibles.add("BonJovi");
		
		Collections.shuffle(respuestasPosibles);
		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("BonJovi", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	private CancionConOpcion cargarCancion3(String cancion) {
		String nombre = "los_simpson";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "los_simpson";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 los_simpson");
		respuestasPosibles.add("Respuesta 2 los_simpson");
		respuestasPosibles.add("Respuesta 3 los_simpson");
		respuestasPosibles.add("los_simpson");
		
		Collections.shuffle(respuestasPosibles);

		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("los_simpson", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	private CancionConOpcion cargarCancion4(String cancion) {
		String nombre = "Lil_Nas_X_THATS_WHAT_I_WANT";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "Lil_Nas_X_THATS_WHAT_I_WANT";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 Lil_Nas_X_THATS_WHAT_I_WANT");
		respuestasPosibles.add("Respuesta 2 Lil_Nas_X_THATS_WHAT_I_WANT");
		respuestasPosibles.add("Respuesta 3 Lil_Nas_X_THATS_WHAT_I_WANT");
		respuestasPosibles.add("Lil_Nas_X_THATS_WHAT_I_WANT");
		
		Collections.shuffle(respuestasPosibles);

		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("Lil_Nas_X_THATS_WHAT_I_WANT", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	private CancionConOpcion cargarCancion5(String cancion) {
		String nombre = "Queen_We_Are_The_Champions";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "Queen_We_Are_The_Champions";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 Queen_We_Are_The_Champions");
		respuestasPosibles.add("Respuesta 2 Queen_We_Are_The_Champions");
		respuestasPosibles.add("Respuesta 3 Queen_We_Are_The_Champions");
		respuestasPosibles.add("Queen_We_Are_The_Champions");
		
		Collections.shuffle(respuestasPosibles);
		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("Queen_We_Are_The_Champions", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	private CancionConOpcion cargarCancion6(String cancion) {
		String nombre = "Queen_We_Will_Rock_You";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "Queen_We_Will_Rock_You";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 Queen_We_Will_Rock_You");
		respuestasPosibles.add("Respuesta 2 Queen_We_Will_Rock_You");
		respuestasPosibles.add("Respuesta 3 Queen_We_Will_Rock_You");
		respuestasPosibles.add("Queen_We_Will_Rock_You");
		
		Collections.shuffle(respuestasPosibles);
		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("Queen_We_Will_Rock_You", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	private CancionConOpcion cargarCancion7(String cancion) {
		String nombre = "Smash-Mouth-I-m-A-Believer";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "Smash-Mouth-I-m-A-Believer";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 Smash-Mouth-I-m-A-Believer");
		respuestasPosibles.add("Respuesta 2 Smash-Mouth-I-m-A-Believer");
		respuestasPosibles.add("Respuesta 3 Smash-Mouth-I-m-A-Believer");
		respuestasPosibles.add("Smash-Mouth-I-m-A-Believer");
		
		Collections.shuffle(respuestasPosibles);
		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("Smash-Mouth-I-m-A-Believer", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	private CancionConOpcion cargarCancion8(String cancion) {
		String nombre = "The_Black_Eyed Peas_Pump_It";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "The_Black_Eyed Peas_Pump_It";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 The_Black_Eyed Peas_Pump_It");
		respuestasPosibles.add("Respuesta 2 The_Black_Eyed Peas_Pump_It");
		respuestasPosibles.add("Respuesta 3 The_Black_Eyed Peas_Pump_It");
		respuestasPosibles.add("The_Black_Eyed Peas_Pump_It");
		
		Collections.shuffle(respuestasPosibles);

		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("The_Black_Eyed Peas_Pump_It", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	private CancionConOpcion cargarCancion9(String cancion) {
		String nombre = "los_simpson";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "Zzoilo_&_Aitana_Mon_Amour";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 Zzoilo_&_Aitana_Mon_Amour");
		respuestasPosibles.add("Respuesta 2 Zzoilo_&_Aitana_Mon_Amour");
		respuestasPosibles.add("Respuesta 3 Zzoilo_&_Aitana_Mon_Amour");
		respuestasPosibles.add("Zzoilo_&_Aitana_Mon_Amour");
		
		Collections.shuffle(respuestasPosibles);
		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("Zzoilo_&_Aitana_Mon_Amour", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	
	private CancionConOpcion cargarCancion10(String cancion) {
		String nombre = "Nirvana_Smells_Like_Teen_Spirit";
		
		Clip clip = null; 
		File musica = new File(cancion);
		String respuestaCorrecta = "Nirvana_Smells_Like_Teen_Spirit";
		ArrayList<String> respuestasPosibles = new ArrayList<>();
		
		respuestasPosibles.add("Respuesta 1 Nirvana_Smells_Like_Teen_Spirit");
		respuestasPosibles.add("Respuesta 2 Nirvana_Smells_Like_Teen_Spirit");
		respuestasPosibles.add("Respuesta 3 Nirvana_Smells_Like_Teen_Spirit");
		respuestasPosibles.add("Nirvana_Smells_Like_Teen_Spirit");
		
		Collections.shuffle(respuestasPosibles);
		
		Artista artista = new Artista();
		if(musica.exists()){
			System.out.println("Existe---"+musica.exists());
			AudioInputStream archivo;
			try {
				archivo = AudioSystem.getAudioInputStream(musica);
				clip = AudioSystem.getClip();
				clip.open(archivo);		
				artista = new Artista("Nirvana_Smells_Like_Teen_Spirit", Pais.ESPAÑA, Idioma.ESPAÑOL);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		CancionConOpcion c = new CancionConOpcion(nombre, artista, clip.getMicrosecondLength(), clip, respuestaCorrecta, respuestasPosibles);
		
		return c;
	}
	
	
	//listener de los JRADIOBUTTON
	public void actionPerformed(ActionEvent e) {
		//comprobamos si el radiobutton seleccionado es el radiobutton1
		if (e.getSource() == radio1) {
			//si es igual igualamos la variable creada a la del radio1
			rdbtnNewRadioButtonSeleccionado = radio1;
		}
		//comprobamos si el radiobutton seleccionado es el radiobutton2
		if (e.getSource() == radio2) {
			//si es igual igualamos la variable creada a la del radio2
			rdbtnNewRadioButtonSeleccionado = radio2;
		}
		//comprobamos si el radiobutton seleccionado es el radiobutton3
		if (e.getSource() == radio3) {
			//si es igual igualamos la variable creada a la del radio3
			rdbtnNewRadioButtonSeleccionado = radio3;
		}
		//comprobamos si el radiobutton seleccionado es el radiobutton4
		if (e.getSource() == radio4) {
			//si es igual igualamos la variable creada a la del radio4
			rdbtnNewRadioButtonSeleccionado = radio4;
		}
	}
}
