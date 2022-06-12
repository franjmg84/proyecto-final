package clases;

import java.sql.SQLException;

import java.sql.Statement;


import enumeraciones.Idioma;
import enumeraciones.Pais;
import excepciones.NombreInvalidoException;
import excepciones.PassInvalidException;
import utils.ConexionBD;

public class Persona extends CosaConNombre {
	
	protected Pais pais;
	protected Idioma idioma;
	/**
	 * @param nombre
	 * @param pais
	 * @param idioma
	 */
	public Persona(String nombre, Pais pais, Idioma idioma) {
		super(nombre);
		this.pais = pais;
		this.idioma = idioma;
	}
	public Persona() {
		super("");
	}
	/**
	 *Metodo get para la variable pais
	 * @return devuelve el valor de pais
	 */
	public Pais getPais() {
		return pais;
	}
	/**
	 *Metodo set pata la variable pais
	 * @param pais el nuevo valor de  pais
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	/**
	 *Metodo get para la variable idioma
	 * @return devuelve el valor de idioma
	 */
	public Idioma getIdioma() {
		return idioma;
	}
	/**
	 *Metodo set pata la variable idioma
	 * @param idioma el nuevo valor de  idioma
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	@Override
	public String toString() {
		return "Persona [pais=" + pais + ", idioma=" + idioma + "]";
	}


}
