package clases;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import enumeraciones.Idioma;
import enumeraciones.Pais;
import excepciones.PassInvalidException;
import excepciones.UsuarioNoExisteException;
import excepciones.NombreInvalidoException;
import utils.ConexionBD;

/**
 * lo que hace
 * @author djfra
 *
 */
public class Usuario extends Persona {
	/** para que sirve */
	private String pass;
	/** para que sirve */
	private String email;

	/**
	 * kaefowkoaewafew
	 * @param email fea fea
	 * @param nombre feaaf 
	 * @param pais
	 * @param idio afew fema
	 * @param pass
	 * @throws SQLException faeaef w efw
	 * @throws PassInvalidException fawf aew
	 */
	public Usuario(String email, String nombre, Pais pais, Idioma idioma, String pass)
			throws SQLException, PassInvalidException {
		super(nombre, pais, idioma);

		// Proteger los setters
		if (!isPassValid(pass)) {
			throw new PassInvalidException("La contraseña debe tener al menos 3 caracteres.");
		}
		Statement smt = ConexionBD.conectar();
		String s = "INSERT INTO `usuario` (`nombre`, `genero`, `pais`, `idioma`, `pass`, `email`, `Partida_nombre_usuario`) VALUES ('"+nombre+"', '', '"+pais+"', '"+idioma+"', '"+pass+"', '"+email+"', '"+nombre+"');";
		System.out.println("SQL INSERT -> "+s);
		if (smt.executeUpdate(s) > 0) {
			this.email = email;
			this.pass = pass;

		} else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar");
		}

		ConexionBD.desconectar();
	}

	protected Usuario(String email) throws SQLException, UsuarioNoExisteException {

		Statement smt = ConexionBD.conectar();
		ResultSet cursor = smt.executeQuery("select * from usuarios where email='" + email + "'");
		
		if (cursor.next()) {
			this.email = cursor.getString("email");
			this.pass = cursor.getString("pass");
			this.nombre = cursor.getString("nombre");

		} else {
			ConexionBD.desconectar();
			throw new UsuarioNoExisteException("No existe ese email en la BD");
		}
		ConexionBD.desconectar();
	}

	/**
	 * Metodo get para la variable pass
	 * 
	 * @return devuelve el valor de pass
	 */
	public String getPass() {
		return pass;
	}

	public static boolean isPassValid(String pass) {
		try {
			if (pass.length() < 3) {
				return false;
			} else {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		}

	}

	protected Usuario(String email, String pass)
			throws SQLException, PassInvalidException, UsuarioNoExisteException, PassInvalidException {

			// Proteger los setters
		if (!isPassValid(pass)) {
			throw new PassInvalidException("La contraseña debe tener al menos 3 caracteres.");
			
		}
		System.out.println("Estoy dentro");
		Statement smt = ConexionBD.conectar();
		ResultSet cursor = smt.executeQuery("select * from usuarios where email='" + email + "'");
		
		if (cursor.next()) {
			this.pass = cursor.getString("pass");
			if (!this.pass.equals(pass)) {
				ConexionBD.desconectar();
				throw new PassInvalidException("La contraseña no es correcta");
			}
			this.email = cursor.getString("email");
			this.nombre = cursor.getString("nombre");
		} else {
			ConexionBD.desconectar();
			throw new UsuarioNoExisteException("No existe ese email en la BD");
			
		}
		ConexionBD.desconectar();
	}
	
	
	/**
	 * Metodo set pata la variable pass
	 * 
	 * @param pass el nuevo valor de pass
	 * @throws PassInvalidException
	 */
	public void setPass(String pass) throws PassInvalidException {
		// Proteger los setters
		if (!isPassValid(pass)) {
			throw new PassInvalidException("La contraseña debe tener al menos 3 caracteres.");
		}

		Statement smt = ConexionBD.conectar();
		try {
			if (smt.executeUpdate("update usuarios set pass='" + pass + "' where nombre='" + this.nombre + "'") > 0) {
				this.pass = pass;
			} else {
				ConexionBD.desconectar();
				throw new SQLException("No se ha podido cambiar el password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionBD.desconectar();
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + "]";
	}

}
