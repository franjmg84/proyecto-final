package clases;

import javax.sound.sampled.Clip;

public class CancionRellenarHueco extends Cancion {
	
	private String respuesta;

	/**
	 * @param nombre
	 * @param artista
	 * @param duracion
	 * @param audio
	 * @param respuesta
	 */
	public CancionRellenarHueco(String nombre, Artista artista, float duracion, Clip audio, String respuesta) {
		super(nombre, artista, duracion, audio);
		this.respuesta = respuesta;
	}

	/**
	 *Metodo get para la variable respuesta
	 * @return devuelve el valor de respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 *Metodo set pata la variable respuesta
	 * @param respuesta el nuevo valor de  respuesta
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "CancionRellenarHueco [respuesta=" + respuesta + "]";
	}
	
	
}
