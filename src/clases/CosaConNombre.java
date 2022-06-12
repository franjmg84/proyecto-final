package clases;

import excepciones.NombreInvalidoException;

public abstract class  CosaConNombre {
	
	protected String nombre;

	/**
	 * @param nombre
	 */
	public CosaConNombre(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 *Metodo get para la variable nombre
	 * @return devuelve el valor de nombre
	 */
	public String getNombre() {

		return nombre;
	}

	/**
	 *Metodo set para la variable nombre
	 * @param nombre el nuevo valor de  nombre
	 * @throws NombreInvalidoException 
	 */
	public void setNombre(String nombre) throws NombreInvalidoException {
		if(nombre.isEmpty()) {
			throw new NombreInvalidoException("El nombre no puede estar vacio");
		}else {
		this.nombre = nombre;
		}
	}

	@Override
	public String toString() {
		return "CosaConNombre [nombre=" + nombre + "]";
	}
	
	
}
