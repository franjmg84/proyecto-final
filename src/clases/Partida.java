package clases;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Partida {
	
	ArrayList<CancionJugada> partida;
	ArrayList<Cancion> partida2;
	private Usuario usuario;
	LocalDateTime cuandoJugo;
	/**
	 * @param partida
	 * @param partida2
	 * @param usuario
	 * @param cuandoJugo
	 */
	public Partida(ArrayList<CancionJugada> partida, ArrayList<Cancion> partida2, Usuario usuario,
			LocalDateTime cuandoJugo) {
		super();
		this.partida = partida;
		this.partida2 = partida2;
		this.usuario = usuario;
		this.cuandoJugo = cuandoJugo;
	}
	/**
	 *Metodo get para la variable partida
	 * @return devuelve el valor de partida
	 */
	public ArrayList<CancionJugada> getPartida() {
		return partida;
	}
	/**
	 *Metodo set pata la variable partida
	 * @param partida el nuevo valor de  partida
	 */
	public void setPartida(ArrayList<CancionJugada> partida) {
		this.partida = partida;
	}
	/**
	 *Metodo get para la variable partida2
	 * @return devuelve el valor de partida2
	 */
	public ArrayList<Cancion> getPartida2() {
		return partida2;
	}
	/**
	 *Metodo set pata la variable partida2
	 * @param partida2 el nuevo valor de  partida2
	 */
	public void setPartida2(ArrayList<Cancion> partida2) {
		this.partida2 = partida2;
	}
	/**
	 *Metodo get para la variable usuario
	 * @return devuelve el valor de usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 *Metodo set pata la variable usuario
	 * @param usuario el nuevo valor de  usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 *Metodo get para la variable cuandoJugo
	 * @return devuelve el valor de cuandoJugo
	 */
	public LocalDateTime getCuandoJugo() {
		return cuandoJugo;
	}
	/**
	 *Metodo set pata la variable cuandoJugo
	 * @param cuandoJugo el nuevo valor de  cuandoJugo
	 */
	public void setCuandoJugo(LocalDateTime cuandoJugo) {
		this.cuandoJugo = cuandoJugo;
	}
	@Override
	public String toString() {
		return "Partida [partida=" + partida + ", partida2=" + partida2 + ", usuario=" + usuario + ", cuandoJugo="
				+ cuandoJugo + "]";
	}
	
	
}
