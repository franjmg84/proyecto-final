package clases;

import java.util.ArrayList;

import javax.sound.sampled.Clip;

public class CancionConOpcion extends Cancion{
	
	private String respuestaCorrecta;
	ArrayList<String>respuestasPosibles;
	private Clip clip;
	/**
	 * @param nombre
	 * @param artista
	 * @param duracion
	 * @param audio
	 * @param respuestaCorrecta
	 * @param respuestasPosibles
	 */
	public CancionConOpcion(String nombre, Artista artista, float duracion, Clip audio, String  respuestaCorrecta,
			ArrayList<String> respuestasPosibles) {
		super(nombre, artista, duracion, audio);
		this.respuestaCorrecta = respuestaCorrecta;
		this.respuestasPosibles = respuestasPosibles;
		this.clip = audio;
	}
	/**
	 *Metodo get para la variable respuestaCorrecta
	 * @return devuelve el valor de respuestaCorrecta
	 */
	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}
	/**
	 *Metodo set pata la variable respuestaCorrecta
	 * @param respuestaCorrecta el nuevo valor de  respuestaCorrecta
	 */
	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}
	/**
	 *Metodo get para la variable respuestasPosibles
	 * @return devuelve el valor de respuestasPosibles
	 */
	public ArrayList<String> getRespuestasPosibles() {
		return respuestasPosibles;
	}
	/**
	 *Metodo set pata la variable respuestasPosibles
	 * @param respuestasPosibles el nuevo valor de  respuestasPosibles
	 */
	public void setRespuestasPosibles(ArrayList<String> respuestasPosibles) {
		this.respuestasPosibles = respuestasPosibles;
	}
	@Override
	public String toString() {
		return "CancionConOpcion [respuestaCorrecta=" + respuestaCorrecta + ", respuestasPosibles=" + respuestasPosibles
				+ "]";
	}
	
	public Clip getClipCancion() {
		return this.clip;
	}
	
	
}
