package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConexionBD {
	private final static String cadenaConexion="jdbc:mysql://127.0.0.1:3306/proyecto";
	private final static String usuarioBD="root";
	private final static String passwordBD="";
	private static Connection conexion; //singleton. solo es posible tener un objeto conexion por eso es static.
	
	//en las variables internas static significa si son compartidas o no.
	//nebula es el static. comparten el mismo cerebro. no cambia. 
	
	public static Statement conectar() {
		//statement, el objeto que nos permite hacer las consultas.
		try {
			if(conexion==null) {//si no hay conexión, me conecto
				conexion=DriverManager.getConnection(cadenaConexion,usuarioBD,passwordBD);
			}
			return conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		}
	}
	
	public static void desconectar() {
		if (conexion!=null) {
			try {
				conexion.close();
				conexion=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
