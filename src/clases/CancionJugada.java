package clases;

public class CancionJugada {
	
	private String cancion;
	private boolean acierto;
	/**
	 * @param cancion
	 * @param acierto
	 */
	public CancionJugada(String cancion, boolean acierto) {
		super();
		this.cancion = cancion;
		this.acierto = acierto;
	}
	/**
	 *Metodo get para la variable cancion
	 * @return devuelve el valor de cancion
	 */
	public String getCancion() {
		return cancion;
	}
	/**
	 *Metodo set pata la variable cancion
	 * @param cancion el nuevo valor de  cancion
	 */
	public void setCancion(String cancion) {
		this.cancion = cancion;
	}
	/**
	 *Metodo get para la variable acierto
	 * @return devuelve el valor de acierto
	 */
	public boolean isAcierto() {
		return acierto;
	}
	/**
	 *Metodo set pata la variable acierto
	 * @param acierto el nuevo valor de  acierto
	 */
	public void setAcierto(boolean acierto) {
		this.acierto = acierto;
	}
	@Override
	public String toString() {
		return "CancionJugada [cancion=" + cancion + ", acierto=" + acierto + "]";
	}
	
	
}
