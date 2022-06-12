package clases;

import java.awt.image.BufferedImage;

import java.sql.SQLException;

import enumeraciones.Idioma;
import enumeraciones.Pais;

public class Artista extends Persona {
	
	/**
	 * @param nombre
	 * @param genero
	 * @param pais
	 * @param idioma
	 * @throws SQLException 
	 */
	public Artista(String nombre, Pais pais, Idioma idioma) throws SQLException {
		super(nombre, pais, idioma);
		
	
	}
	
	public Artista(){
	}
	
}
